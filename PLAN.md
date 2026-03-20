# Plan: Modular KMP Scaffold — Architecture Upgrade

## Context

The kmp-scaffold should be a **production-ready starting point** with modular, swappable architectural components extracted from best practices across 5 production projects (Passkeep, abookify, tacker, lit-connect, zubrila). Each concern is cleanly packaged — reusable infrastructure in standalone modules, app-specific code in `composeApp`.

## Current State

Single `:composeApp` module with basic Koin DI, PreCompose navigation, placeholder screens. Has Ktor, SQLDelight, DataStore configured but not properly implemented.

## Decisions
- **Package name**: `com.bearminds.scaffold` (rename from `com.bearminds.passkeep`)
- **Navigation**: Replace PreCompose with AndroidX Navigation Compose (JetBrains fork, not Google's)
- **Scope**: Full scaffold — storage + network + connectivity + analytics
- **Module split**: 3 standalone modules (architecture, theme, core) + composeApp
- **BaseViewModel effects**: `receiveAsFlow()` directly, no `shareIn`
- **AppPreferences**: Interface + impl both in composeApp (always app-specific)

---

## Target Architecture

### Gradle Modules (3 + app)
```
kmp-scaffold/
├── architecture/         # BaseViewModel, MVI contracts, ViewEffect
├── theme/                # AppTheme, color tokens, typography
├── core/                 # Storage, Network, Connectivity, Analytics (as packages)
├── composeApp/           # Thin app shell — wires modules together
└── iosApp/               # iOS native wrapper
```

### What goes WHERE

| Module | What it contains |
|---|---|
| `:architecture` | `BaseViewModel<Event, State>`, `ViewEvent`, `ViewState`, `ViewEffect`, built-in effects (Snackbar, Haptic, NavigateBack), `DispatcherProvider` interface |
| `:theme` | `AppTheme()` composable, `@Immutable AppColors` data class (generic semantic tokens only), `LocalAppColors`, `MaterialTheme.appColors` extension, light/dark schemes, typography, shapes |
| `:core` | **Packages**: `core.storage` (DataStore factory, platform paths, Koin module), `core.network` (HttpClient expect/actual, base config, platform engines, `AppResult` sealed interface, `safeApiCall`), `core.connectivity` (NetworkConnectivity interface + platform impls), `core.analytics` (AnalyticsProvider interface, CompositeAnalytics, `ConsentProvider` interface), `core.logging` (Napier/Kermit integration) |
| `composeApp` | `AppPreferences` interface + impl (app-specific keys), `AppAnalyticsManager` (app-specific events), navigation routes + NavGraph, feature screens, DI wiring |

### :core internal package structure
```
core/src/commonMain/kotlin/com/bearminds/core/
├── storage/
│   ├── createDataStore.kt           # Singleton factory with SynchronizedObject lock
│   ├── storageModule.kt             # Koin module providing DataStore<Preferences>
│   └── platformStorageModule.kt     # expect val platformStorageModule
├── network/
│   ├── networkModule.kt             # Koin module providing HttpClient
│   ├── networkPlatform.kt           # expect fun provideHttpClient()
│   ├── AppResult.kt                 # sealed interface: Success, Error
│   └── SafeApiCall.kt               # safeApiCall wrapper
├── connectivity/
│   ├── NetworkConnectivity.kt       # Interface: isOnline Flow, start/stopMonitoring
│   ├── connectivityModule.kt        # Koin module with includes(platformConnectivityModule)
│   └── platformConnectivityModule.kt # expect val
├── analytics/
│   ├── AnalyticsProvider.kt         # Interface: logEvent, setUserProperty, setCollectionEnabled
│   ├── CompositeAnalytics.kt        # Multi-provider composite
│   ├── ConsentProvider.kt           # Interface (decouples from storage)
│   ├── analyticsModule.kt           # Koin module with consent flow
│   └── platformAnalytics.kt         # expect fun createPlatformAnalyticsProviders()
└── logging/
    └── Logger.kt                    # Napier/Kermit wrapper or expect/actual
```

Platform source sets in `:core`:
```
core/src/androidMain/.../
├── storage/createDataStore.android.kt       # context.filesDir
├── storage/platformStorageModule.android.kt # androidContext() Koin
├── network/HttpClient.android.kt            # OkHttp engine
├── connectivity/AndroidNetworkConnectivity.kt # ConnectivityManager.NetworkCallback
└── analytics/platformAnalytics.android.kt   # Stub/Firebase provider

core/src/iosMain/.../
├── storage/createDataStore.ios.kt           # NSDocumentDirectory
├── storage/platformStorageModule.ios.kt
├── network/HttpClient.ios.kt                # Darwin engine
├── connectivity/IosNetworkConnectivity.kt   # NWPathMonitor
└── analytics/platformAnalytics.ios.kt

core/src/jvmMain/.../
├── storage/createDataStore.jvm.kt           # OS-aware paths (Win/Mac/Linux)
├── storage/platformStorageModule.jvm.kt
├── network/HttpClient.jvm.kt                # CIO engine
├── connectivity/JvmNetworkConnectivity.kt   # HTTP HEAD polling (5s)
└── analytics/platformAnalytics.jvm.kt
```

### composeApp internal structure
```
composeApp/src/commonMain/kotlin/com/bearminds/scaffold/
├── di/AppModule.kt                  # Composes all modules + app singletons
├── navigation/
│   ├── NavigationRoutes.kt          # Sealed interface with @Serializable routes
│   ├── MainNavGraph.kt              # NavHost with per-route animations
│   └── NavigationAnimations.kt      # Reusable transition helpers
├── storage/
│   ├── AppPreferences.kt            # Interface (app-specific keys)
│   └── AppPreferencesImpl.kt        # Implementation
├── analytics/
│   └── AppAnalyticsManager.kt       # App-specific events and user properties
├── root/                            # Root screen + ViewModel
├── theme/                           # ThemeSwitcher ViewModel + contract
└── views/                           # App-specific shared UI components
```

### Module Dependency Graph
```
composeApp
  +-- :architecture  (no deps on other modules)
  +-- :theme         (no deps on other modules)
  +-- :core          (no deps on other modules; analytics uses ConsentProvider interface, not :storage)
```

---

## Changes

### Phase 1: Gradle & Build Config

#### 1.1 Update `settings.gradle.kts`
- Rename root project "Passkeep" → "KmpScaffold"
- Add foojay-resolver-convention plugin
- Add `mavenContent` filtering for Google repository
- Include modules: `:architecture`, `:theme`, `:core`

#### 1.2 Update root `build.gradle.kts`
- Add plugins with `apply false`: serialization, ksp, detekt

#### 1.3 Update `gradle.properties`
- JVM memory: `org.gradle.jvmargs=-Xmx6g -XX:MaxMetaspaceSize=1g -XX:+UseParallelGC`
- Add `org.jetbrains.compose.experimental.macos.enabled=true`
- Add `VERSION_NAME=1.0.0` and `ANDROID_VERSION_CODE=1`

#### 1.4 Update `gradle/libs.versions.toml`
- Add `app-versionName` and `app-versionCode`
- Add Mokkery, kotlinx-coroutines-test
- Add detekt plugin, Napier/Kermit
- Replace PreCompose with `org.jetbrains.androidx.navigation:navigation-compose` (JetBrains fork!)
- Add Ktor logging plugin

#### 1.5 Update `composeApp/build.gradle.kts`
- JVM toolchain 11 → 21 (keep Android compileOptions at JVM 11 for compatibility)
- Add `linkerOpts("-lsqlite3")` to iOS frameworks
- Add `ExperimentalForeignApi` opt-in for iOS source sets
- Add dependencies on `:architecture`, `:theme`, `:core`
- Add signing config from local.properties
- Remove PreCompose dependency

---

### Phase 2: `:architecture` Module
**Source**: Passkeep/KMP/architecture

Files:
- `architecture/build.gradle.kts` — KMP library (Android, iOS, JVM)
- `architecture/src/commonMain/kotlin/com/bearminds/architecture/BaseViewModel.kt`
- `architecture/src/commonMain/kotlin/com/bearminds/architecture/DispatcherProvider.kt`

BaseViewModel:
- Generic `<Event : ViewEvent, UIState : ViewState>`
- `Channel<Event>` with `conflate()` for event processing
- Non-nullable `StateFlow<UIState>` via `MutableStateFlow(initialState)` with `by lazy`
- `Channel<ViewEffect>` → exposed as `receiveAsFlow()` directly (NO `shareIn`)
- `setState(reducer)`, `setEffect(builder)`, `onEvent(event)` API
- Built-in effects: `SnackbarEffect`, `HapticFeedbackEffect`, `NavigateBackEffect`

DispatcherProvider:
- Interface with `main`, `io`, `default`, `unconfined`
- Default implementation using `Dispatchers.*`
- Injected via Koin for testability

---

### Phase 3: `:theme` Module
**Source**: Passkeep/KMP/theme (simplified — NO tacker-specific tokens)

Files:
- `theme/build.gradle.kts` — KMP library with Compose
- `theme/src/commonMain/kotlin/com/bearminds/theme/AppTheme.kt`
- `theme/src/commonMain/kotlin/com/bearminds/theme/Color.kt`
- `theme/src/commonMain/kotlin/com/bearminds/theme/Typography.kt`

Generic semantic tokens only:
- `@Immutable data class AppColors(background, surface, primary, secondary, error, onPrimary, onSurface, textPrimary, textSecondary, textDisabled, divider, ...)`
- `LocalAppColors` CompositionLocal
- `MaterialTheme.appColors` extension property
- Light + dark schemes
- Typography with placeholder values
- No glassmorphism, no paywall gradients, no category colors

---

### Phase 4: `:core` Module
**Source**: All 5 projects

#### 4.1 Storage package
- `createDataStore()` singleton factory with `SynchronizedObject` lock
- Platform `expect/actual` for file paths (Android: `context.filesDir`, iOS: `NSDocumentDirectory`, JVM: OS-aware)
- Koin module providing `DataStore<Preferences>` with `includes(platformStorageModule)`

#### 4.2 Network package
- `expect fun provideHttpClient(): HttpClient` with platform engines (OkHttp/Darwin/CIO)
- Base config: `ContentNegotiation` (JSON), `HttpTimeout` (30s request, 15s connect), `Logging` (HEADERS)
- `sealed interface AppResult<out T>` — `Success(data: T)`, `Error(exception: Throwable, message: String?)`
- `suspend fun <T> safeApiCall(block: suspend () -> T): AppResult<T>` wrapper
- Koin module providing `HttpClient`

#### 4.3 Connectivity package
- `NetworkConnectivity` interface: `isOnline: Flow<Boolean?>`, `startMonitoring()`, `stopMonitoring()`
- Android: `ConnectivityManager.NetworkCallback` with `NET_CAPABILITY_INTERNET` + `NET_CAPABILITY_VALIDATED`
- iOS: `NWPathMonitor` on dispatch queue
- JVM: HTTP HEAD polling (5s interval)
- Koin module with `expect val platformConnectivityModule`

#### 4.4 Analytics package
- `AnalyticsProvider` interface: `logEvent()`, `setUserProperty()`, `setCollectionEnabled()`
- `CompositeAnalytics` — forwards to all registered providers
- `ConsentProvider` interface (single `isEnabled: Flow<Boolean>`) — decoupled from storage
- Koin module observing consent flow
- Platform: stub/no-op providers (real Firebase added per-app)

#### 4.5 Logging package
- Napier or Kermit integration (decide during implementation)
- Simple API: `logD()`, `logE()`, `logW()`, etc.

---

### Phase 5: Navigation Upgrade (in composeApp)
**Source**: tacker

- `NavigationRoutes.kt` — Sealed interface with `@Serializable` routes
- `MainNavGraph.kt` — NavHost with per-route enter/exit animations
- `NavigationAnimations.kt` — Reusable `slideInHorizontally`/`fadeOut` helpers
- Remove all PreCompose references
- **IMPORTANT**: Use `org.jetbrains.androidx.navigation:navigation-compose`, NOT Google's `androidx.navigation:navigation-compose`

```kotlin
sealed interface NavigationRoute {
    @Serializable data object Home : NavigationRoute
    @Serializable data object Settings : NavigationRoute
    @Serializable data class Detail(val id: String) : NavigationRoute
}
```

---

### Phase 6: App-Specific Implementations (in composeApp)

#### 6.1 AppPreferences (interface + impl, both in composeApp)
```kotlin
interface AppPreferences {
    val themeFlow: Flow<String>
    val languageFlow: Flow<String>
    val isOnboardingCompletedFlow: Flow<Boolean>
    val isAnalyticsEnabledFlow: Flow<Boolean>
    suspend fun setTheme(theme: String)
    suspend fun setLanguage(language: String)
    suspend fun setOnboardingCompleted(completed: Boolean)
    suspend fun setAnalyticsEnabled(enabled: Boolean)
}
```
`AppPreferencesImpl` wraps `DataStore<Preferences>` from `:core`.
Also implements `ConsentProvider` from `:core` (bridges analytics consent).

#### 6.2 DI Wiring
```kotlin
val appModule = module {
    includes(storageModule, networkModule, connectivityModule, analyticsModule)
    single(named("applicationScope")) { CoroutineScope(SupervisorJob() + Dispatchers.Main) }
    single<AppPreferences> { AppPreferencesImpl(get()) }
    single<ConsentProvider> { get<AppPreferences>() as ConsentProvider }
    // feature ViewModels...
}
```

---

### Phase 7: Example Test
- `composeApp/src/commonTest/kotlin/.../ExampleViewModelTest.kt`
- Demonstrates: creating ViewModel, sending events, asserting state changes via Turbine or manual Flow collection
- Shows DispatcherProvider injection for test dispatchers

---

### Phase 8: Build Config & Quality (Lower priority)

- 8.1 Add ProGuard rules (`composeApp/proguard-rules.pro`)
- 8.2 Add detekt config (`config/detekt/detekt.yml`)
- 8.3 Add custom lint tasks (CheckHardcodedColors, CheckHardcodedTypography)
- 8.4 Add CI scripts (`CI/bump-android-version.sh`, `CI/bump-ios-version.sh`)
- 8.5 Add GitHub Actions (`claude-code-review.yml`)
- 8.6 Add AGENTS.md

---

## Verification
1. `./gradlew :architecture:compileKotlinJvm` — Architecture module compiles
2. `./gradlew :theme:compileKotlinJvm` — Theme module compiles
3. `./gradlew :core:compileKotlinJvm` — Core module compiles
4. `./gradlew :composeApp:assembleDebug` — Android builds
5. `./gradlew :composeApp:jvmJar` — Desktop builds
6. `./gradlew :composeApp:jvmTest` — Example test passes
7. `./gradlew check` — All checks pass
8. Run desktop app — verify navigation and theme switching work

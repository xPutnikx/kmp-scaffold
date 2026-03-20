# AGENTS.md — KMP Scaffold

## Project Type
Kotlin Multiplatform (KMP) with Compose Multiplatform. Targets: Android, iOS, JVM Desktop.

## Architecture
- **MVI** pattern via `BaseViewModel<Event, State>` from `:architecture` module
- **Koin** for dependency injection — feature modules composed into `appModule`
- **AndroidX Navigation Compose** (JetBrains fork) with type-safe `@Serializable` sealed routes
- **Material3** theming via `:theme` module with custom `AppColors` tokens

## Module Structure
```
:architecture  — BaseViewModel, DispatcherProvider (no deps on other modules)
:theme         — AppTheme, colors, typography (no deps on other modules)
:core          — Storage, Network, Connectivity, Analytics, Logging (no deps on other modules)
:composeApp    — App shell, wires modules, app-specific implementations
```

## Key Patterns
- **Storage**: `createDataStore()` factory in `:core`, `AppPreferences` interface + impl in `composeApp`
- **Network**: `expect fun provideHttpClient()` in `:core` with platform engines (OkHttp/Darwin/CIO)
- **Connectivity**: `NetworkConnectivity` interface in `:core` with platform implementations
- **Analytics**: `AnalyticsProvider` interface + `CompositeAnalytics` in `:core`, `ConsentProvider` decouples from storage
- **Navigation**: Sealed interface routes with `@Serializable`, per-route animations

## Rules
- Use `MaterialTheme.appColors` for all colors — never hardcode Color values
- Use `MaterialTheme.typography` for all text styles — never hardcode fontSize
- All ViewModels must extend `BaseViewModel` and use MVI contracts
- Platform code goes in `expect/actual` declarations, not `#ifdef`-style checks
- Koin modules are self-contained per feature with `includes()` for composition

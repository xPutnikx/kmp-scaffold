# KMP Scaffold

A production-ready Kotlin Multiplatform scaffold with modular architecture extracted from real production apps. Pre-configured with MVI, dependency injection, navigation, theming, storage, networking, connectivity monitoring, and analytics.

## Architecture

```
:architecture   — BaseViewModel (MVI), ComposableData, RootScreen, DispatcherProvider
:theme          — AppTheme, semantic color tokens, typography
:core           — Storage, Network, Connectivity, Analytics, Logging (as packages)
:composeApp     — App shell, wires modules, app-specific implementations
```

### Key Patterns

- **MVI** via `BaseViewModel<Event, State>` with Channel-based event processing
- **ComposableData + DataBuilder** — data objects that render themselves, built by DataBuilder classes
- **Contract pattern** — separate `State`, `Event`, `Effect` per screen
- **RootScreen** composable with built-in Scaffold, snackbar, and haptic effect processing
- **AndroidX Navigation Compose** (JetBrains fork) with `@Serializable` sealed routes

## Platform Support

- Android (API 24+)
- iOS (arm64, simulator)
- Desktop/JVM (macOS, Windows, Linux)

## Tech Stack

| Category | Technology |
|----------|-----------|
| UI | Compose Multiplatform 1.9.1 |
| Language | Kotlin 2.2.20 |
| DI | Koin 4.1.1 |
| Navigation | AndroidX Navigation Compose |
| Storage | DataStore 1.1.7 |
| Database | SQLDelight 2.1.0 |
| Networking | Ktor 3.3.0 |
| Logging | Napier 2.7.1 |

## Quick Start

```bash
# Android
./gradlew :composeApp:assembleDebug

# Desktop
./gradlew :composeApp:run

# iOS — open iosApp/iosApp.xcodeproj in Xcode

# Tests
./gradlew :composeApp:jvmTest
```

## Customization

1. Replace `com.bearminds.scaffold` with your package name
2. Customize colors in `theme/Color.kt`
3. Add routes in `navigation/NavigationRoutes.kt`
4. Add screens using the Contract + DataBuilder pattern

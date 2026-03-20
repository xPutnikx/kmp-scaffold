# Kotlin Multiplatform Template

A production-ready Kotlin Multiplatform (KMP) template with pre-configured architecture, dependency injection, navigation, theming, and storage. Perfect for building cross-platform mobile and desktop applications.

## 🚀 Features

### ✅ Complete Architecture Setup
- **Koin Dependency Injection** - Modular DI with platform-specific modules
- **PreCompose Navigation** - Type-safe navigation system
- **Material3 Theming** - Light/Dark mode with customizable colors
- **DataStore Preferences** - Cross-platform key-value storage
- **Ktor HTTP Client** - Platform-specific network layer
- **SQLDelight** - Type-safe SQL database (configured but not used by default)
- **BaseViewModel Pattern** - State management with sealed classes

### 📱 Platform Support
- ✅ **Android** (API 24+)
- ✅ **iOS** (arm64, simulator)
- ✅ **Desktop/JVM** (macOS, Windows, Linux)
- ❌ Web (removed for performance, can be re-added)

### 🏗️ Project Structure

```
composeApp/src/
├── commonMain/
│   ├── kotlin/com/bearminds/passkeep/
│   │   ├── di/                    # Dependency injection modules
│   │   ├── storage/               # DataStore preferences
│   │   ├── network/               # HTTP client setup
│   │   ├── theme/                 # Material3 theming
│   │   │   └── switcher/          # Theme switcher feature
│   │   ├── compose/navigation/    # Navigation routes
│   │   ├── root/                  # Root screen
│   │   └── views/                 # Base classes (BaseViewModel)
│   └── composeResources/          # Shared resources (fonts, images, strings)
├── androidMain/
│   ├── kotlin/                    # Android-specific code
│   └── AndroidManifest.xml        # Android configuration
├── iosMain/
│   └── kotlin/                    # iOS-specific code
└── jvmMain/
    └── kotlin/                    # Desktop-specific code
```

## 🛠️ Tech Stack

| Category | Technology |
|----------|-----------|
| **UI Framework** | Compose Multiplatform 1.9.1 |
| **Language** | Kotlin 2.2.20 |
| **Dependency Injection** | Koin 4.1.1 |
| **Navigation** | PreCompose 1.6.2 |
| **Storage** | DataStore 1.1.7 |
| **Database** | SQLDelight 2.1.0 |
| **Networking** | Ktor 3.3.0 |
| **Serialization** | Kotlinx Serialization 1.9.0 |
| **Coroutines** | Kotlinx Coroutines 1.10.2 |

## 🎯 Quick Start

### Prerequisites

- **JDK 11+** (recommended: JDK 17 or 21)
- **Android Studio** (latest version) or **IntelliJ IDEA**
- **Xcode 15+** (for iOS development, macOS only)
- **Kotlin Multiplatform Mobile Plugin** (Android Studio)

### 1️⃣ Use This Template

1. Click **"Use this template"** on GitHub
2. Clone your new repository
3. Follow the [TEMPLATE_SETUP.md](TEMPLATE_SETUP.md) guide to customize package names

### 2️⃣ Build & Run

#### Android
```bash
./gradlew :composeApp:assembleDebug
# Or run from Android Studio
```

#### iOS
```bash
# Open iosApp/iosApp.xcodeproj in Xcode
# Or run from Android Studio with KMM plugin
```

#### Desktop (JVM)
```bash
./gradlew :composeApp:run
```

## 📚 Documentation

- **[TEMPLATE_SETUP.md](TEMPLATE_SETUP.md)** - How to customize this template for your project
- **[ARCHITECTURE.md](ARCHITECTURE.md)** - Architecture decisions and patterns
- **[CONTRIBUTING.md](CONTRIBUTING.md)** - Contribution guidelines

## 🎨 Customization

### Package Names
Replace `com.bearminds.passkeep` with your package name:
1. See detailed instructions in [TEMPLATE_SETUP.md](TEMPLATE_SETUP.md)
2. Or use the provided rename script (coming soon)

### Theming
Customize colors in:
- `theme/Color.kt` - Color definitions
- `theme/Theme.kt` - Light/Dark color schemes
- `theme/AppTheme.kt` - Typography and shapes

### Navigation Routes
Add your screens in:
- `compose/navigation/NavigationData.kt` - Define routes
- `App.kt` - Add navigation scenes

## 🗂️ Key Files

| File | Purpose |
|------|---------|
| `di/AppModule.kt` | Root DI module |
| `storage/AppPreferences.kt` | Preferences interface |
| `theme/AppTheme.kt` | Main theme composable |
| `App.kt` | Navigation & app entry point |
| `root/RootScreenDestination.kt` | Root screen template |

## 🧪 Testing

```bash
# Run all tests
./gradlew test

# Android instrumented tests
./gradlew connectedAndroidTest

# iOS tests (requires macOS + Xcode)
./gradlew iosSimulatorArm64Test
```

## 📦 Build Variants

```bash
# Debug builds
./gradlew assembleDebug

# Release builds (configure signing first)
./gradlew assembleRelease

# Desktop distribution
./gradlew packageDistributionForCurrentOS
```

## 🤝 Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) first.

## 📄 License

This template is available under the [MIT License](LICENSE).

## 🙏 Acknowledgments

Architecture inspired by:
- [Official KMP Samples](https://github.com/JetBrains/compose-multiplatform)
- [Audiobookify](https://github.com/yourusername/abookify) - Reference architecture
- [Koin Documentation](https://insert-koin.io/)
- [PreCompose](https://github.com/Tlaster/PreCompose)

## 🐛 Known Issues

- None currently

## 🗺️ Roadmap

- [ ] Add automated package renaming script
- [ ] Add more example screens
- [ ] Add unit test examples
- [ ] Add CI/CD templates
- [ ] Add localization example

## 📞 Support

- [Issues](https://github.com/yourusername/kmp-template/issues)
- [Discussions](https://github.com/yourusername/kmp-template/discussions)

---

Made with ❤️ using Kotlin Multiplatform

# ProGuard rules for KMP Scaffold Android app

# ============================================================================
# Kotlin Serialization
# ============================================================================
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class com.bearminds.scaffold.**$$serializer { *; }
-keepclassmembers class com.bearminds.scaffold.** {
    *** Companion;
}
-keepclasseswithmembers class com.bearminds.scaffold.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# ============================================================================
# Compose
# ============================================================================
-dontwarn androidx.compose.**
-keepclassmembers class androidx.compose.ui.platform.** { *; }

# ============================================================================
# Koin Dependency Injection
# ============================================================================
-keep class org.koin.** { *; }
-keepclassmembers class * {
    @org.koin.core.annotation.* <methods>;
}

# Keep ViewModel classes for Koin injection
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keep class com.bearminds.scaffold.**ViewModel { *; }
-keep class com.bearminds.scaffold.**Contract { *; }
-keep class com.bearminds.scaffold.**Contract$* { *; }

# Keep all interfaces used by Koin DI
-keep interface com.bearminds.scaffold.** { *; }
-keep interface com.bearminds.core.** { *; }
-keep interface com.bearminds.architecture.** { *; }

# ============================================================================
# Ktor
# ============================================================================
-dontwarn io.ktor.**
-keep class io.ktor.** { *; }

# ============================================================================
# Coroutines
# ============================================================================
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory { *; }

# ============================================================================
# DataStore
# ============================================================================
-keepclassmembers class * extends com.google.protobuf.GeneratedMessageLite {
    <fields>;
}

# ============================================================================
# Okio
# ============================================================================
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okio.**

# ============================================================================
# Navigation
# ============================================================================
-keepnames class * implements android.os.Parcelable
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# ============================================================================
# General
# ============================================================================
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclasseswithmembers class * {
    native <methods>;
}

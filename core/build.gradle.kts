plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    jvmToolchain(21)

    jvm()

    androidTarget()

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "coreKit"
            isStatic = true
        }
    }

    sourceSets {
        val iosMain by creating {
            dependsOn(commonMain.get())
        }

        iosArm64Main {
            dependsOn(iosMain)
        }

        iosSimulatorArm64Main {
            dependsOn(iosMain)
        }

        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.json)

                // DataStore
                api(libs.androidx.datastore.preferences.core)
                api(libs.androidx.datastore.core.okio)

                // Koin
                api(libs.koin.core)

                // Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.json)
                implementation(libs.ktor.client.logging)

                // Okio (for DataStore paths)
                implementation(libs.okio)

                // Logging
                api(libs.napier)

                // AtomicFU (for DataStore lock)
                implementation(libs.kotlinx.atomicfu)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.koin.android)
                implementation(libs.ktor.client.okhttp)
            }
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        jvmMain {
            dependencies {
                implementation(libs.ktor.client.cio)
                implementation(libs.koin.core.jvm)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }

    sourceSets.named { it.lowercase().let { n -> n.startsWith("ios") } }
        .configureEach {
            languageSettings {
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
            }
        }
}

android {
    namespace = "com.bearminds.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

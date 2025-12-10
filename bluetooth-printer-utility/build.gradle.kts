plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("maven-publish")
}

group = "com.github.paragonaccount"
version = "1.0.2"

android {
    namespace = "com.paragon.bluetooth_printer_utility"
    compileSdk = 34

    defaultConfig {
        minSdk = 27
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(files("libs/ZSDK_ANDROID_API.jar"))
}

publishing {
    publications {
        afterEvaluate {
            android.libraryVariants.forEach { variant ->
                create<MavenPublication>(variant.name) {
                    groupId = project.group.toString()
                    artifactId = "bluetooth-printer-utility"
                    version = project.version.toString()
                    from(components.findByName(variant.name))
                }
            }
        }
    }
}
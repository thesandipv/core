plugins {
    id("com.android.library")
    id("kotlin-android")
}

// Libs
val kotlinVersion = "2.1.10"
// Configs
val major = 0
val minor = 6
val patch = 0
val myVersion by extra { "$major.$minor.$patch" }

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.afterroot.utils"

    compileSdk = 35

    defaultConfig {
        minSdk = 23
        resourceConfigurations.addAll(listOf("en"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.fragment:fragment-ktx:1.8.5")
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("androidx.transition:transition:1.5.1")
    implementation("com.afollestad.material-dialogs:core:3.3.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.jakewharton.timber:timber:5.0.1")
}

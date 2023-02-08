plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.dokka") version "1.7.10"
    id("maven-publish")
}

//Libs
val kotlinVersion = "1.7.21"
//Configs
val major = 0
val minor = 5
val patch = 0
val myVersion by extra { "${major}.${minor}.${patch}" }

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 16
        targetSdk = 33
        resourceConfigurations.addAll(listOf("en"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.dokkaHtml.configure {
    dokkaSourceSets {
        named("main") {
            noAndroidSdkLink.set(false)
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.fragment:fragment-ktx:1.5.5")
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("androidx.transition:transition:1.4.1")
    implementation("com.afollestad.material-dialogs:core:3.3.0")
    implementation("com.google.android.material:material:1.8.0-beta01")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.jakewharton.timber:timber:5.0.1")
}
apply(from = "publish.gradle")

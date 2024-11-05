plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")
  id("org.jetbrains.kotlin.plugin.compose")
  id("dagger.hilt.android.plugin")
  kotlin("plugin.serialization")
}

android {
  namespace = "com.teixeira0x.squareclient"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.teixeira0x.squareclient"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    vectorDrawables.useSupportLibrary = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
  }

  flavorDimensions("env")

  productFlavors {
    create("production") {
      buildConfigField(
        "String",
        "BASE_URL",
        "\"https://api.squarecloud.app/v2/\"",
      )
    }
  }

  buildFeatures {
    buildConfig = true
    compose = true
  }
  composeOptions { kotlinCompilerExtensionVersion = "1.3.2" }
  packaging { resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}") }
}

kapt { correctErrorTypes = true }

dependencies {
  implementation("androidx.appcompat:appcompat:1.7.0")
  implementation("androidx.core:core-ktx:1.13.1")
  implementation("androidx.datastore:datastore-preferences:1.1.1")

  // Jetpack Compose
  implementation(platform("androidx.compose:compose-bom:2024.09.03"))
  implementation("androidx.activity:activity-compose:1.5.1")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.ui:ui")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.material3:material3")
  debugImplementation("androidx.compose.ui:ui-tooling")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
  implementation("androidx.navigation:navigation-compose:2.8.2")
  implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

  // Google
  implementation("com.google.android.material:material:1.12.0")
  implementation("com.google.code.gson:gson:2.10.1")
  implementation("com.google.dagger:hilt-android:2.52")
  kapt("com.google.dagger:hilt-compiler:2.52")

  // Network
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")

  // Kotlin
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

  // Test
  testImplementation("junit:junit:4.13.2")
}

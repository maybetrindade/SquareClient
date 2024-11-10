import com.teixeira0x.squareclient.build.BuildConfig

plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")
  id("org.jetbrains.kotlin.plugin.compose")
  id("dagger.hilt.android.plugin")
}

android {
  namespace = BuildConfig.packageName

  defaultConfig {
    applicationId = BuildConfig.packageName
    vectorDrawables.useSupportLibrary = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  buildTypes {
    release {
      isMinifyEnabled = false
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

  packaging { resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}") }
}

kapt { correctErrorTypes = true }

dependencies {
  // AndroidX
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.datastore.prefs)

  // Jetpack Compose
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.activity)
  implementation(libs.androidx.compose.ui)
  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.viewmodel)
  implementation(libs.androidx.compose.navigation)
  implementation(libs.androidx.compose.hilt)

  // Google
  implementation(libs.google.material)
  implementation(libs.google.gson)
  implementation(libs.google.hilt)
  kapt(libs.google.hilt.compiler)

  // Network
  implementation(libs.network.retrofit)
  implementation(libs.network.retrofit.gson)
  implementation(libs.network.okhttp)
  implementation(libs.network.okhttp.logging)

  // Kotlin
  implementation(libs.kotlin.coroutines.android)

  // Test
  testImplementation(libs.tests.junit)
}

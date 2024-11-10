import com.android.build.gradle.BaseExtension
import com.teixeira0x.squareclient.build.BuildConfig
import com.teixeira0x.squareclient.build.VersionUtils
import com.teixeira0x.squareclient.plugin.SquareClientPlugin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all
// sub-projects/modules.
plugins {
  id("build-logic.root-project")
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.compose) apply false
  alias(libs.plugins.dagger.hilt.android) apply false
}

fun Project.configureBaseExtension() {
  extensions.findByType(BaseExtension::class)?.run {
    compileSdkVersion(BuildConfig.compileSdk)
    // buildToolsVersion = BuildConfig.buildTools

    defaultConfig {
      minSdk = BuildConfig.minSdk
      targetSdk = BuildConfig.targetSdk
      versionCode = VersionUtils.versionCode
      versionName = VersionUtils.versionName
    }

    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
    }
  }
}

subprojects {
  plugins.withId("com.android.application") { configureBaseExtension() }
  plugins.withId("com.android.library") { configureBaseExtension() }

  afterEvaluate { apply { plugin(SquareClientPlugin::class.java) } }

  tasks.withType<KotlinCompile>().configureEach {
    compilerOptions { jvmTarget = JvmTarget.JVM_17 }
  }
}

tasks.register<Delete>("clean") { delete(rootProject.layout.buildDirectory) }

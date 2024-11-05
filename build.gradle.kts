import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all
// sub-projects/modules.
plugins {
  id("com.android.application") version "8.7.1" apply false
  id("com.android.library") version "8.7.1" apply false
  id("org.jetbrains.kotlin.android") version "2.0.21" apply false
  id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
  id("com.google.dagger.hilt.android") version "2.52" apply false
  kotlin("plugin.serialization") version "2.0.20" apply false
}

subprojects {
  tasks.withType<KotlinCompile>().configureEach {
    compilerOptions { jvmTarget = JvmTarget.JVM_17 }
  }
}

tasks.register<Delete>("clean") { delete(rootProject.layout.buildDirectory) }

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins { `kotlin-dsl` }

repositories {
  google()
  gradlePluginPortal()
  mavenCentral()
}

tasks.withType<KotlinCompile>().configureEach {
  compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
}

dependencies {
  implementation("com.android.tools.build:gradle:${libs.versions.agp.get()}")
}

gradlePlugin {
  plugins {
    create("com.teixeira0x.squareclient-plugin") {
      id = "com.teixeira0x.squareclient-plugin"
      implementationClass =
        "com.teixeira0x.squareclient.plugin.SquareClientPlugin"
    }
  }
}

plugins { alias(libs.plugins.kotlin.jvm) apply false }

subprojects {
  plugins.withId("java-library") {
    extensions.getByType(JavaPluginExtension::class.java).apply {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
    }
  }
}

package com.teixeira0x.squareclient.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SquareClientPlugin : Plugin<Project> {
  override fun apply(target: Project) =
    target.run {
      if (plugins.hasPlugin("com.android.application")) {
        // setup signing configuration
        plugins.apply(SigningConfigPlugin::class.java)
      }
    }
}

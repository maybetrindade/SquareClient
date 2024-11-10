package com.teixeira0x.squareclient.build

import org.gradle.api.Project
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Provider

const val KEY_BIN = "APP_SIGNING_BIN"
const val KEY_ALIAS = "APP_SIGNING_ALIAS"
const val KEY_PASS = "APP_SIGNING_KEY_PASS"
const val KEY_STORE_PASS = "APP_SIGNING_STORE_PASS"

val Project.signingKey: Provider<RegularFile>
  get() = rootProject.layout.buildDirectory.file("signing/signing-key.jks")

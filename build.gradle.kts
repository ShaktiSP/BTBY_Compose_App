// Top-level build file where you can add configuration options common to all sub-projects/modules.

// For KAPT
//buildscript {
//    dependencies {
//        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48")
//    }
//}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}
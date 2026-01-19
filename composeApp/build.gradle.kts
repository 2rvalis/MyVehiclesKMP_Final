import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.googleKsp)
    id("androidx.room") version "2.7.0-alpha11"
}

kotlin {
    jvmToolchain(17)

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    // iOS Targets
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            // Σύνδεση με την SQLite του συστήματος iOS
            linkerOpts("-lsqlite3")
            freeCompilerArgs += "-Xruntime-logs=gc=info"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.1")

            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Room Dependencies
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

            implementation(libs.coil.compose)
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha10")
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.core.ktx)
        }

        // Καθαρή δημιουργία του iosMain χωρίς χειροκίνητα srcDirs
        val iosMain by creating {
            dependsOn(commonMain.get())
        }

        // Σύνδεση των συγκεκριμένων targets με το iosMain
        iosX64Main.get().dependsOn(iosMain)
        iosArm64Main.get().dependsOn(iosMain)
        iosSimulatorArm64Main.get().dependsOn(iosMain)
    }

    compilerOptions {
        // Απαραίτητο για το expect/actual της Room
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

android {
    namespace = "com.example.myvehicles"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.myvehicles"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

room {
    schemaDirectory("$projectDir/schemas")
    generateKotlin = true
}

dependencies {
    debugImplementation(compose.uiTooling)

    // Ρύθμιση του KSP Compiler για όλους τους στόχους
    val roomCompiler = libs.androidx.room.compiler
    add("kspAndroid", roomCompiler)
    add("kspIosX64", roomCompiler)
    add("kspIosArm64", roomCompiler)
    add("kspIosSimulatorArm64", roomCompiler)
}

//for activation
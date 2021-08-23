import config.Libs.androidx
import config.Libs.biometric
import config.Libs.circleImageView
import config.Libs.coroutines
import config.Libs.debugTools
import config.Libs.glide
import config.Libs.installReferrer
import config.Libs.koin
import config.Libs.lifecycle
import config.Libs.materialDesign
import config.Libs.moshi
import config.Libs.navigation
import config.Libs.picasso
import config.Libs.retrofit
import config.Libs.room
import config.Libs.spinKit
import config.Libs.kotlin
import config.TestLibs.androidxTest
import config.TestLibs.coroutinesTest
import config.TestLibs.espresso
import config.TestLibs.json
import config.TestLibs.junit4
import config.TestLibs.koinTest
import config.TestLibs.mockWebServer
import config.TestLibs.mockk
import config.TestLibs.navigationTest
import config.TestLibs.testOrchestrator
import config.TestLibs.truth

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("jacoco")
    id("kotlin-android")
}

jacoco {
    toolVersion = Versioning.JACOCO_VERSION
}

android {
    compileSdkVersion(Versioning.Android.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = config.Android.APPLICATION_ID
        minSdkVersion(Versioning.Android.MIN_SDK_VERSION)
        targetSdkVersion(Versioning.Android.TARGET_SDK_VERSION)
        versionCode = Versioning.Android.APP_VERSION_CODE
        versionName = Versioning.Android.APP_VERSION_NAME

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = config.Android.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArgument("clearPackageData", "true")

        multiDexEnabled = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    flavorDimensions("default")

    productFlavors {
        create("dev") {
            dimension = "default"
            applicationIdSuffix = ".dev"
            versionNameSuffix = ".dev"
            buildConfigField("String", "BASE_URL", Environment.BASE_URL)
        }
        create("prod") {
            dimension = "default"
        }
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        exclude("META-INF/LICENSE*")
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    lintOptions {
        isCheckReleaseBuilds = false
        isQuiet = true
        isAbortOnError = true
        disable("MissingTranslation")
        baseline(file("${rootProject.projectDir}/config/lint/lint-baseline.xml"))
    }
}

tasks {
    withType<Test> {
        configure<JacocoTaskExtension> {
            isIncludeNoLocationClasses = true
        }
    }
}

dependencies {
    kotlin()
    materialDesign()
    biometric()
    picasso()
    glide()
    circleImageView()
    spinKit()
    installReferrer()
    debugTools()
    junit4()
    json()
    truth()
    testOrchestrator()
    androidx()
    lifecycle()
    room()
    koin()
    coroutines()
    navigation()
    retrofit()
    moshi()
    espresso()
    mockk()
    androidxTest()
    navigationTest()
    koinTest()
    coroutinesTest()
    mockWebServer()

    testImplementation ("com.github.marcinOz:TestCoroutineRule:1.0.1")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

}

package config

import org.gradle.api.artifacts.dsl.DependencyHandler

object TestLibs {
    const val JUNIT4 = "junit:junit:${Versioning.JUNIT_VERSION}"
    private const val KOIN_TEST = "org.koin:koin-test:${Versioning.KOIN_VERSION}"
    private const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versioning.COROUTINES_VERSION}"
    private const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versioning.ESPRESSO_VERSION}"
    private const val ESPRESSO_CONTRIB = "androidx.test.espresso:espresso-contrib:${Versioning.ESPRESSO_VERSION}"
    private const val ESPRESSO_ACCESSIBILITY = "androidx.test.espresso:espresso-accessibility:${Versioning.ESPRESSO_VERSION}"
    private const val ESPRESSO_INTENTS = "androidx.test.espresso:espresso-intents:${Versioning.ESPRESSO_VERSION}"
    private const val MOCKK = "io.mockk:mockk:${Versioning.MOCKK_VERSION}"
    private const val MOCKK_ANDROID = "io.mockk:mockk-android:${Versioning.MOCKK_VERSION}"
    private const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit-ktx:${Versioning.ANDROIDX_TEST_EXT_JUNIT_VERSION}"
    private const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versioning.ANDROID_TEST_VERSION}"
    private const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versioning.ANDROID_TEST_VERSION}"
    private const val ANDROIDX_TEST_CORE = "androidx.test:core-ktx:${Versioning.ANDROID_TEST_VERSION}"
    private const val ANDROIDX_ARCH_CORE_TESTING = "androidx.arch.core:core-testing:${Versioning.ANDROIDX_ARCH_CORE_TESTING}"
    const val ANDROIDX_TEST_ORCHESTRATOR = "androidx.test:orchestrator:${Versioning.ANDROID_TEST_VERSION}"
    private const val ANDROIDX_FRAGMENT_TESTING = "androidx.fragment:fragment-testing:${Versioning.ANDROIDX_FRAGMENT_VERSION}"
    private const val ANDROIDX_NAVIGATION_TESTING = "androidx.navigation:navigation-testing:${Versioning.NAVIGATION_VERSION}"
    private const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versioning.OKHTTP3_LOGGING_INTERCEPTOR_VERSION}"
    const val TRUTH = "com.google.truth:truth:${Versioning.TRUTH_VERSION}"
    const val JSON = "org.json:json:${Versioning.JSON_VERSION}"

    fun DependencyHandler.json() = testImplementation(JSON)

    fun DependencyHandler.testOrchestrator() = androidTestUtil(ANDROIDX_TEST_ORCHESTRATOR)


    fun DependencyHandler.truth() {
        testImplementation(TRUTH)
        androidTestImplementation(TRUTH)
    }

    fun DependencyHandler.navigationTest() =
        androidTestImplementation(ANDROIDX_NAVIGATION_TESTING)

    fun DependencyHandler.koinTest() = androidTestImplementation(KOIN_TEST)

    fun DependencyHandler.coroutinesTest() {
        testImplementation(COROUTINES_TEST)
        androidTestImplementation(COROUTINES_TEST)
    }

    fun DependencyHandler.espresso() {
        androidTestImplementation(ESPRESSO_CORE)
        androidTestImplementation(ESPRESSO_CONTRIB)
        androidTestImplementation(ESPRESSO_ACCESSIBILITY)
        androidTestImplementation(ESPRESSO_INTENTS)
    }

    fun DependencyHandler.mockk() {
        testImplementation(MOCKK)
        androidTestImplementation(MOCKK_ANDROID)
    }

    fun DependencyHandler.mockWebServer() =
        androidTestImplementation(MOCK_WEB_SERVER)

    fun DependencyHandler.androidxTest() {
        testImplementation(ANDROIDX_TEST_EXT_JUNIT)
        androidTestImplementation(ANDROIDX_TEST_EXT_JUNIT)
        androidTestImplementation(ANDROIDX_TEST_RUNNER)
        androidTestImplementation(ANDROIDX_TEST_RULES)
        debugImplementation(ANDROIDX_TEST_CORE)
        debugImplementation(ANDROIDX_ARCH_CORE_TESTING)
        debugImplementation(ANDROIDX_FRAGMENT_TESTING)
    }

    fun DependencyHandler.junit4() = testImplementation(JUNIT4)
}

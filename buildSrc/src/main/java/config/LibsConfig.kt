package config

import org.gradle.api.artifacts.dsl.DependencyHandler

object Libs {
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
    const val CORE_KTX = "androidx.core:core-ktx:${Versioning.CORE_KTX_VERSION}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versioning.CONSTRAINT_LAYOUT_VERSION}"
    const val LEGACY_SUPPORT_V4 = "androidx.legacy:legacy-support-v4:1.0.0"
    const val MULTIDEX = "androidx.multidex:multidex:2.0.1"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:1.1.0"
    const val FRAGMENT = "androidx.fragment:fragment-ktx:${Versioning.ANDROIDX_FRAGMENT_VERSION}"
    private const val BIOMETRIC = "androidx.biometric:biometric:${Versioning.BIOMETRIC_VERSION}"

    private const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versioning.LIFECYCLE_VERSION}"
    private const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Versioning.LIFECYCLE_VERSION}"
    private const val LIFECYCLE_COMMON_JAVA8 = "androidx.lifecycle:lifecycle-common-java8:${Versioning.LIFECYCLE_VERSION}"

    private const val NAVIGATION_FRAGMENT_KTX ="androidx.navigation:navigation-fragment-ktx:${Versioning.NAVIGATION_VERSION}"
    private const val NAVIGATION_UI_KTX =  "androidx.navigation:navigation-ui-ktx:${Versioning.NAVIGATION_VERSION}"

    private const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versioning.ROOM_VERSION}"
    private const val ROOM_COMPILER = "androidx.room:room-compiler:${Versioning.ROOM_VERSION}"
    private const val ROOM_KTX = "androidx.room:room-ktx:${Versioning.ROOM_VERSION}"

    private const val MATERIAL_DESIGN = "com.google.android.material:material:${Versioning.MATERIAL_VERSION}"

    private const val KOTLIN_JAVA8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versioning.KOTLIN_VERSION}"
    private const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versioning.KOTLIN_VERSION}"
    private const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versioning.COROUTINES_VERSION}"

    private const val KOIN_CORE = "org.koin:koin-core:${Versioning.KOIN_VERSION}"
    private const val KOIN_VIEW_MODEL = "org.koin:koin-androidx-viewmodel:${Versioning.KOIN_VERSION}"

    private const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versioning.RETROFIT_VERSION}"
    private const val RETROFIT_CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versioning.RETROFIT_VERSION}"
    private const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versioning.RETROFIT_VERSION}"
    private const val MOSHI = "com.squareup.moshi:moshi:${Versioning.MOSHI_VERSION}"
    private const val MOSHI_KOTLIN =  "com.squareup.moshi:moshi-kotlin:${Versioning.MOSHI_VERSION}"
    private const val OKHTTP3_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versioning.OKHTTP3_LOGGING_INTERCEPTOR_VERSION}"

    private const val PICASSO = "com.squareup.picasso:picasso:${Versioning.PICASSO_VERSION}"
    private const val CIRCLE_IMAGEVIEW = "de.hdodenhof:circleimageview:${Versioning.CIRCLE_IMAGEVIEW_VERSION}"
    private const val ANDROID_SPIN_KIT = "com.github.ybq:Android-SpinKit:1.4.0"
    const val INSTALL_REFERRER = "com.android.installreferrer:installreferrer:${Versioning.INSTALL_REFERRER_VERSION}"

    private const val DEBUG_DB = "com.amitshekhar.android:debug-db:1.0.6"
    private const val CHUCK_DEBUG = "com.readystatesoftware.chuck:library:1.1.0"
    private const val CHUCK_RELEASE_NO_OP = "com.readystatesoftware.chuck:library-no-op:1.1.0"

    private const val GLIDE = "com.github.bumptech.glide:glide:${Versioning.GLIDE_VERSION}"
    private const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versioning.GLIDE_VERSION}"

    fun DependencyHandler.androidx() {
        implementation(APPCOMPAT)
        implementation(CORE_KTX)
        implementation(CONSTRAINT_LAYOUT)
        implementation(LEGACY_SUPPORT_V4)
        implementation(MULTIDEX)
        implementation(RECYCLER_VIEW)
        implementation(FRAGMENT)
    }

    fun DependencyHandler.lifecycle() {
        implementation(LIFECYCLE_VIEWMODEL)
        implementation(LIFECYCLE_EXTENSIONS)
        implementation(LIFECYCLE_COMMON_JAVA8)
    }

    fun DependencyHandler.navigation() {
        implementation(NAVIGATION_FRAGMENT_KTX)
        implementation(NAVIGATION_UI_KTX)
    }

    fun DependencyHandler.room() {
        implementation(ROOM_RUNTIME)
        kapt(ROOM_COMPILER)
        implementation(ROOM_KTX)
    }

    fun DependencyHandler.koin() {
        implementation(KOIN_CORE)
        implementation(KOIN_VIEW_MODEL)
    }

    fun DependencyHandler.kotlin() {
        api(KOTLIN_JAVA8)
        api(KOTLIN_REFLECT)
    }

    fun DependencyHandler.retrofit() {
        implementation(RETROFIT)
        implementation(RETROFIT_CONVERTER_MOSHI)
        implementation(RETROFIT_CONVERTER_GSON)
        implementation(OKHTTP3_LOGGING_INTERCEPTOR)
    }

    fun DependencyHandler.moshi() {
        implementation(MOSHI)
        implementation(MOSHI_KOTLIN)
    }

    fun DependencyHandler.glide() {
        implementation (GLIDE)
        kapt (GLIDE_COMPILER)
    }

    fun DependencyHandler.picasso() {
        api(PICASSO)
        implementation(PICASSO)
    }

    fun DependencyHandler.debugTools() {
        releaseImplementation(CHUCK_RELEASE_NO_OP)
        debugImplementation(DEBUG_DB)
        debugImplementation(CHUCK_DEBUG)
    }

    fun DependencyHandler.coroutines() = implementation(COROUTINES_CORE)
    fun DependencyHandler.circleImageView() = implementation(CIRCLE_IMAGEVIEW)
    fun DependencyHandler.materialDesign() = api(MATERIAL_DESIGN)
    fun DependencyHandler.biometric() = implementation(BIOMETRIC)
    fun DependencyHandler.spinKit() = implementation(ANDROID_SPIN_KIT)
    fun DependencyHandler.installReferrer() = implementation(INSTALL_REFERRER)




}

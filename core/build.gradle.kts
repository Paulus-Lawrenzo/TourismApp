plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinParcelize)
    id(BuildPlugins.kotlinKapt)
}

apply {
    from("../shared_dependencies.gradle")
}

val moduleDependency: groovy.lang.Closure<Any> by ext

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Core.kotlin)

    // room
    api(Core.roomRuntime)
    kapt(Core.roomCompiler)
    androidTestImplementation(Core.roomTesting)

    // retrofit
    implementation(Core.retrofit)
    implementation(Core.retrofitConverter)
    api(Core.loggingInterceptor)

    // coroutine
    implementation(Core.kotlinCoroutinesCore)
    implementation(Core.kotlinCoroutinesAndroid)
    implementation(Core.roomKtx)

    api(Core.lifecycleLiveData)
    api(Core.activityKtx)
    api(Core.fragmentKtx)
}
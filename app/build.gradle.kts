plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

apply {
    from("../shared_dependencies.gradle")
}

val moduleDependency: groovy.lang.Closure<Any> by ext

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.dicoding.tourismapp"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(Core.constraint)
    implementation(Core.legacySupport)
    testImplementation(Core.junit)
    androidTestImplementation(Core.androidxJunit)
    androidTestImplementation(Core.espresso)
    implementation(Core.multidex)

    implementation(Core.glide)

    implementation(Core.roomRuntime)
    kapt(Core.roomCompiler)
    androidTestImplementation(Core.roomTesting)

    implementation(Core.retrofit)
    implementation(Core.retrofitConverter)
    implementation(Core.loggingInterceptor)

    implementation(Core.kotlinCoroutinesCore)
    implementation(Core.kotlinCoroutinesAndroid)
    implementation(Core.roomKtx)
    implementation(Core.dagger)
    kapt(Core.daggerCompiler)

    implementation(Core.activityKtx)
    implementation(Core.fragmentKtx)

    implementation(project(CoreModules.core))
}

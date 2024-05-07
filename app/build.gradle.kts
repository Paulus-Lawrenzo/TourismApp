plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinParcelize)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.hiltDagger)
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
    implementation(project(CoreModules.core))
    implementation(Core.kotlin)
}

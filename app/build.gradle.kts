plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.dotech.quotes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dotech.quotes"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Icons
    implementation(libs.androidx.material3.icons)
//    Coil
    implementation(libs.coil)

    //Compose
    implementation(libs.androidx.lifecycle.viewmodel.compse)
    implementation(libs.androidx.navigation.compse)
    implementation(libs.com.google.accompanist.flowlayout)


    //Coroutines
    implementation(libs.org.jetbrains.kotlinx.coroutine.core)
    implementation(libs.org.jetbrains.kotlinx.coroutine.android)


    //Coroutines Lifecycle Scoops
    implementation(libs.androidx.lifecyle.lifecyleViewmodel)
    implementation(libs.androidx.lifecycle.runtime.ktx)


    //Dagger Hilt
    implementation(libs.com.google.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose) //hiltViewModel


    ksp(libs.com.google.dagger.hilt.complier)
    ksp(libs.androidx.hilt.complier)

    //Retrofit2
    implementation(libs.com.squareup.retrofit2)
    implementation(libs.com.squareup.retrofit2.converter)

    // OkHTTP3
    implementation(libs.com.squareup.okhttp3)
    implementation(libs.com.squareup.okhttp3.logging)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.complier)
    implementation(libs.androidx.room.ext)


}
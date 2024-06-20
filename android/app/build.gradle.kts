import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleAndroidLibrariesMapsplatformSecretsGradlePlugin)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
}

var localProps = Properties()
localProps.load(FileInputStream(rootProject.file("local.properties")))

android {
    namespace = "com.capstone.floodforecast"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.capstone.floodforecast"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "MAPS_API_KEY", "\"${localProps.getProperty("MAPS_API_KEY")}\"")
        buildConfigField("String", "BASE_URL", "\"https://capstone-wvl6o5mvsq-et.a.run.app/api/v1/\"")
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore.jks")
            storePassword = localProps.getProperty("KEYSTORE_PASSWORD")
            keyAlias = localProps.getProperty("KEY_ALIAS")
            keyPassword = localProps.getProperty("KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
        viewBinding = true
        buildConfig = true
    }

    lint {
        baseline = file("lint-baseline.xml")
        disable += "Instantiatable"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // android jetpack library
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.1")
    implementation("androidx.activity:activity-ktx:1.9.0")
    implementation("androidx.fragment:fragment-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx")
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // retrofit2
    implementation("com.squareup.retrofit2:retrofit")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.0")

    // room
    implementation("androidx.room:room-ktx:2.4.3")
    implementation("androidx.room:room-paging:2.4.3")
    implementation("androidx.room:room-common:2.4.3")
    implementation("androidx.room:room-runtime:2.4.3")
    ksp("androidx.room:room-compiler:2.4.3")

    // bumptech
    implementation("com.github.bumptech.glide:glide:4.15.1")
    ksp("com.github.bumptech.glide:compiler:4.15.1")

    // cardview
    implementation("androidx.cardview:cardview:1.0.0")
}
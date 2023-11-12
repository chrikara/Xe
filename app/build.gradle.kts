plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Dagger Hilt
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")

    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
    id("de.mannodermaus.android-junit5") version "1.9.3.0"




}

android {
    namespace = "com.example.xe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.xe"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.xe.CustomTestRunner"
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
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-compiler:2.48.1")

    // Dagger Hilt and Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Moshi
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Serializable
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

// Test
    testImplementation("com.willowtreeapps.assertk:assertk:0.26.1")
    androidTestImplementation("com.willowtreeapps.assertk:assertk:0.26.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")

    testImplementation("io.mockk:mockk:1.12.5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")


    // Android test
    androidTestImplementation("io.mockk:mockk-android:1.12.5")

    //MockWebServer
    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")

    //Android Coroutines Test
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")


    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    // ...with Kotlin.
    kspAndroidTest("com.google.dagger:hilt-android-compiler:2.48.1")

    //Create Compose Rule
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")




}
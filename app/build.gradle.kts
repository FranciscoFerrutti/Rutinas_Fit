plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ar.edu.itba.rutinas_fit"
    compileSdk = 34

    defaultConfig {
        applicationId = "ar.edu.itba.rutinas_fit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
        // Es necesario activar la opción de compilación buildConfig para que se genere la clase
        // autogenerada BuilConfig con los parámetros de configuración.
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // No usar localhost o la IP 127.0.0.1 porque es la interfaz de loopback
            // del emulador. La forma de salir del emulador para acceder al localhost
            // de host del mismo es usando la IP 10.0.2.2.
            buildConfigField("String", "API_BASE_URL",
                "\"http://192.168.0.4:8080/api/\"")
        }
        debug {
            debug {
                // No usar localhost o la IP 127.0.0.1 porque es la interfaz de loopback
                // del emulador. La forma de salir del emulador para acceder al localhost
                // de host del mismo es usando la IP 10.0.2.2.
                buildConfigField("String", "API_BASE_URL",
                    "\"http://192.168.0.4:8080/api/\"")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = buildToolsVersion
}

dependencies {
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.compose.material3:material3:1.2.0-alpha11")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.5")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.27.0")
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")
    implementation ("io.coil-kt:coil-compose:1.4.0")
    implementation("androidx.media3:media3-ui:1.2.0")
    implementation ("com.google.android.exoplayer:exoplayer-core:2.14.1")
    implementation ("com.google.android.exoplayer:exoplayer-dash:2.14.1")
    implementation ("com.google.android.exoplayer:exoplayer-ui:2.14.1")
    implementation ("com.google.android.exoplayer:exoplayer-core:2.X.X")
    implementation ("com.google.android.exoplayer:exoplayer-ui:2.X.X")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
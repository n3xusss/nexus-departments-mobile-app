import java.util.Properties
import java.io.FileInputStream


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
}

val localPropertiesFile = rootProject.file("local.properties")
val apiProperties = Properties().apply {
    load(FileInputStream(localPropertiesFile))
}
android {



    namespace = "com.example.nexusapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nexusapp"
        minSdk = 26
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
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"${apiProperties.getProperty("api.key")}\"")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField("String", "API_KEY", "\"${apiProperties.getProperty("api.key")}\"")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    //constraint layout
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    //viewmodels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    //navigation
    implementation ("io.github.raamcosta.compose-destinations:animations-core:1.6.14-beta")
    ksp ("io.github.raamcosta.compose-destinations:ksp:1.6.14-beta")
    //Glide
    implementation ("com.github.bumptech.glide:compose:1.0.0-alpha.5")
    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.50")
    kapt ("com.google.dagger:hilt-android-compiler:2.50")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("androidx.hilt:hilt-work:1.1.0")
    kapt ("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.work:work-runtime-ktx:2.9.0")

    //system ui controller
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

// Converter for JSON serialization and deserialization
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// Converter for XML serialization and deserialization (if needed)
// implementation 'com.squareup.retrofit2:converter-simplexml:2.9.0'

// Logging interceptor for debugging (optional)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    // CameraX
    implementation ("androidx.camera:camera-camera2:1.3.0")
    implementation ("androidx.camera:camera-lifecycle:1.3.0")
    implementation ("androidx.camera:camera-view:1.4.0-alpha02")
    //ML kit barecode scanner
    implementation ("com.google.mlkit:barcode-scanning:17.2.0")
    //Color picker
    implementation("com.github.skydoves:colorpicker-compose:1.0.7")
    //Time and date picker
    implementation ("io.github.vanpra.compose-material-dialogs:datetime:0.8.1-rc")




}
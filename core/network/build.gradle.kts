plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.nour.core.network"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildFeatures {
            buildConfig = true
        }

        defaultConfig {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/movie/\"")
            buildConfigField("String", "API_KEY", "\"c77ba13b89c042bdaf4271af5351b0bb\"")
            buildConfigField("String", "ACCESS_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNzdiYTEzYjg5YzA0MmJkYWY0MjcxYWY1MzUxYjBiYiIsIm5iZiI6MTcyMjg0NTQyMS40MjEsInN1YiI6IjY2YjA4OGVkNmRmY2ZlNTVmNmJkZDU0MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NFf4YH6nJdqyKEBJyfqk07_GjdmbU2x7gHZ-opndNLk\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.bundles.ktor)
    implementation(libs.ktor.client.okhttp)

    implementation(libs.timber)

    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)

    implementation(libs.bundles.koin)
    ksp(libs.koin.ksp.compiler)

    implementation(libs.kotlinx.serialization)

    implementation(project(":core:common"))
}
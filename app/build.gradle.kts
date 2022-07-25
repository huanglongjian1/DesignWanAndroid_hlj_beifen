plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Version.compileSdk

    defaultConfig {
        applicationId = Version.applicationId
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk
        versionCode = Version.versionCode
        versionName = Version.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    applicationVariants.all {
        outputs.all {
            (this as? com.android.build.gradle.internal.api.ApkVariantOutputImpl)?.outputFileName =
                "Design WanAndroid-${Version.versionName}-${name}.apk"
        }
    }

    buildTypes {
        debug {
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    lint {
        baseline = File("lint-baseline.xml")
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    compileOptions {
        targetCompatibility(JavaVersion.VERSION_11)
        sourceCompatibility(JavaVersion.VERSION_11)
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar", "*.aar")))
    implementation(project(mapOf("path" to ":multitype")))
    implementation(project(mapOf("path" to ":resource")))
    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":compose")))
    implementation(Deps.coreKtx)
    implementation(Deps.appcompat)
    implementation(Deps.activity)
    implementation(Deps.fragment)
    implementation(Deps.material)
    implementation(Deps.constraintlayout)
    implementation(Deps.lifecycleLiveDataKtx)
    implementation(Deps.lifecycleViewModelKtx)
    implementation(Deps.lifecucleRuntimeKtx)
    implementation(Deps.navigationFragmentKtx)
    implementation(Deps.navigationUiKtx)
    implementation(Deps.swiperefreshlayout)
    implementation(Deps.recyclerview)
    implementation(Deps.paging)
    implementation(Deps.pagingKtx)
    implementation(Deps.dataStore)
    implementation(Deps.preferences)
    implementation(Deps.hiltAndroid)
    kapt(Deps.kaptHiltAndroidCompiler)
    kapt(Deps.kaptHiltCompiler)

//    implementation(Deps.okhttp)
//    implementation(Deps.okhttpLoggingInterceptor)
    implementation(Deps.gson)
    implementation(Deps.fresco)

    implementation(Deps.banner)
    implementation(Deps.flexbox)

//    implementation(Deps.kotlinSerial)
    debugImplementation(Deps.DebugDependency.debugLeakCanary)

    testImplementation(Deps.testJunit)
    androidTestImplementation(Deps.androidTestJunit)
    androidTestImplementation(Deps.androidTestEspresso)
}
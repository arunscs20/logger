plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.vanniktech.maven.publish)
}

android {
    namespace = "io.github.arunscs20.logger"
    compileSdk = 37
    version = "1.0.1"

    defaultConfig {
        minSdk = 24
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
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

mavenPublishing{
    coordinates(
        "io.github.arunscs20",
        "logger-runtime",
        version.toString()
    )
    pom{
        name.set("Logger")
        description.set("A lightweight Android Logcat logger plugin")
        inceptionYear.set("2026")
        url.set("https://github.com/arunscs20/logger")
        licenses{
            license{
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers{
            developer{
                id.set("arunscs20")
                name.set("Arun S C S")
                url.set("https://www.aetheralstudios.com/arun")
            }
        }
        scm{
            url.set("https://github.com/arunscs20/logger")
            connection.set("scm:git:git://github.com/arunscs20/logger.git")
            developerConnection.set("scm:git:ssh://git@github.com/arunscs20/logger.git")
        }
    }
}

//signing {
//    val keyId = System.getenv("ORG_GRADLE_PROJECT_signing.keyId")
//    val password = System.getenv("ORG_GRADLE_PROJECT_signing.password")
//    val keyFile = System.getenv("ORG_GRADLE_PROJECT_signing.secretKeyRingFile")
//
//    if (keyFile != null) {
//        // This explicitly tells Gradle to use the file you created in the workflow
//        project.extra.set("signing.keyId", keyId)
//        project.extra.set("signing.password", password)
//        project.extra.set("signing.secretKeyRingFile", keyFile)
//    }
//
//    sign(publishing.publications)
//}
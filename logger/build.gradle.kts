plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.vanniktech.maven.publish)
}

android {
    namespace = "com.aetheralstudios.logger"
    compileSdk = 36

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
    kotlinOptions {
        jvmTarget = "11"
    }
}

mavenPublishing{
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(
        "io.github.arunscs20",
        "logger",
        "1.0.0"
    )
    pom{
        name.set("Logger")
        description.set("A lightweight Android Logcat logger plugin")
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

//afterEvaluate{
//    publishing {
//        publications{
//            create<MavenPublication>("release"){
//                from(components["release"])
//                groupId = "io.github.arunscs20"
//                artifactId = "logger"
//                version = "1.0.0"
//
//
//            }
//        }
//        repositories{
//            maven {
//                name = "MavenCentral"
//                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
//                credentials {
//                    username = project.findProperty("mavenCentralUsername") as String?
//                    password = project.findProperty("mavenCentralPassword") as String?
//                }
//            }
//        }
//    }
//    signing {
//        sign(publishing.publications["release"])
//    }
//}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
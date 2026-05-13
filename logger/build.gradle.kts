plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    signing
    `maven-publish`
}

android {
    namespace = "com.aetheralstudios.logger"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aetheralstudios.logger"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    publishing{
        singleVariant("release"){
            withSourcesJar()
            withJavadocJar()
        }
    }

    afterEvaluate{
        publishing {
            publications{
                create<MavenPublication>("release"){
                    from(components["release"])
                    groupId = "io.github.arun1810"
                    artifactId = "logger"
                    version = "1.0.0"

                    pom{
                        name.set("Logger")
                        description.set("A lightweight Android Logcat logger plugin")
                        url.set("https://github.com/arunscs20/logger")

                        licenses{
                            license{
                                name.set("Apache-2.0")
                                url.set("https://www.apache.org/licenses/LICENSE-2.0")
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
            }
            repositories{
                maven {
                    name = "sonatype"
                    url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    credentials {
                        username = project.findProperty("ossrhUsername") as String?
                        password = project.findProperty("ossrhPassword") as String?
                    }
                }
            }
        }
        signing {
            sign(publishing.publications["release"])
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
import org.gradle.api.publish.maven.MavenPublication

plugins {
    id("java")
    id("maven-publish")
    id("jacoco")
    id("com.github.sherter.google-java-format") version "0.9"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    signing
}



group = "io.github.alesaudate"
version = "1.1-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
    withJavadocJar()
    withSourcesJar()
}

nexusPublishing {
    repositories {
        sonatype {  //only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "pix-qrcodegen"

            from(components["java"])

            pom {
                name.set("PIX QR Code generator")
                description.set("Helper library to create PIX-compliant QR Code texts")
                url.set("https://github.com/alesaudate/pix-qrcodegen")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("alesaudate")
                        name.set("Alexandre Saudate")
                    }
                }

                scm {
                    url.set("https://github.com/alesaudate/pix-qrcodegen")
                    connection.set("scm:git://github.com/alesaudate/pix-qrcodegen.git")
                    developerConnection.set("scm:git://github.com/alesaudate/pix-qrcodegen.git")
                }
            }
        }
    }
}
signing {
    sign(publishing.publications["mavenJava"])
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}


tasks.googleJavaFormat {
    googleJavaFormat.toolVersion = "1.0"
}

tasks.verifyGoogleJavaFormat {
    if (googleJavaFormat.toolVersion == "1.8") {
        googleJavaFormat.toolVersion = "1.0"
    }
}


tasks.check {
    dependsOn("jacocoTestCoverageVerification")
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "1.0".toBigDecimal()  //100% coverage
            }
        }
    }
}
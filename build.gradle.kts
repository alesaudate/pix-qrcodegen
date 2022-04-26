plugins {
    id("java")
    id("jacoco")
    id("com.github.sherter.google-java-format") version "0.9"
}

group = "io.github.alesaudate"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
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
                minimum = "0.9".toBigDecimal()
            }
        }
    }
}
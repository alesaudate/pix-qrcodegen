plugins {
    id("java")
    id("com.github.sherter.google-java-format") version "0.9"
}

group = "com.github.alesaudate"
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
}


tasks.googleJavaFormat {
    googleJavaFormat.toolVersion = "1.0"
}
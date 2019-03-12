import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
}

group = "com.zlrx.kotlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.4.0")


}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
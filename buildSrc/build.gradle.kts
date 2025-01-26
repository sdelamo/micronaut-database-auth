plugins {
    `kotlin-dsl`
    `groovy-gradle-plugin`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}
dependencies {
    implementation(libs.gradle.micronaut)
    implementation(libs.spotless)
    implementation(libs.graalvm.native.buildtools)
}

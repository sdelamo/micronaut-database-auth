plugins {
    java
    checkstyle // https://docs.gradle.org/current/userguide/checkstyle_plugin.html
    jacoco
    id("com.diffplug.spotless")
}

java {
    withSourcesJar()
    withJavadocJar()
}

group = project.findProperty("mavenGroup") as String
version = project.findProperty("projectVersion") as String

repositories {
    mavenCentral()
}


spotless {
    java {
        licenseHeaderFile("$rootDir/config/spotless.license.java")
    }
}

val mn = extensions.findByType<VersionCatalogsExtension>()!!.named("mn")

dependencies {
    // Logging
    testRuntimeOnly(mn.findLibrary("logback.classic").get())

    // JUnit Testing
    testImplementation(mn.findLibrary("junit.jupiter.api").get())

}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    jacocoTestReport {
        dependsOn(tasks.test)
        reports {
            xml.required.set(true)
            csv.required.set(false)
            html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
        }
    }

    test {
        finalizedBy(tasks.jacocoTestReport)
    }

}

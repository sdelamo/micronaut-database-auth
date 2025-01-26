plugins {
    id("io.micronaut.java-base")
    `maven-publish` // https://docs.gradle.org/current/userguide/publishing_maven.html
    signing // https://docs.gradle.org/current/userguide/signing_plugin.html
}

publishing {
    repositories {
        maven {
            name = "build"
            url = uri(rootProject.layout.buildDirectory.dir("repo"))
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                inceptionYear.set(project.findProperty("inceptionYear") as String)
                name.set(project.findProperty("projectName") as String)
                description.set(project.findProperty("projectDesc") as String)
                url.set(project.findProperty("projectUrl") as String)
                licenses {
                    license {
                        name.set("Apache-2.0")
                        url.set("https://spdx.org/licenses/Apache-2.0.html")
                    }
                }
                developers {
                    developer {
                        id.set(project.findProperty("developerId") as String)
                        name.set(project.findProperty("developerName") as String)
                        email.set(project.findProperty("developerEmail") as String)
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/${project.findProperty("githubOrg") as String}/${project.findProperty("githubRepo") as String}.git")
                    developerConnection.set("scm:git:ssh://github.com/${project.findProperty("githubOrg") as String}/${project.findProperty("githubRepo") as String}.git")
                    url.set("https://github.com/${project.findProperty("githubOrg") as String}/${project.findProperty("githubRepo") as String}")
                }
            }
        }
    }
}
if (!version.toString().endsWith("SNAPSHOT")) {
    signing {
        sign(publishing.publications["mavenJava"])
    }
}

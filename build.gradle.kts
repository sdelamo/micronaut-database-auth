import org.jreleaser.model.Active
plugins {
    id("base")
    id("org.jreleaser")
}
group = project.findProperty("mavenGroup") as String
version = project.findProperty("projectVersion") as String
jreleaser {
    release {
        github {
            skipRelease = true
        }
    }
    signing {
        active = Active.ALWAYS
        armored = true
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active = Active.ALWAYS
                    url.set("https://central.sonatype.com/api/v1/publisher")
                    stagingRepository("build/repo")
                }
            }
        }
    }
}
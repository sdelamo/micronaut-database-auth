plugins {
    id("io.micronaut.java-base")
    id("io.micronaut.library")
}

micronaut {
    processing {
        module.set(project.name)
        group.set(project.findProperty("mavenGroup") as String)
        incremental.set(true)
        annotations.add("${project.findProperty("mavenGroup") as String}.*")
    }
}

val mn = extensions.findByType<VersionCatalogsExtension>()!!.named("mn")

dependencies {
    testImplementation(mn.findLibrary("micronaut.test.junit5").get())
}

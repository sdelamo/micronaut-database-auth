plugins {
    base
    id("jacoco-report-aggregation") // https://docs.gradle.org/current/userguide/jacoco_report_aggregation_plugin.html
}

repositories {
    mavenCentral()
}

dependencies {
    jacocoAggregation(project(":micronaut-database-auth"))
}

reporting {
    reports {
        val testCodeCoverageReport by creating(JacocoCoverageReport::class) {
            testSuiteName = "test"
        }
    }
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport")) 
}

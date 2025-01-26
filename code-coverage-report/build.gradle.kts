plugins {
    base
    id("jacoco-report-aggregation") // https://docs.gradle.org/current/userguide/jacoco_report_aggregation_plugin.html
}

repositories {
    mavenCentral()
}

dependencies {
    jacocoAggregation(project(":example-micronaut-lib"))
}

reporting {
    reports {
        val testCodeCoverageReport by creating(JacocoCoverageReport::class) {
            testType = TestSuiteType.UNIT_TEST
        }
    }
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport")) 
}

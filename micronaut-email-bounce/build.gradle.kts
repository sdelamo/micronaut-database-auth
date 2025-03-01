plugins {
    id("io.micronaut.library.published-micronaut-library")
}
dependencies {
    annotationProcessor(mn.micronaut.validation.processor)
    api(mn.micronaut.validation)
    annotationProcessor(mn.micronaut.data.processor)
    api(mn.micronaut.data.jdbc)
    testImplementation(mn.micronaut.jdbc.hikari)
    testImplementation(mn.h2)
    testImplementation(mn.micronaut.liquibase)

}
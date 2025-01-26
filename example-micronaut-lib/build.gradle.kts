plugins {
    id("io.micronaut.library.published-micronaut-library")
}

dependencies {
    annotationProcessor(mn.micronaut.validation.processor)
    api(mn.micronaut.validation)
    api(mn.micronaut.views.fieldset)

    annotationProcessor(mn.micronaut.serde.processor)
    api(mn.micronaut.serde.api)
    api(mn.micronaut.data.model)
    testImplementation(mn.micronaut.serde.jackson)
    testImplementation(mn.micronaut.http.client)
    testImplementation(mn.micronaut.http.server.netty)

    testAnnotationProcessor(mn.micronaut.data.processor)
    testImplementation(mn.micronaut.data.jdbc)
    testImplementation(mn.micronaut.jdbc.hikari)
    testRuntimeOnly(mn.h2)

    testImplementation(mn.micronaut.views.thymeleaf)

    testImplementation(mn.micronaut.liquibase)
    testImplementation(mn.slf4j.jul.to.slf4j)
}

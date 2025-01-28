plugins {
    id("io.micronaut.library.published-micronaut-library")
}

dependencies {
    annotationProcessor(mn.micronaut.validation.processor)
    api(mn.micronaut.validation)
    api(mn.micronaut.views.fieldset)

    implementation(mn.micronaut.email)
    implementation(mn.micronaut.security)
    compileOnly(mn.micronaut.security.jwt)
    compileOnly(mn.micronaut.reactor)
    implementation(mn.micronaut.views.core)
    implementation(mn.micronaut.http.server)

    annotationProcessor(mn.micronaut.serde.processor)
    implementation(mn.micronaut.serde.jackson)

    testImplementation(mn.micronaut.security.jwt)
    testImplementation(mn.micronaut.views.thymeleaf)
    testImplementation(mn.micronaut.http.server.netty)
    testImplementation(mn.micronaut.http.client.jdk)
}

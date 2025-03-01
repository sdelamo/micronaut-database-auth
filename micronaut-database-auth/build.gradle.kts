plugins {
    id("io.micronaut.library.published-micronaut-library")
}

dependencies {
    annotationProcessor(mn.micronaut.validation.processor)
    api(mn.micronaut.validation)
    api(mn.micronaut.views.fieldset)
    api(mn.micronaut.email)
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
    testImplementation(mn.micronaut.http.client)
    testImplementation(mn.mockito.core)
}

if (System.getenv("CI") != null && hasProperty("buildScan")) {
    extensions.findByName("buildScan")?.withGroovyBuilder {
        setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
        setProperty("termsOfServiceAgree", "yes")
    }
}

plugins {
    id("io.micronaut.java-base")
    groovy
}
repositories {
    mavenCentral()
}
dependencies {
    testImplementation(project(":micronaut-database-auth"))
    testImplementation(mn.micronaut.security.jwt)
    testImplementation(mn.micronaut.views.thymeleaf)
    testImplementation(mn.micronaut.http.server.netty)
    testCompileOnly(mn.micronaut.inject.groovy)

    testImplementation(mn.micronaut.test.spock)
    testImplementation(libs.geb.spock) {
        exclude(group = "org.spockframework", module = "spock-core")
        exclude(group = "org.apache.groovy.geb", module = "geb-implicit-assertions")
    }

    testImplementation(libs.selenium.support)
    testImplementation(libs.selenium.api)
    testImplementation(libs.selenium.remote.driver)
    testRuntimeOnly(libs.selenium.chrome.driver)
    testRuntimeOnly(libs.selenium.firefox.driver)
    testRuntimeOnly(libs.selenium.safari.driver)
}
tasks {
    withType<Test> {
        systemProperties["webdriver.chrome.driver"] = "/Users/sdelamo/Applications/geckodriver"
        systemProperties["webdriver.gecko.driver"] = "/Users/sdelamo/Applications/geckodriver"
    }
}
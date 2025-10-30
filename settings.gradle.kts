plugins {
    id("io.micronaut.platform.catalog") version "4.6.1"
    id("com.gradle.develocity") version("4.2.2") // https://plugins.gradle.org/plugin/com.gradle.develocity
}

rootProject.name = "micronaut-database-auth-parent"

include("micronaut-email-bounce")
include("micronaut-database-auth")
include("code-coverage-report")
include("docs")
include("ui-tests")

develocity {
    buildScan {
        termsOfUseUrl.set("https://gradle.com/help/legal-terms-of-use")
        termsOfUseAgree.set("yes")
    }
}
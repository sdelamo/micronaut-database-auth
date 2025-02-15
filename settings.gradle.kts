plugins {
    id("io.micronaut.platform.catalog") version "4.4.5"
    id("com.gradle.develocity") version("3.19.1")
}

rootProject.name = "micronaut-database-auth-parent"

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
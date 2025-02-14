plugins {
    id("io.micronaut.platform.catalog") version "4.4.5"
}

rootProject.name = "micronaut-database-auth-parent"

include("micronaut-database-auth")
include("code-coverage-report")
include("docs")
include("ui-tests")

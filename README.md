
# Micronaut Library Gradle Template
[GitHub Repository Template](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-template-repository) to create a [Micronaut](https://micronaut.io) library with a Gradle Build

## Language and Test Framework

This template assumes you want to write a Java Library compatible with Java 17 and uses [Micronaut JUnit 5](https://micronaut-projects.github.io/micronaut-test/4.6.2/guide/#junit5) for testing. 

## Library Configuration

Modify `gradle.properties`. Change `TODO` items with your project details.

### Micronaut Version

You define the version of [Micronaut Platform](https://micronaut-projects.github.io/micronaut-platform/latest/guide/) in `micronautVersion` in `gradle/libs.versions.toml`.

### Micronaut Gradle Plugin

You define the version of [Micronaut Gradle Plugin](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/index.html) in `micronaut-gradle-plugin` in `gradle/libs.versions.toml` and in `settings.gradle.kts`.

## Example

This repository contains a module named `example-micronaut-lib`. It is an example of a Micronaut library which exposes introspected classes, a `@Singleton` `PaginationFactory` and configuration bean `PaginationConfiguration` with a default value defined in `PaginationConfigurationProperties`.

`example-micronaut-lib/build.gradle.kts` show you can simply apply the internal plugin `io.micronaut.library.maven-publish` or the `io.micronaut.library.maven-java-library` plugin, if you don't whish to publish to  Sonatype OSSRH. It applies `checkstyle`, `spotless`, `JaCoCo`, and GraalVM Native Image plugins.

## Micronaut Platform Gradle Version Catalogue

This template applies the [Micronaut Version Catalogue](https://micronaut-projects.github.io/micronaut-platform/latest/guide) in root `settings.gradle.kts`.

`example-micronaut-lib/build.gradle.kts`show how to use the Micronaut Platform version Catalogue.

## License Management

This template uses the [Spotless Gradle Plugin](https://github.com/diffplug/spotless) to ensure every file contains a license matching the template in `config/spotless.license.java`.

## Checkstyle

The template applies the [Checkstyle Gradle Plugin](https://docs.gradle.org/current/userguide/checkstyle_plugin.html). You can configure it with `config/checkstyle/checkstyle.xml` and `config/checkstyle/supressions.xml`

## Code Coverage

The template applies the [JaCoCo Gradle Plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html) to get code coverage and the [JaCoCo report Aggregation Plugin](https://docs.gradle.org/current/userguide/jacoco_report_aggregation_plugin.html). 

Run `./gradlew testCodeCoverageReport` and you can access the HTML report `open code-coverage-report/build/reports/jacoco/testCodeCoverageReport/html/index.html`.

## GraalVM Native Image

The template applies the [Gradle Build Native Image Plugin](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html). You can run the Gradle task `./gradlew nativeTest` to ensure your library is compatible with Native Image.  

## Publish to Sonatype OSSRH (OSS Repository Hosting)

### GPG Key
To publish to Sonatype OSSRH, you need first to [generate a GPG key](https://central.sonatype.org/publish/requirements/gpg/#generating-a-key-pair9) and [distributed your public key](https://central.sonatype.org/publish/requirements/gpg/#distributing-your-public-key). 

### User Token

- [Generate a Token](https://central.sonatype.org/publish/generate-token/#generate-a-token-on-ossrh-sonatype-nexus-repository-manager-servers). Save the user token username and password.

### Credentials

The [credentials](https://central.sonatype.org/publish/publish-gradle/#credentials) for signing and upload can be stored in your `gradle.properties` file in your users home directory. The content would look like this

`$HOME/.gradle/gradle.properties`

```properties
sonatypeUsername=SonaTypeUserTokenUserName
sonatypePassword=SonaTypeUserTokenPassword
# <1>
signing.keyId=YourKeyId
# <2>
signing.password=YouPublicKeyPassword
# <3>
signing.secretKeyRingFile=PathToYourKeyRingFile
```

<1> The public key ID (The last 8 symbols of the keyId. You can use `gpg -K --keyid-format short` to get it).  
<2> The passphrase used to protect your private key.  
<3> The absolute path to the secret key ring file containing your private key. (Since gpg 2.1, you need to export the keys with command `gpg --keyring secring.gpg --export-secret-keys > ~/.gnupg/secring.gpg`).  

### Publish Release to Sonatype OSSRH

- Bump up version. Ensure `projectVersion` does not contain `-SNAPSHOT`. 
- Tag it. E.g. `v1.0.0`
- `./gradlew publishToSonatype closeSonatypeStagingRepository --info`

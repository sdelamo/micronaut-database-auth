/*
 * Copyright 2025-2025 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.micronaut.bootstrap;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Property(name = "liquibase.datasources.default.change-log", value = "classpath:db/liquibase-changelog.xml")
@Property(name = "datasources.default.password", value = "")
@Property(name = "datasources.default.dialect", value = "H2")
@Property(name = "datasources.default.url", value = "jdbc:h2:mem:devDb;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE")
@Property(name = "datasources.default.username", value = "sa")
@Property(name = "datasources.default.driver-class-name", value = "org.h2.Driver")
@Property(name = "micronaut.data.pageable.max-page-size", value = "10")
@Property(name = "spec.name", value = "GithubReleaseControllerTest")
@DisabledInNativeImage
@MicronautTest
class GithubReleaseControllerTest {

    @Test
    void paginationTest(@Client("/")HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String html = client.retrieve(HttpRequest.GET("/").accept(MediaType.TEXT_HTML));
        assertTrue(html.contains(">1</span></li>"));
        assertTrue(html.contains(">2</a></li>"));
        assertTrue(html.contains(">3</a></li>"));
        assertTrue(html.contains(">4</a></li>"));
        assertTrue(html.contains(">5</a></li>"));
        assertTrue(html.contains("<span aria-hidden=\"true\">&raquo;</span>"));
        assertFalse(html.contains(">6</a></li>"));


        html = client.retrieve(HttpRequest.GET(UriBuilder.of("/").queryParam("page", "16").build()).accept(MediaType.TEXT_HTML));
        assertTrue(html.contains(">17</span></li>"));
        assertTrue(html.contains(">16</a></li>"));
        assertTrue(html.contains(">15</a></li>"));
        assertTrue(html.contains(">14</a></li>"));
        assertTrue(html.contains(">13</a></li>"));
        assertFalse(html.contains("<span aria-hidden=\"true\">&raquo;</span>"));
        assertTrue(html.contains("<span aria-hidden=\"true\">&laquo;</span>"));
    }
}

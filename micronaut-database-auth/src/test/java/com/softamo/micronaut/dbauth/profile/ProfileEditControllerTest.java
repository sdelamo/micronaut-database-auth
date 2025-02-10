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
package com.softamo.micronaut.dbauth.profile;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Property(name = "i18n.languages[0]", value = "SPANISH")
@Property(name = "i18n.languages[1]", value = "GALICIAN")
@Property(name = "i18n.languages[2]", value = "CATALAN")
@Property(name = "i18n.languages[3]", value = "BASQUE")
@Property(name = "i18n.languages[4]", value = "VALENCIAN")
@Property(name = "i18n.default-timezone", value = "Europe/Madrid")
@Property(name = "micronaut.security.mock.username", value = "sdelamo")
@Property(name = "spec.name", value = "ProfileEditController")
@MicronautTest
class ProfileEditControllerTest {
    public static final String PATH = "/profile/edit";

    @DisabledInNativeImage
    @Test
    void profileEditFormIsRendered(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String html = assertDoesNotThrow(() -> client.retrieve(HttpRequest.GET(PATH).accept(MediaType.TEXT_HTML)));
        assertTrue(html.contains("Español"));
        assertTrue(html.contains("<option value=\"Europe/Madrid\" selected=\"selected\">Europe/Madrid (Central European Standard Time)</option>"));
    }
}

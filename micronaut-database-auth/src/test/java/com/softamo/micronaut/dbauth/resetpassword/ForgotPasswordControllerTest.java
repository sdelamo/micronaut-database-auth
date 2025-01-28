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
package com.softamo.micronaut.dbauth.resetpassword;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Singleton;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "spec.name", value = "ResetPasswordControllerTest")
@MicronautTest
class ResetPasswordControllerTest {

    public static final String PATH = "/resetPassword";
    private Argument<String> ARG_HTML = Argument.of(String.class);

    @Test
    void resetPasswordFormIsRendered(@Client("/") HttpClient httpClient) {
        HttpRequest<?> resetPasswordRequest =
                HttpRequest.GET(PATH).accept(MediaType.TEXT_HTML);
        BlockingHttpClient client = httpClient.toBlocking();
        String html = assertDoesNotThrow(() -> client.retrieve(resetPasswordRequest));
        assertNotNull(html);
        assertTrue(html.contains("<form"));
        assertTrue(html.contains("action=\"/resetPassword\""));
    }

//    @Test
//    void resetPasswordFormSubmission(@Client("/") HttpClient httpClient) {
//        BlockingHttpClient client = httpClient.toBlocking();
//        assertDoesNotThrow(() -> client.retrieve(formSubmission("foo@email.com")));
//    }
//
//    @Test
//    void resetPasswordFormSubmissionWithInvalidEmail(@Client("/") HttpClient httpClient) {
//        BlockingHttpClient client = httpClient.toBlocking();
//        HttpRequest<?> resetPasswordRequest = formSubmission("invalidemail");
//        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () -> client.retrieve(resetPasswordRequest, ARG_HTML, ARG_HTML));
//        assertEquals(422, ex.getStatus().getCode());
//        Optional<String> htmlOptional = ex.getResponse().getBody(String.class);
//        assertTrue(htmlOptional.isPresent());
//        String html = htmlOptional.get();
//        assertNotNull(html);
//        assertTrue(html.contains("<form"));
//        assertTrue(html.contains("action=\"/resetPassword\""));
//    }

    private static HttpRequest<?> formSubmission(String email) {
        return HttpRequest.POST(PATH, Map.of("email", email))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE);
    }

    @Requires(property = "spec.name", value = "ResetPasswordControllerTest")
    @Replaces(ResetPasswordService.class)
    @Singleton
    static class ResetPasswordServiceReplacement implements ResetPasswordService {
    }
}

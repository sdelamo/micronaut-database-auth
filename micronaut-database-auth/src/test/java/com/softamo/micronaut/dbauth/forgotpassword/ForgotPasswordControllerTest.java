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
package com.softamo.micronaut.dbauth.forgotpassword;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "spec.name", value = "ForgotPasswordControllerTest")
@MicronautTest
class ForgotPasswordControllerTest {

    public static final String PATH = "/forgotPassword";
    private Argument<String> ARG_HTML = Argument.of(String.class);

    @Test
    void forgotPasswordFormIsRendered(@Client("/") HttpClient httpClient) {
        HttpRequest<?> forgotPasswordRequest =
                HttpRequest.GET(PATH).accept(MediaType.TEXT_HTML);
        BlockingHttpClient client = httpClient.toBlocking();
        String html = assertDoesNotThrow(() -> client.retrieve(forgotPasswordRequest));
        assertNotNull(html);
        assertTrue(html.contains("<form"));
        assertTrue(html.contains("action=\"/forgotPassword\""));
    }

    @Test
    void forgotPasswordFormSubmission(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        assertDoesNotThrow(() -> client.retrieve(formSubmission("foo@email.com")));
    }

    @Test
    void forgotPasswordFormSubmissionWithInvalidEmail(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest<?> forgotPasswordRequest = formSubmission("invalidemail");
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () -> client.retrieve(forgotPasswordRequest, ARG_HTML, ARG_HTML));
        assertEquals(422, ex.getStatus().getCode());
        Optional<String> htmlOptional = ex.getResponse().getBody(String.class);
        assertTrue(htmlOptional.isPresent());
        String html = htmlOptional.get();
        assertNotNull(html);
        assertTrue(html.contains("<form"));
        assertTrue(html.contains("action=\"/forgotPassword\""));
    }

    private static HttpRequest<?> formSubmission(String email) {
        return HttpRequest.POST(PATH, Map.of("email", email))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE);
    }

    @Requires(property = "spec.name", value = "ForgotPasswordControllerTest")
    @Replaces(ForgotPasswordFormService.class)
    @Singleton
    static class ForgotPasswordFormServiceReplacement implements ForgotPasswordFormService {

        @Override
        public void handleForgotPasswordFormSubmission(@NonNull @NotNull Locale locale,
                                                       @NonNull @NotBlank String host,
                                                       @NonNull @NotNull @Valid ForgotPasswordForm form) {
            //no op
        }
    }
}

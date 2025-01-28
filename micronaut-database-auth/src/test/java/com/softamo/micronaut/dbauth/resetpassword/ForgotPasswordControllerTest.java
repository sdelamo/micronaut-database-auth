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
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.type.Argument;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.*;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "micronaut.http.client.follow-redirects", value = StringUtils.FALSE)
@Property(name = "micronaut.security.unauthorized.url", value = "/login")
@Property(name = "spec.name", value = "ResetPasswordControllerTest")
@MicronautTest
class ResetPasswordControllerTest {

    public static final String PATH = "/resetPassword";
    private Argument<String> ARG_HTML = Argument.of(String.class);

    @DisabledInNativeImage
    @Test
    void resetPasswordFormIsRendered(@Client("/") HttpClient httpClient,
                                     ResetPasswordTokenGenerator tokenGenerator) {
        BlockingHttpClient client = httpClient.toBlocking();

        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class,
                () -> client.retrieve(HttpRequest.GET(PATH).accept(MediaType.TEXT_HTML)));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());

        ex = assertThrows(HttpClientResponseException.class,
                () -> client.retrieve(resetPasswordFormRequest("invalid")));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());

        String email = "foo@email.com";
        String token = tokenGenerator.generateResetPasswordToken(email);
        String html = assertDoesNotThrow(() -> client.retrieve(resetPasswordFormRequest(token)));
        assertNotNull(html);
        assertTrue(html.contains("<form"));
        assertTrue(html.contains("action=\"/resetPassword\""));
    }

    @DisabledInNativeImage
    @Test
    void resetPasswordFormSubmissionWithNotMatchingPasswords(@Client("/") HttpClient httpClient,
                                                             ResetPasswordTokenGenerator tokenGenerator) {
        String token = tokenGenerator.generateResetPasswordToken("foo@email.com");
        BlockingHttpClient client = httpClient.toBlocking();
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () -> client.retrieve(formSubmission(token, "password", "password2"), ARG_HTML, ARG_HTML));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());

        ex = assertThrows(HttpClientResponseException.class, () -> client.retrieve(formSubmission("invalidtoken", "password", "password"), ARG_HTML, ARG_HTML));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }

    @DisabledInNativeImage
    @Test
    void resetPasswordFormSubmission(@Client("/") HttpClient httpClient, ResetPasswordTokenGenerator tokenGenerator) {
        BlockingHttpClient client = httpClient.toBlocking();
        String token = tokenGenerator.generateResetPasswordToken("foo@email.com");
        HttpRequest<?> resetPasswordRequest = formSubmission(token, "password", "password");
        HttpResponse<?> response = assertDoesNotThrow(() -> client.exchange(resetPasswordRequest, ARG_HTML, ARG_HTML));
        String location = response.getHeaders().get(HttpHeaders.LOCATION);
        assertNotNull(location);
        assertEquals("/login", location);
    }

    private static HttpRequest<?> resetPasswordFormRequest(String token) {
        return HttpRequest.GET(UriBuilder.of(PATH).queryParam("token", token).build()).accept(MediaType.TEXT_HTML);
    }

    private static HttpRequest<?> formSubmission(String token, String password, String repeatPassword) {
        return HttpRequest.POST(PATH, Map.of(
                        "token", token,
                "password", password,
                        "repeatPassword", repeatPassword
                ))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE);
    }

    @Requires(property = "spec.name", value = "ResetPasswordControllerTest")
    @Replaces(ResetPasswordService.class)
    @Singleton
    static class ResetPasswordServiceReplacement implements ResetPasswordService {
        @Override
        public  void resetPassword(@NonNull @NotBlank @Email String email,
                                   @NonNull @NotBlank String password) {

        }
    }

    @Requires(property = "spec.name", value = "ResetPasswordControllerTest")
    @Controller("/login")
    static class LoginFormController {
        @Secured(SecurityRule.IS_ANONYMOUS)
        @Produces(MediaType.TEXT_HTML)
        @Get
        String index() {
            return "<!DOCTYPE html><html><head></head><body>Login form</body></html>";
        }
    }
}

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
package com.softamo.micronaut.dbauth.signup;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.type.Argument;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.*;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Property(name = "micronaut.http.client.follow-redirects", value = StringUtils.FALSE)
@Property(name = "micronaut.security.redirect.unauthorized.url", value = "/security/login")
@Property(name = "spec.name", value = "SignupControllerTest")
@MicronautTest
class SignupControllerTest {
    public static final String PATH = "/signup";
    private static final Argument<String> ARG_HTML = Argument.of(String.class);
    @Inject
    SignupService signupService;

    @DisabledInNativeImage
    @Test
    void signupFormIsRendered(@Client("/") HttpClient httpClient) {
        HttpRequest<?> forgotPasswordRequest =
                HttpRequest.GET(PATH).accept(MediaType.TEXT_HTML);
        BlockingHttpClient client = httpClient.toBlocking();
        String html = assertDoesNotThrow(() -> client.retrieve(forgotPasswordRequest, ARG_HTML, ARG_HTML));
        assertNotNull(html);
        assertTrue(html.contains("<form"));
        assertTrue(html.contains("action=\"/signup\""));
        assertFalse(html.contains("type=\"text\""));
        assertTrue(html.contains("type=\"email\""));
        assertTrue(html.contains("name=\"email\""));
        assertTrue(html.contains("type=\"password\""));
        assertTrue(html.contains("name=\"password\""));
        assertTrue(html.contains("name=\"repeatPassword\""));
    }

    @Test
    void signupFormSubmission(@Client("/") HttpClient httpClient) {
        SignUpForm signUpForm = new SignUpForm("sergio@email.com", "password", "password");
        HttpRequest<?> forgotPasswordRequest =
                HttpRequest.POST(PATH, Map.of("email", signUpForm.email(), "password", signUpForm.password(), "repeatPassword", signUpForm.repeatPassword()))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED);
        BlockingHttpClient client = httpClient.toBlocking();
        HttpResponse<?> response = assertDoesNotThrow(() -> client.exchange(forgotPasswordRequest));
        assertEquals(HttpStatus.SEE_OTHER, response.getStatus());
        assertEquals("/security/login", response.getHeaders().get(HttpHeaders.LOCATION));
        verify(signupService).signup(signUpForm);
    }

    @Requires(property = "spec.name", value = "SignupControllerTest")
    @MockBean(SignupService.class)
    SignupService mockSignupService() {
        return mock(SignupService.class);
    }

}

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
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "spec.name", value = "ForgotPasswordFormServiceTest")
@MicronautTest(startApplication = false)
class ForgotPasswordFormServiceTest {

    @Test
    void testSendForgotPasswordEmail(ForgotPasswordFormService service) {
        String host = "http://localhost:8080";
        Locale locale = Locale.ENGLISH;
        assertThrows(ConstraintViolationException.class, () -> service.handleForgotPasswordFormSubmission(locale, host, null));
        assertThrows(ConstraintViolationException.class, () -> service.handleForgotPasswordFormSubmission(locale, host, new ForgotPasswordForm("email")));
    }

    @Requires(property = "spec.name", value = "ForgotPasswordFormServiceTest")
    @Replaces(ForgotPasswordFormService.class)
    @Singleton
    static class ForgotPasswordFormServiceReplacement implements ForgotPasswordFormService {

        @Override
        public void handleForgotPasswordFormSubmission(@NonNull @NotNull Locale locale,
                                                       @NonNull @NotBlank String host,
                                                       @NonNull @NotNull @Valid ForgotPasswordForm form) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}

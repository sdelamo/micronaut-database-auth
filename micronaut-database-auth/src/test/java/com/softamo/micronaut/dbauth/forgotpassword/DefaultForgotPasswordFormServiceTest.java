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
import io.micronaut.context.annotation.Requires;
import io.micronaut.email.EmailSender;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Property(name = "spec.name", value = "DefaultForgotPasswordFormServiceTest")
@Property(name = "micronaut.email.from.email", value = "sender@email.com")
@MicronautTest(startApplication = false)
class DefaultForgotPasswordFormServiceTest {
    @Inject
    EmailSender emailSender;

    @DisabledInNativeImage
    @Test
    void testDefaultForgotPasswordFormService(ForgotPasswordFormService service) {
        service.handleForgotPasswordFormSubmission(Locale.ENGLISH, "http://localhost:8080", new ForgotPasswordForm("sergio@example.com"));
        verify(emailSender, timeout(1000)).send(any());
    }

    @Requires(property = "spec.name", value = "DefaultForgotPasswordFormServiceTest")
    @MockBean(EmailSender.class)
    EmailSender mockEmailSender() {
        return mock(EmailSender.class);
    }
}

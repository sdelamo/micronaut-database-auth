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

import com.softamo.micronaut.dbauth.exceptions.UserNotFoundException;
import com.softamo.micronaut.dbauth.repositories.UserRepository;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.email.EmailSender;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Property(name = "spec.name", value = "DefaultForgotPasswordFormServiceUserNotFoundTest")
@Property(name = "micronaut.email.from.email", value = "sender@email.com")
@MicronautTest(startApplication = false)
class DefaultForgotPasswordFormServiceUserNotFoundTest {
    @Inject
    EmailSender emailSender;

    @Inject
    UserRepository userRepository;

    @DisabledInNativeImage
    @Test
    void testDefaultForgotPasswordFormService(ForgotPasswordFormService service) {
        String email = "sergio@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> {
            service.handleForgotPasswordFormSubmission(Locale.ENGLISH, "http://localhost:8080", new ForgotPasswordForm(email));
        });
        verify(userRepository, times(1)).existsByEmail(email);
        verifyNoInteractions(emailSender);
    }

    @Requires(property = "spec.name", value = "DefaultForgotPasswordFormServiceUserNotFoundTest")
    @MockBean(EmailSender.class)
    EmailSender mockEmailSender() {
        return mock(EmailSender.class);
    }

    @Requires(property = "spec.name", value = "DefaultForgotPasswordFormServiceUserNotFoundTest")
    @MockBean(UserRepository.class)
    UserRepository mockUserRepository() {
        return mock(UserRepository.class);
    }
}

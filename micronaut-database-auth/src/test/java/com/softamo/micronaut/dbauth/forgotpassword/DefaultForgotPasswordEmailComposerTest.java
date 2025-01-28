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
import io.micronaut.email.BodyType;
import io.micronaut.email.Email;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "micronaut.email.from.email", value = "sender@email.com")
@MicronautTest(startApplication = false)
class DefaultForgotPasswordEmailComposerTest {

    @Test
    void testComposeEmail(ForgotPasswordEmailComposer composer) {
        String recipient = "foo@email.com";
        Email.Builder builder = composer.composeForgotPasswordEmail(Locale.ENGLISH, "http://localhost:8080", recipient);
        Email email = builder.build();
        assertEquals(1, email.getTo().size());
        assertEquals(recipient, email.getTo().stream().findFirst().get().getEmail());
        assertEquals("sender@email.com", email.getFrom().getEmail());
        assertEquals("Reset your password", email.getSubject());
        Optional<String> textOptional = email.getBody().get(BodyType.TEXT);
        assertTrue(textOptional.isPresent());
        String text = textOptional.get();
        assertTrue(text.contains("To set a password for your account, just click the button below and follow the instructions."));
        assertTrue(text.contains("http://localhost:8080/resetPassword?token="));
    }
}

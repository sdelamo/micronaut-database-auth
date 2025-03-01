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
package com.softamo.email.bounce.data;

import com.softamo.email.bounce.EmailFailureReason;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class EmailFailureEntityTest {
    @Test
    void emailFailureEventIsIntrospected() {
        assertDoesNotThrow(() -> BeanIntrospection.getIntrospection(EmailFailureEntity.class));
    }

    @Test
    void validEmail(Validator validator) {
        assertTrue(validator.validate(new EmailFailureEntity(null, null, "sergio@example.com", EmailFailureReason.COMPLAINT)).isEmpty());
    }

    @Test
    void invalidEmail(Validator validator) {
        assertFalse(validator.validate(new EmailFailureEntity(null, null, "example.com", EmailFailureReason.COMPLAINT)).isEmpty());
    }

    @Test
    void invalidReason(Validator validator) {
        assertFalse(validator.validate(new EmailFailureEntity(null, null, "sergio@example.com", null)).isEmpty());
    }
}

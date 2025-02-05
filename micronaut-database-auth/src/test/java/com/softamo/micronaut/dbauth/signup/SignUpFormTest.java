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

import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.type.Argument;
import io.micronaut.serde.SerdeIntrospections;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class SignUpFormTest {

    @Test
    void validEmail(Validator validator) {
        assertTrue(validator.validate(new SignUpForm("sergio@example.com", "foobar", "foobar")).isEmpty());
    }

    @Test
    void passwordsDontMatch(Validator validator) {
        assertFalse(validator.validate(new SignUpForm("sergio@example.com", "foobar", "barfoo")).isEmpty());
    }

    @Test
    void blankEmail(Validator validator) {
        assertFalse(validator.validate(new SignUpForm("", "foobar", "foobar")).isEmpty());
        assertFalse(validator.validate(new SignUpForm(null, "foobar", "foobar")).isEmpty());
    }

    @Test
    void invalidEmail(Validator validator) {
        assertFalse(validator.validate(new SignUpForm("example.com", "foobar", "foobar")).isEmpty());
    }

    @Test
    void isAnnotatedWithIntrospected() {
        assertDoesNotThrow(() -> BeanIntrospection.getIntrospection(SignUpForm.class));
    }

    @Test
    void resetPasswordFormIsAnnotatedWithSerdeableDeserializable(SerdeIntrospections serdeIntrospections) {
        assertDoesNotThrow(() -> serdeIntrospections.getDeserializableIntrospection(Argument.of(SignUpForm.class)));
    }

    @Test
    void resetPasswordFormIsAnnotatedWithSerdeableSerializable(SerdeIntrospections serdeIntrospections) {
        assertDoesNotThrow(() -> serdeIntrospections.getSerializableIntrospection(Argument.of(SignUpForm.class)));
    }
}

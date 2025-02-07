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
package com.softamo.micronaut.dbauth.editpassword;

import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.type.Argument;
import io.micronaut.serde.SerdeIntrospections;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class EditPasswordFormTest {

    @Test
    void validEditPasswordForm(Validator validator) {
        assertTrue(validator.validate(new EditPasswordForm("password", "foobar", "foobar")).isEmpty());
    }

    @Test
    void passwordsDontMatch(Validator validator) {
        assertFalse(validator.validate(new EditPasswordForm("password", "foobar", "barfoo")).isEmpty());
    }

    @Test
    void blankCurrentPassword(Validator validator) {
        assertFalse(validator.validate(new EditPasswordForm("", "foobar", "foobar")).isEmpty());
        assertFalse(validator.validate(new EditPasswordForm(null, "foobar", "foobar")).isEmpty());
    }

    @Test
    void blankPassword(Validator validator) {
        assertFalse(validator.validate(new EditPasswordForm("password", "", "foobar")).isEmpty());
        assertFalse(validator.validate(new EditPasswordForm("password", null, "foobar")).isEmpty());
    }

    @Test
    void blankRepeatPassword(Validator validator) {
        assertFalse(validator.validate(new EditPasswordForm("password", "foobar", "")).isEmpty());
        assertFalse(validator.validate(new EditPasswordForm("password", "foobar", null)).isEmpty());
    }

    @Test
    void isAnnotatedWithIntrospected() {
        assertDoesNotThrow(() -> BeanIntrospection.getIntrospection(EditPasswordForm.class));
    }

    @Test
    void resetPasswordFormIsAnnotatedWithSerdeableDeserializable(SerdeIntrospections serdeIntrospections) {
        assertDoesNotThrow(() -> serdeIntrospections.getDeserializableIntrospection(Argument.of(EditPasswordForm.class)));
    }

    @Test
    void resetPasswordFormIsAnnotatedWithSerdeableSerializable(SerdeIntrospections serdeIntrospections) {
        assertDoesNotThrow(() -> serdeIntrospections.getSerializableIntrospection(Argument.of(EditPasswordForm.class)));
    }
}

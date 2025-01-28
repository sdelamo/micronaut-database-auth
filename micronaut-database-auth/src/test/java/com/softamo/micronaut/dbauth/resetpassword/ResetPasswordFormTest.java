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

import com.softamo.micronaut.dbauth.constraints.RepeatPassword;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.type.Argument;
import io.micronaut.serde.SerdeIntrospections;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class ResetPasswordFormTest {

    @Test
    void implementsRepeatPassword() {
        assertInstanceOf(RepeatPassword.class, new ResetPasswordForm("xyz", "password", "password"));
    }

    @Test
    void notMatchingPasswordsFailsValidation(Validator validator) {
        assertTrue(validator.validate(new ResetPasswordForm("xyz", "password", "password")).isEmpty());
        assertFalse(validator.validate(new ResetPasswordForm("xyz", "password", "password1")).isEmpty());
    }

    @Test
    void tokenCannotBeBlank(Validator validator) {
        assertFalse(validator.validate(new ResetPasswordForm("", "password", "password")).isEmpty());
        assertFalse(validator.validate(new ResetPasswordForm(null, "password", "password")).isEmpty());
    }

    @Test
    void isAnnotatedWithIntrospected() {
        assertDoesNotThrow(() -> BeanIntrospection.getIntrospection(ResetPasswordForm.class));
    }

    @Test
    void resetPasswordFormIsAnnotatedWithSerdeableDeserializable(SerdeIntrospections serdeIntrospections) {
        assertDoesNotThrow(() -> serdeIntrospections.getDeserializableIntrospection(Argument.of(ResetPasswordForm.class)));
    }

    @Test
    void resetPasswordFormIsAnnotatedWithSerdeableSerializable(SerdeIntrospections serdeIntrospections) {
        assertDoesNotThrow(() -> serdeIntrospections.getSerializableIntrospection(Argument.of(ResetPasswordForm.class)));
    }
}

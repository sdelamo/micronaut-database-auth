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
package com.softamo.micronaut.dbauth.repositories;

import io.micronaut.context.annotation.Property;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Property(name = "spec.name", value = "UserRepositoryTest")
@MicronautTest(startApplication = false)
class UserRepositoryTest {

    @Test
    void emailNotBlank(UserRepository userRepository) {
        assertThrows(ConstraintViolationException.class, () -> userRepository.existsByEmail(null));
        assertThrows(ConstraintViolationException.class, () -> userRepository.existsByEmail(""));
    }

    @Test
    void mustBeEmail(UserRepository userRepository) {
        assertThrows(ConstraintViolationException.class, () -> userRepository.existsByEmail("foo"));
        assertDoesNotThrow(() -> userRepository.existsByEmail("sergio@example.com"));
    }

    @Property(name = "spec.name", value = "UserRepositoryTest")
    @Singleton
    static class UserRepositoryMock implements UserRepository {

        @Override
        public boolean existsByEmail(@NonNull @NotBlank @Email String email) {
            return true;
        }
    }
}

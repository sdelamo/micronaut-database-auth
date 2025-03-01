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
import com.softamo.email.bounce.EmailFailureValidator;
import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "emailfailure.threshold", value = "1")
@MicronautTest(startApplication = false, transactional = false)
class EmailFailureCrudRepositoryTest {

    @Test
    void testCrudOperations(EmailFailureRepository repository, EmailFailureValidator validator) {
        String email = "sergio@example.com";
        assertFalse(validator.exceedsFailureThreshold(email));
        assertDoesNotThrow(() -> repository.save(email, EmailFailureReason.COMPLAINT));
        assertFalse(validator.exceedsFailureThreshold(email));
        assertDoesNotThrow(() -> repository.save(email, EmailFailureReason.BOUNCE));
        assertTrue(validator.exceedsFailureThreshold(email));
    }
}

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
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.repository.GenericRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface EmailFailureRepository extends GenericRepository<@Valid EmailFailureEntity, Long> {
    long countByEmail(@NonNull @NotBlank @Email String email);

    default EmailFailureEntity save(@NonNull @NotBlank @Email String email, @NotNull @NotNull EmailFailureReason reason) {
        return save(new EmailFailureEntity(null, null, email, reason));
    }

    @NonNull EmailFailureEntity save(@NonNull EmailFailureEntity entity);
}

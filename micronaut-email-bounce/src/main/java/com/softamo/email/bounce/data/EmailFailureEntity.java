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
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * Mapped entity for email failure.
 * @param id primary key
 * @param dateCreated automatic timestamp for creation
 * @param email email
 * @param reason reason of failure
 */
@MappedEntity("emailfailure")
public record EmailFailureEntity(
        @Id @GeneratedValue @Nullable Long id,
        @DateCreated @Nullable Date dateCreated,
        @NonNull @NotBlank @Email String email,
        @NonNull @NotNull EmailFailureReason reason) {
}

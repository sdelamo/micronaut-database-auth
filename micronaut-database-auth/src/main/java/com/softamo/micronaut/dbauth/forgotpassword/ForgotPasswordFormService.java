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

import io.micronaut.context.annotation.DefaultImplementation;
import io.micronaut.core.annotation.NonNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;

/**
 * API to handle the Forgot Password Form submission.
 * Typically, this API implementation will email the user with a link to reset the password.
 * @author Sergio del Amo
 * @since 0.0.1
 */
@DefaultImplementation(DefaultForgotPasswordFormService.class)
@FunctionalInterface
public interface ForgotPasswordFormService {
    /**
     *
     * @param locale Locale
     * @param host Host
     * @param form Forgot Password Form
     */
    void handleForgotPasswordFormSubmission(@NonNull @NotNull Locale locale,
                                            @NonNull @NotBlank String host,
                                            @NonNull @NotNull @Valid ForgotPasswordForm form);
}

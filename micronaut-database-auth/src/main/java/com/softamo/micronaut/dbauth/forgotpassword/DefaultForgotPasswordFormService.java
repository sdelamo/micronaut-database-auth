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

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.email.EmailSender;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;

@Requires(beans = {EmailSender.class})
@Singleton
@Internal
class DefaultForgotPasswordFormService implements ForgotPasswordFormService {
    private final EmailSender<?, ?> emailSender;
    private final ForgotPasswordEmailComposer forgotPasswordEmailComposer;

    DefaultForgotPasswordFormService(
            EmailSender<?, ?> emailSender,
            ForgotPasswordEmailComposer forgotPasswordEmailComposer) {
        this.emailSender = emailSender;
        this.forgotPasswordEmailComposer = forgotPasswordEmailComposer;
    }

    @Override
    public void handleForgotPasswordFormSubmission(@NonNull @NotNull Locale locale,
                                                   @NonNull @NotBlank String host,
                                                   @NonNull @NotNull @Valid ForgotPasswordForm form) {
        emailSender.send(forgotPasswordEmailComposer.composeForgotPasswordEmail(locale, host, form.email()));
    }
}

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

import com.softamo.micronaut.dbauth.exceptions.UserNotFoundException;
import com.softamo.micronaut.dbauth.repositories.UserRepository;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.email.Email;
import io.micronaut.email.EmailSender;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;

@Requires(beans = {EmailSender.class, ForgotPasswordEmailComposer.class})
@Singleton
@Internal
class DefaultForgotPasswordFormService implements ForgotPasswordFormService {
    private final EmailSender<?, ?> emailSender;
    private final ForgotPasswordEmailComposer forgotPasswordEmailComposer;
    @Nullable
    private final UserRepository userRepository;

    DefaultForgotPasswordFormService(
            EmailSender<?, ?> emailSender,
            ForgotPasswordEmailComposer forgotPasswordEmailComposer,
            @Nullable UserRepository userRepository) {
        this.emailSender = emailSender;
        this.forgotPasswordEmailComposer = forgotPasswordEmailComposer;
        this.userRepository = userRepository;
    }

    @Override
    public void handleForgotPasswordFormSubmission(@NonNull @NotNull Locale locale,
                                                   @NonNull @NotBlank String host,
                                                   @NonNull @NotNull @Valid ForgotPasswordForm form) {
        if (userRepository != null && !userRepository.existsByEmail(form.email())) {
            throw new UserNotFoundException();
        }
        Email.Builder email = forgotPasswordEmailComposer.composeForgotPasswordEmail(locale, host, form.email());
        sendEmail(email);
    }

    @Async
    public void sendEmail(Email.Builder email) {
        emailSender.send(email);
    }
}

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

import com.softamo.micronaut.dbauth.resetpassword.ResetPasswordConfiguration;
import com.softamo.micronaut.dbauth.resetpassword.ResetPasswordTokenGenerator;
import io.micronaut.context.MessageSource;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.email.Email;
import io.micronaut.email.configuration.FromConfiguration;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;

@Requires(beans = {MessageSource.class, FromConfiguration.class, ResetPasswordTokenGenerator.class})
@Singleton
@Internal
class DefaultForgotPasswordEmailComposer implements ForgotPasswordEmailComposer {
    private static final String LINE_BREAK = "\n";
    private final MessageSource messageSource;
    private final FromConfiguration fromConfiguration;
    private final ResetPasswordTokenGenerator resetPasswordTokenGenerator;
    private final ResetPasswordConfiguration resetPasswordConfiguration;

    DefaultForgotPasswordEmailComposer(MessageSource messageSource,
                                       FromConfiguration fromConfiguration,
                                       ResetPasswordTokenGenerator resetPasswordTokenGenerator,
                                       ResetPasswordConfiguration resetPasswordConfiguration) {
        this.messageSource = messageSource;
        this.fromConfiguration = fromConfiguration;
        this.resetPasswordTokenGenerator = resetPasswordTokenGenerator;
        this.resetPasswordConfiguration = resetPasswordConfiguration;
    }

    @Override
    @NonNull
    public Email.Builder composeForgotPasswordEmail(@NonNull @NotNull Locale locale,
                                             @NonNull @NotBlank String host,
                                             @NonNull @NotBlank @jakarta.validation.constraints.Email String recipient) {
        String token = resetPasswordTokenGenerator.generateResetPasswordToken(recipient);
        String subject = messageSource.getMessage("resetPassword.email.subject", "Reset your password", locale);
        String instructions = messageSource.getMessage("resetPassword.email.instructions", "To set a password for your account, just click the button below and follow the instructions.", locale);
        String link = UriBuilder.of(host).path(resetPasswordConfiguration.getPath()).queryParam("token", token).build().toString();
        return Email.builder()
                .to(recipient)
                .from(fromConfiguration.getFrom())
                .subject(subject)
                .body(String.join(LINE_BREAK, instructions, link));
    }
}

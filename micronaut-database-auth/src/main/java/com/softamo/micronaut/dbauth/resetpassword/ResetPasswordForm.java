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

import com.softamo.micronaut.dbauth.constraints.PasswordMatch;
import com.softamo.micronaut.dbauth.constraints.RepeatPassword;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.views.fields.annotations.InputPassword;
import jakarta.validation.constraints.NotBlank;

/**
 * Reset Password Form.
 * @author Sergio del Amo
 * @since 0.0.1
 * @param token Reset Password Token
 * @param password New Password
 * @param repeatPassword Repeat New Password
 */
@PasswordMatch
@Serdeable
public record ResetPasswordForm(@NonNull @NotBlank String token,
                                @NonNull @NotBlank @InputPassword String password,
                                @NonNull @NotBlank @InputPassword String repeatPassword) implements RepeatPassword {
}

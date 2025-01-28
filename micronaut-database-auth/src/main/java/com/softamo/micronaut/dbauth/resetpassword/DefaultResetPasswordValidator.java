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

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpRequest;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.validator.TokenValidator;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Requires(beans = TokenValidator.class)
@Requires(classes = Mono.class)
@Singleton
class DefaultResetPasswordValidator implements ResetPasswordTokenValidator {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultResetPasswordValidator.class);
    private final List<TokenValidator<HttpRequest<?>>> validators;
    private final ExecutorService executorService;

    DefaultResetPasswordValidator(List<TokenValidator<HttpRequest<?>>> validators,
                                  @Named(TaskExecutors.BLOCKING) ExecutorService executorService) {
        this.validators = validators;
        this.executorService = executorService;
    }

    @Override
    @NonNull
    public Optional<Authentication> validate(@NonNull @NotBlank String token, HttpRequest<?> request) {
        for (TokenValidator<HttpRequest<?>> validator : validators) {
            try {
                Optional<Authentication> authenticationOptional = executorService.submit(() -> Mono.from(validator.validateToken(token, request)).blockOptional()).get();
                if (authenticationOptional.isPresent()) {
                    return authenticationOptional;
                }
            } catch (ExecutionException e) {
                LOG.warn("ExecutionException validating token {}", token, e);
            } catch (InterruptedException e) {
                LOG.warn("InterruptedException validating token {}", token, e);
            }
        }
        return Optional.empty();
    }
}

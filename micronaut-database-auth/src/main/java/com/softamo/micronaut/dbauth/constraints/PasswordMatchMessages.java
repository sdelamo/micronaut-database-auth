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
package com.softamo.micronaut.dbauth.constraints;

import io.micronaut.context.StaticMessageSource;
import jakarta.inject.Singleton;

/**
 * {@link StaticMessageSource} implementation for {@link PasswordMatch} which adds the passwords do not match message.
 * @author Sergio del Amo
 * @since 0.0.1
 */
@Singleton
public class PasswordMatchMessages extends StaticMessageSource {
    private static final String PASSWORD_MATCH_MESSAGE = "Passwords do not match";
    private static final String MESSAGE_SUFFIX = ".message";

    /**
     * Adds the message for {@link PasswordMatch} in the constructor.
     */
    public PasswordMatchMessages() {
        addMessage(PasswordMatch.class.getName() + MESSAGE_SUFFIX, PASSWORD_MATCH_MESSAGE);
    }
}

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
package com.softamo.micronaut.dbauth.i18n;

import io.micronaut.context.MessageSource;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.i18n.ResourceBundleMessageSource;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

/**
 * Factory for creating a {@link ResourceBundleMessageSource} source with the {@link Language#getMessage()} codes.
 */
@Factory
public class LanguageMessageSourceFactory {
    /**
     *
     * @return the message source
     */
    @Named("language")
    @Singleton
    public MessageSource createLanguageMessageSource() {
        return new ResourceBundleMessageSource("i18n/languages");
    }
}

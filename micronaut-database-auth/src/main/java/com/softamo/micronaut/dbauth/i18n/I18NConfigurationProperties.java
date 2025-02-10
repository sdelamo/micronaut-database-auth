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

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

import java.util.List;
import java.util.TimeZone;

@ConfigurationProperties("i18n")
class I18NConfigurationProperties implements I18nConfiguration {
    public static final TimeZone DEFAULT = TimeZone.getDefault();
    private List<Language> languages;

    @NonNull
    private TimeZone defaultTimeZone = DEFAULT;

    @NonNull
    public TimeZone getDefaultTimeZone() {
        return defaultTimeZone;
    }

    public void setDefaultTimeZone(@NonNull TimeZone defaultTimeZone) {
        this.defaultTimeZone = defaultTimeZone;
    }

    @Override
    @Nullable
    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}

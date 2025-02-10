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

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.views.fields.elements.Option;
import io.micronaut.views.fields.fetchers.OptionFetcher;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class LanguageOptionFetcher implements OptionFetcher<String> {
    private final List<Language> languages;

    public LanguageOptionFetcher(I18nConfiguration i18nConfiguration) {
        this.languages = i18nConfiguration.getLanguages();
    }

    @Override
    public List<Option> generate(@NonNull Class<String> type) {
        return languages.stream().map(lang -> createOption(lang, null)).toList();
    }

    @Override
    public List<Option> generate(@NonNull String language) {
        return languages.stream().map(lang -> createOption(lang, language)).toList();
    }

    @NonNull
    private static Option createOption(@NonNull Language language, @Nullable String selected) {
        return Option.builder()
                .selected(selected != null && selected.equals(language.name()))
                .value(language.name())
                .label(language.getMessage())
                .build();
    }
}

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
package com.softamo.micronaut.dbauth.profile;

import com.softamo.micronaut.dbauth.i18n.I18nConfiguration;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.views.fields.elements.Option;
import io.micronaut.views.fields.fetchers.OptionFetcher;
import io.micronaut.views.fields.messages.Message;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Singleton
public class TimeZoneFetcher implements OptionFetcher<TimeZone> {

    private final TimeZoneFormatter timeZoneFormatter;
    private final I18nConfiguration i18nConfiguration;

    public TimeZoneFetcher(TimeZoneFormatter timeZoneFormatter,
                           I18nConfiguration i18nConfiguration) {
        this.timeZoneFormatter = timeZoneFormatter;
        this.i18nConfiguration = i18nConfiguration;
    }

    @Override
    public List<Option> generate(@NonNull Class<TimeZone> type) {
        return generate(i18nConfiguration.getDefaultTimeZone());
    }

    @Override
    public List<Option> generate(@Nullable TimeZone instance) {
        return Arrays.stream(TimeZone.getAvailableIDs())
                .map(TimeZone::getTimeZone)
                .map(tz -> option(tz, instance))
                .toList();
    }

    @NonNull
    private Option option(@NonNull TimeZone instance, @Nullable TimeZone selected) {
        return Option.builder()
                .value(instance.getID())
                .label(Message.of(getLabel(instance)))
                .selected(selected != null && instance.getID().equals(selected.getID()))
                .build();
    }

    @NonNull
    private String getLabel(@NonNull TimeZone timeZone) {
        return timeZoneFormatter.format(timeZone);
    }
}

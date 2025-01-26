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
package example.micronaut.bootstrap;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.views.fields.messages.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Breadcrumb.
 * @see <a href="https://getbootstrap.com/docs/5.3/components/breadcrumb/">Breadcrumb</a>
 * @param label Breadcrumb Label
 * @param href Breadcrumb link
 */
@Introspected
public record Breadcrumb(
        @NotNull @Valid Message label,
        @Nullable String href
) {
    public Breadcrumb(Message label) {
        this(label, null);
    }
}


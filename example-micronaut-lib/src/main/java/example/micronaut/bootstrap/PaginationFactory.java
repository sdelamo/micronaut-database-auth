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

import io.micronaut.data.model.Page;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;

import java.util.function.Function;

/**
 * Factory class to create {@link Pagination} instances.
 */
@Singleton
public class PaginationFactory {
    private final PaginationConfiguration paginationConfiguration;

    public PaginationFactory(PaginationConfiguration paginationConfiguration) {
        this.paginationConfiguration = paginationConfiguration;
    }

    /**
     *
     * @param page Page
     * @param pageUriBuilder Page URI Builder
     * @return Bootstrap Pagination
     * @param <T> – The generic type
     */
    public <T> Pagination create(Page<T> page, Function<Integer, UriBuilder> pageUriBuilder) {
        return new Pagination(page.getTotalSize(),
                (int) page.getOffset(),
                page.getSize(),
                paginationConfiguration.getMaxNumberOfPages(),
                pageUriBuilder);
    }
}

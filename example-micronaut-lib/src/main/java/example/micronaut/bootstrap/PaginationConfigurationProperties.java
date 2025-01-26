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

import io.micronaut.context.annotation.ConfigurationProperties;

/**
 * {@link ConfigurationProperties} implementation of {@link PaginationConfiguration}.
 */
@ConfigurationProperties("bootstrap.pagination")
public class PaginationConfigurationProperties implements PaginationConfiguration {
    private static final Integer DEFAULT_MAX_NUMBER_OF_PAGES = 5;
    private Integer maxNumberOfPages = DEFAULT_MAX_NUMBER_OF_PAGES;

    @Override
    public Integer getMaxNumberOfPages() {
        return maxNumberOfPages;
    }

    /**
     *
     * @param maxNumberOfPages Max Number of Pagination Pages.
     */
    public void setMaxNumberOfPages(Integer maxNumberOfPages) {
        this.maxNumberOfPages = maxNumberOfPages;
    }
}

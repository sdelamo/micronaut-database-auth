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

import io.micronaut.http.uri.UriBuilder;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Pagination to indicate a series of related content exists across multiple pages.
 * @see <a href="https://getbootstrap.com/docs/5.3/components/pagination/">Bootstrap Pagination</a>
 */
public class Pagination {
    @Nullable
    private PageItem previous;

    @Nullable
    private PageItem next;

    private List<PageItem> items = new ArrayList<>();

    /**
     *
     * @param total Total number of results
     * @param offset Result Offset
     * @param size Size
     * @param maxPages Max number of pages in pagination.
     * @param uriFunction Function to generate a {@link UriBuilder} given a page number.
     */
    public Pagination(long total,
                      int offset,
                      int size,
                      int maxPages,
                      Function<Integer, UriBuilder> uriFunction) {
        int totalPages = (int) Math.ceil((double) total / size);
        int currentPage = (offset + size) / size;
        if ((offset - size) < 0) {
            previous = null;
        } else {
            previous = new PageItem(false, uriFunction.apply(currentPage - 2).build().toString(), currentPage - 1);
        }

        int startPage = Math.max(1, currentPage - maxPages / 2);
        int endPage = Math.min(totalPages, startPage + (maxPages - 1));
        if (startPage - maxPages > 0 && (endPage - startPage) < maxPages) {
            startPage = endPage - maxPages;
        }

        for (int i = startPage; i <= endPage; i++) {
            items.add(new PageItem(currentPage == i, uriFunction.apply((i - 1)).build().toString(), i));
        }

        if (offset + size > total) {
            next = null;
        } else {
            next = new PageItem(false, uriFunction.apply(currentPage).build().toString(), currentPage + 1);
        }
    }

    /**
     *
     * @return Pagination Items
     */
    public List<PageItem> getItems() {
        return items;
    }

    /**
     *
     * @return Previous page
     */
    @Nullable
    public PageItem getPrevious() {
        return previous;
    }

    /**
     *
     * @return Next Page
     */
    @Nullable
    public PageItem getNext() {
        return next;
    }
}


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

import io.micronaut.context.annotation.Requires;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.views.View;

import java.util.Map;

@Requires(property = "spec.name", value = "GithubReleaseControllerTest")
@Controller
class GithubReleaseController {

    private final GithubReleaseRepository repository;
    private final PaginationFactory paginationFactory;

    GithubReleaseController(GithubReleaseRepository repository,
                            PaginationFactory paginationFactory) {
        this.repository = repository;
        this.paginationFactory = paginationFactory;
    }

    @Produces(MediaType.TEXT_HTML)
    @View("index.html")
    @Get
    Map<String, Object> index(Pageable pageable) {
        Page<GitHubRelease> page = repository.findAll(pageable);
        Pagination pagination = paginationFactory.create(page, i -> UriBuilder.of("/").queryParam("page", "" + i).queryParam("size", page.getSize()));
        return Map.of(
                "pagination", pagination,
                "releases", page.getContent()
        );
    }
}

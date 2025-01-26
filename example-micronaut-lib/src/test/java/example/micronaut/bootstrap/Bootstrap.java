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
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Singleton;

import java.util.List;

@Requires(property = "spec.name", value = "GithubReleaseControllerTest")
@Singleton
class Bootstrap implements ApplicationEventListener<StartupEvent> {
    private static final List<String> REALEASES = List.of(
            "4.7.4",
            "4.7.3",
            "4.7.2",
            "4.7.1",
            "4.7.0",
            "4.6.3",
            "4.6.2",
            "4.6.1",
            "4.6.0",
            "4.5.1",
            "4.5.0",
            "4.4.3",
            "4.4.2",
            "4.4.1",
            "4.4.0",
            "4.3.8",
            "4.3.7",
            "4.3.6",
            "4.3.5",
            "4.3.4",
            "4.3.3",
            "4.3.2",
            "4.3.1",
            "4.3.0",
            "4.2.4",
            "4.2.3",
            "4.2.2",
            "4.2.1",
            "4.2.0",
            "4.1.6",
            "4.1.5",
            "4.1.4",
            "4.1.3",
            "4.1.2",
            "4.1.1",
            "4.1.0",
            "4.0.6",
            "4.0.5",
            "4.0.4",
            "4.0.3",
            "4.0.2",
            "4.0.1",
            "4.0.0",
            "3.10.1",
            "3.10.0",
            "3.9.6",
            "3.9.5",
            "3.9.4",
            "3.9.3",
            "3.9.2",
            "3.9.1",
            "3.9.0",
            "3.8.12",
            "3.8.11",
            "3.8.10",
            "3.8.9",
            "3.8.8",
            "3.8.7",
            "3.8.6",
            "3.8.5",
            "3.8.4",
            "3.8.3",
            "3.8.2",
            "3.8.1",
            "3.8.0",
            "3.7.7",
            "3.7.6",
            "3.7.5",
            "3.7.4",
            "3.7.3",
            "3.7.2",
            "3.7.1",
            "3.7.0",
            "3.6.6",
            "3.6.5",
            "3.6.4",
            "3.6.3",
            "3.6.2",
            "3.6.1",
            "3.6.0",
            "3.5.7",
            "3.5.6",
            "3.5.5",
            "3.5.4",
            "3.5.3",
            "3.5.2",
            "3.5.1",
            "3.5.0",
            "3.4.4",
            "3.4.3",
            "3.4.2",
            "3.4.1",
            "3.4.0",
            "3.3.4",
            "3.3.3",
            "3.3.1",
            "3.3.0",
            "3.2.7",
            "3.2.6",
            "3.2.5",
            "3.2.4",
            "2.5.13",
            "2.5.12",
            "2.5.11",
            "2.5.10",
            "2.5.9",
            "2.5.8",
            "2.5.7",
            "2.5.6",
            "2.5.5",
            "2.5.4",
            "2.5.3",
            "2.5.2",
            "2.5.1",
            "2.5.0",
            "2.4.4",
            "2.4.3",
            "2.4.2",
            "2.4.1",
            "2.4.0",
            "2.3.4",
            "2.3.3",
            "2.3.2",
            "2.3.1",
            "2.3.0",
            "2.2.3",
            "2.2.2",
            "2.2.1",
            "2.2.0",
            "2.1.4",
            "2.1.3",
            "2.1.2",
            "2.1.1",
            "2.1.0",
            "2.0.3",
            "2.0.2",
            "2.0.1",
            "2.0.0",
            "1.3.7",
            "1.3.6",
            "1.3.5",
            "1.3.4",
            "1.3.3",
            "1.3.2",
            "1.3.1",
            "1.3.0",
            "1.2.11",
            "1.2.10",
            "1.2.9",
            "1.2.8",
            "1.2.7",
            "1.2.6",
            "1.2.5",
            "1.2.4",
            "1.2.3",
            "1.2.2",
            "1.2.1",
            "1.2.0",
            "1.1.4",
            "1.1.3",
            "1.1.2",
            "1.1.1",
            "1.1.0",
            "1.0.5",
            "1.0.4",
            "1.0.3",
            "1.0.2",
            "1.0.1"
    );
    private final GithubReleaseRepository repository;

    Bootstrap(GithubReleaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(StartupEvent event) {
        for (String version : REALEASES) {
            repository.save(new GitHubRelease(null, version));
        }
    }
}

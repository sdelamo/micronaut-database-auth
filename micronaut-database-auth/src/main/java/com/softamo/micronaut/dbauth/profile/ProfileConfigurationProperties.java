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

import io.micronaut.context.annotation.ConfigurationProperties;

/**
 * {@link ConfigurationProperties} implementation of {@link ProfileConfiguration}.
 */
@ConfigurationProperties(ProfileConfigurationProperties.PREFIX)
public class ProfileConfigurationProperties implements ProfileConfiguration {
    public static final String PREFIX = "profile";
    public static final String DEFAULT_PATH = "/profile";
    public static final String DEFAULT_EDIT_VIEW = "profile/edit.html";
    public static final String DEFAULT_EDIT_PATH = "/edit";
    private boolean enabled = true;
    private String path = DEFAULT_PATH;
    private String editPath = DEFAULT_EDIT_PATH;
    private String editView = DEFAULT_EDIT_VIEW;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled Whether the forgot password controller is enabled. Default value (true).
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getEditPath() {
        return editPath;
    }

    /**
     *
     * @param editPath Profile Controller edit path
     */
    public void setEditPath(String editPath) {
        this.editPath = editPath;
    }

    @Override
    public String getEditView() {
        return editView;
    }

    /**
     *
     * @param editView Profile Controller edit view
     */
    public void setEditView(String editView) {
        this.editView = editView;
    }

    @Override
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path Profile Controller path
     */
    public void setPath(String path) {
        this.path = path;
    }
}

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
package com.softamo.micronaut.dbauth.signup;

import com.softamo.micronaut.dbauth.utils.ControllerConfiguration;
import io.micronaut.context.annotation.ConfigurationProperties;

/**
 * Configuration for the Signup Controller.
 *
 * @author Sergio del Amo
 * @since 0.0.1
 */
@ConfigurationProperties(SignupConfigurationProperties.PREFIX)
class SignupConfigurationProperties implements SignupConfiguration {
    public static final String PREFIX = ControllerConfiguration.PREFIX + ".signup";
    public static final String DEFAULT_PATH = "/signup";
    private boolean enabled = true;
    private String path = DEFAULT_PATH;
    private String view = "signup.html";

    /**
     *
     * @return View name to render the forgot password form.
     */
    public String getView() {
        return view;
    }

    /**
     *
     * @param view View name to render the forgot password form.
     */
    public void setView(String view) {
        this.view = view;
    }

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
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path Forgot Password Controller path
     */
    public void setPath(String path) {
        this.path = path;
    }
}


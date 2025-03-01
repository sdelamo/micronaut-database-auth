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
package com.softamo.micronaut.dbauth.forgotpassword;

import com.softamo.micronaut.dbauth.utils.ControllerConfiguration;
import io.micronaut.context.annotation.ConfigurationProperties;

/**
 * Configuration for the Forgot Password Controller.
 *
 * @author Sergio del Amo
 * @since 0.0.1
 */
@ConfigurationProperties(ForgotPasswordConfigurationProperties.PREFIX)
class ForgotPasswordConfigurationProperties implements ForgotPasswordConfiguration {
    public static final String PREFIX = ControllerConfiguration.PREFIX + ".forgotpassword";
    private boolean enabled = true;
    private String path = "/forgotPassword";
    private String view = "forgotPassword.html";
    private String instructionsView = "forgotPasswordInstructions.html";

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

    /**
     *
     * @return View name to render the forgot password instructions page. The view after submitting the forgot password form. Typically, asking the user to check his email..
     */
    public String getInstructionsView() {
        return instructionsView;
    }

    /**
     *
     * @param instructionsView View name to render the forgot password instructions page. The view after submitting the forgot password form. Typically, asking the user to check his email..
     */
    public void setInstructionsView(String instructionsView) {
        this.instructionsView = instructionsView;
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

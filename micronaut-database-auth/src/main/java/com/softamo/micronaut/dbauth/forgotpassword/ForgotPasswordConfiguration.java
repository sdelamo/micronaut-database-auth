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

/**
 * Forgot Password Configuration.
 * @author Sergio del Amo
 * @since 0.0.1
 */
public interface ForgotPasswordConfiguration extends ControllerConfiguration {
    /**
     *
     * @return View name to render the forgot password form.
     */
    String getView();

    /**
     *
     * @return View name to render the forgot password instructions page. The view after submitting the forgot password form. Typically, asking the user to check his email..
     */
    String getInstructionsView();
}

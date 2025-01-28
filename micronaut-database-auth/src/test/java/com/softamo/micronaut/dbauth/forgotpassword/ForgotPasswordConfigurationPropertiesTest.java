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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForgotPasswordConfigurationPropertiesTest {

    @Test
    void configurationSetters() {
        ForgotPasswordConfigurationProperties configuration = new ForgotPasswordConfigurationProperties();
        configuration.setEnabled(true);
        configuration.setPath("/foo");
        configuration.setView("/viewbar");
        configuration.setInstructionsView("/viewbarfoo");
        assertTrue(configuration.isEnabled());
        assertEquals("/foo", configuration.getPath());
        assertEquals("/viewbar", configuration.getView());
        assertEquals("/viewbarfoo", configuration.getInstructionsView());
    }
}

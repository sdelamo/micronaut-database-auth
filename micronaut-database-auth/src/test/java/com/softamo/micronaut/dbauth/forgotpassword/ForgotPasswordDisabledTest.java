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

import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.util.StringUtils;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "dbauth.forgotpassword.enabled", value = StringUtils.FALSE)
@MicronautTest(startApplication = false)
class ProfileConfigurationDisabledTest {

    @Inject
    BeanContext beanContext;

    @Test
    void forgotPasswordBeansCanBeDisabled() {
        assertFalse(beanContext.containsBean(ForgotPasswordEmailComposer.class));
        assertFalse(beanContext.containsBean(ForgotPasswordConfiguration.class));
        assertFalse(beanContext.containsBean(ForgotPasswordController.class));
        assertFalse(beanContext.containsBean(ForgotPasswordFormService.class));
    }
}

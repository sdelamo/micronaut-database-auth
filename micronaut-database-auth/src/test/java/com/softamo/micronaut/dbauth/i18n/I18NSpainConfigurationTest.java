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
package com.softamo.micronaut.dbauth.i18n;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Property(name = "i18n.languages[0]", value = "SPANISH")
@Property(name = "i18n.languages[1]", value = "GALICIAN")
@Property(name = "i18n.languages[2]", value = "CATALAN")
@Property(name = "i18n.languages[3]", value = "BASQUE")
@Property(name = "i18n.languages[4]", value = "VALENCIAN")
@MicronautTest(startApplication = false)
class I18NSpainConfigurationTest {

    @Test
    void languagesConfiguration(I18nConfiguration configuration) {
        assertEquals(5, configuration.getLanguages().size());
    }
}

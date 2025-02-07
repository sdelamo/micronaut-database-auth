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

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.fields.Form;
import io.micronaut.views.fields.FormGenerator;

import java.util.Collections;
import java.util.Map;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Requires(property = ProfileConfigurationProperties.PREFIX + ".enabled", notEquals = StringUtils.FALSE, defaultValue = StringUtils.TRUE)
@Requires(beans = {ProfileConfiguration.class, FormGenerator.class})
@Requires(classes = Controller.class)
@Controller("${" + ProfileConfigurationProperties.PREFIX + ".path:" + ProfileConfigurationProperties.DEFAULT_PATH + "}")
class ProfileController {
    private static final String KEY_FORM = "form";
    private final ProfileConfiguration profileConfiguration;
    private final FormGenerator formGenerator;

    ProfileController(ProfileConfiguration profileConfiguration,
                      FormGenerator formGenerator) {
        this.profileConfiguration = profileConfiguration;
        this.formGenerator = formGenerator;
    }

    @Get("${" + ProfileConfigurationProperties.PREFIX + ".edit-path:" + ProfileConfigurationProperties.DEFAULT_EDIT_PATH + "}")
    @Produces(MediaType.TEXT_HTML)
    ModelAndView<Map<String, Object>> edit() {
        Form form = formGenerator.generate(profileConfiguration.getPath(), ProfileEditForm.class);
        return new ModelAndView<>(profileConfiguration.getEditView(), Collections.singletonMap(KEY_FORM, form));
    }
}

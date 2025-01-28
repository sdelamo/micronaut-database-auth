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
package com.softamo.micronaut.dbauth.resetpassword;

import com.softamo.micronaut.dbauth.ViewsUtils;
import com.softamo.micronaut.dbauth.forgotpassword.ForgotPasswordForm;
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

import java.util.Map;

@Requires(property = ResetPasswordConfigurationProperties.PREFIX + ".enabled", notEquals = StringUtils.FALSE, defaultValue = StringUtils.TRUE)
@Controller("${" + ResetPasswordConfigurationProperties.PREFIX + ".path:/resetPassword}")
@Requires(beans = {FormGenerator.class, ResetPasswordConfiguration.class})
@Secured(SecurityRule.IS_ANONYMOUS)
class ResetPasswordController {
    private final ResetPasswordConfiguration resetPasswordConfiguration;
    private final FormGenerator formGenerator;

    ResetPasswordController(ResetPasswordConfiguration resetPasswordConfiguration,
                            FormGenerator formGenerator) {
        this.resetPasswordConfiguration = resetPasswordConfiguration;
        this.formGenerator = formGenerator;
    }

    @Produces(MediaType.TEXT_HTML)
    @Get
    ModelAndView<Map<String, Object>> resetPassword() {
        Form form = formGenerator.generate(resetPasswordConfiguration.getPath(), ForgotPasswordForm.class);
        return new ModelAndView<>(resetPasswordConfiguration.getView(),
                Map.of(ViewsUtils.KEY_FORM, form));
    }
}

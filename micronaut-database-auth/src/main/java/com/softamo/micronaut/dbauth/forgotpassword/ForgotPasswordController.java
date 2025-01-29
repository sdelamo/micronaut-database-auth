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

import com.softamo.micronaut.dbauth.utils.ViewsUtils;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.server.util.HttpHostResolver;
import io.micronaut.http.server.util.locale.HttpLocaleResolver;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.fields.Form;
import io.micronaut.views.fields.FormGenerator;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

@Requires(property = ForgotPasswordConfigurationProperties.PREFIX + ".enabled", notEquals = StringUtils.FALSE, defaultValue = StringUtils.TRUE)
@Controller("${" + ForgotPasswordConfigurationProperties.PREFIX + ".path:/forgotPassword}")
@Requires(beans = {HttpLocaleResolver.class, HttpHostResolver.class, FormGenerator.class, ForgotPasswordConfiguration.class, ForgotPasswordFormService.class})
@Secured(SecurityRule.IS_ANONYMOUS)
class ForgotPasswordController {
    private final HttpLocaleResolver httpLocaleResolver;
    private final FormGenerator formGenerator;
    private final ForgotPasswordConfiguration forgotPasswordConfiguration;
    private final ForgotPasswordFormService forgotPasswordFormService;
    private final HttpHostResolver httpHostResolver;

    ForgotPasswordController(HttpLocaleResolver httpLocaleResolver,
                             FormGenerator formGenerator,
                             ForgotPasswordConfiguration forgotPasswordConfiguration,
                             ForgotPasswordFormService forgotPasswordFormService,
                             HttpHostResolver httpHostResolver) {
        this.httpLocaleResolver = httpLocaleResolver;
        this.formGenerator = formGenerator;
        this.forgotPasswordConfiguration = forgotPasswordConfiguration;
        this.forgotPasswordFormService = forgotPasswordFormService;
        this.httpHostResolver = httpHostResolver;
    }

    @Produces(MediaType.TEXT_HTML)
    @Get
    ModelAndView<Map<String, Object>> forgotPassword() {
        Form form = formGenerator.generate(forgotPasswordConfiguration.getPath(), ForgotPasswordForm.class);
        return new ModelAndView<>(forgotPasswordConfiguration.getView(),
                Map.of(ViewsUtils.KEY_FORM, form));
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post
    ModelAndView<Map<String, Object>> forgotPasswordSubmit(@NonNull @NotNull @Valid @Body ForgotPasswordForm forgotPasswordForm,
                                             HttpRequest<?> request) {
        Locale locale = httpLocaleResolver.resolveOrDefault(request);
        String host = httpHostResolver.resolve(request);
        forgotPasswordFormService.handleForgotPasswordFormSubmission(locale, host, forgotPasswordForm);
        return new ModelAndView<>(forgotPasswordConfiguration.getInstructionsView(),
                Collections.emptyMap());
    }

    @Error
    public HttpResponse<ModelAndView<Map<String, Object>>> error(ConstraintViolationException e) {
        return HttpResponse.unprocessableEntity().body(forgotPassword());
    }

}

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

import com.softamo.micronaut.dbauth.utils.ViewsUtils;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.config.UnauthorizedRedirectConfiguration;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.fields.Form;
import io.micronaut.views.fields.FormGenerator;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Requires(property = SignupConfigurationProperties.PREFIX + ".enabled", notEquals = StringUtils.FALSE, defaultValue = StringUtils.TRUE)
@Requires(beans = {SignupConfiguration.class, FormGenerator.class, SignupService.class})
@Requires(classes = Controller.class)
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("${" + SignupConfigurationProperties.PREFIX + ".path:" + SignupConfigurationProperties.DEFAULT_PATH + "}")
class SignupController {
    private final SignupConfiguration signupConfiguration;
    private final FormGenerator formGenerator;
    private final SignupService signupService;
    private final UnauthorizedRedirectConfiguration unauthorizedRedirectConfiguration;

    SignupController(SignupConfiguration signupConfiguration,
                     FormGenerator formGenerator,
                     SignupService signupService,
                     UnauthorizedRedirectConfiguration unauthorizedRedirectConfiguration) {
        this.signupConfiguration = signupConfiguration;
        this.formGenerator = formGenerator;
        this.signupService = signupService;
        this.unauthorizedRedirectConfiguration = unauthorizedRedirectConfiguration;
    }

    @Produces(MediaType.TEXT_HTML)
    @Get
    ModelAndView<Map<String, Object>> signup() {
        return signup(formGenerator.generate(signupConfiguration.getPath(), SignUpForm.class));
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post
    HttpResponse<?> signupSubmit(@NonNull @NotNull @Valid @Body SignUpForm signUpForm) {
        signupService.signup(signUpForm);
        return HttpResponse.seeOther(URI.create(unauthorizedRedirectConfiguration.getUrl()));
    }

    @Error
    HttpResponse<ModelAndView<Map<String, Object>>> error(ConstraintViolationException e, HttpRequest<?> request) {
        Optional<SignUpForm> formOptional = request.getBody(SignUpForm.class);
        if (formOptional.isPresent()) {
            return HttpResponse.unprocessableEntity().body(signup(formGenerator.generate(signupConfiguration.getPath(), formOptional.get(), e)));
        }
        return HttpResponse.unprocessableEntity();
    }

    private ModelAndView<Map<String, Object>> signup(Form form) {
        return new ModelAndView<>(signupConfiguration.getView(),
                Map.of(ViewsUtils.KEY_FORM, form));
    }
}

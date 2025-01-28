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

import com.softamo.micronaut.dbauth.utils.ViewsUtils;
import com.softamo.micronaut.dbauth.forgotpassword.ForgotPasswordForm;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.endpoints.LoginControllerConfiguration;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.fields.Form;
import io.micronaut.views.fields.FormGenerator;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Requires(property = ResetPasswordConfigurationProperties.PREFIX + ".enabled", notEquals = StringUtils.FALSE, defaultValue = StringUtils.TRUE)
@Requires(beans = {LoginControllerConfiguration.class, ResetPasswordConfiguration.class, FormGenerator.class, ResetPasswordService.class, ResetPasswordTokenValidator.class})
@Requires(classes = Controller.class)
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("${" + ResetPasswordConfigurationProperties.PREFIX + ".path:/resetPassword}")
class ResetPasswordController {
    private final LoginControllerConfiguration loginControllerConfiguration;
    private final ResetPasswordConfiguration resetPasswordConfiguration;
    private final FormGenerator formGenerator;
    private final ResetPasswordService resetPasswordService;
    private final ResetPasswordTokenValidator resetPasswordTokenValidator;

    ResetPasswordController(LoginControllerConfiguration loginControllerConfiguration,
                            ResetPasswordConfiguration resetPasswordConfiguration,
                            FormGenerator formGenerator,
                            ResetPasswordService resetPasswordService,
                            ResetPasswordTokenValidator resetPasswordTokenValidator) {
        this.loginControllerConfiguration = loginControllerConfiguration;
        this.resetPasswordConfiguration = resetPasswordConfiguration;
        this.formGenerator = formGenerator;
        this.resetPasswordService = resetPasswordService;
        this.resetPasswordTokenValidator = resetPasswordTokenValidator;
    }

    @Produces(MediaType.TEXT_HTML)
    @Get
    HttpResponse<ModelAndView<Map<String, Object>>> resetPassword(@QueryValue @NotBlank String token,
                                                                  @NonNull HttpRequest<?> request) {
        Optional<Authentication> authenticationOptional = resetPasswordTokenValidator.validate(token, request);
        if (authenticationOptional.isEmpty()) {
            return HttpResponse.notFound();
        }
        Form form = formGenerator.generate(resetPasswordConfiguration.getPath(), ForgotPasswordForm.class);
        return HttpResponse.ok(new ModelAndView<>(resetPasswordConfiguration.getView(),
                Map.of(ViewsUtils.KEY_FORM, form)));
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post
    HttpResponse<?> resetPasswordSubmit(@NonNull @NotNull @Valid @Body ResetPasswordForm resetPasswordForm,
                                                          @NonNull HttpRequest<?> request) {
        Optional<Authentication> authenticationOptional = resetPasswordTokenValidator.validate(resetPasswordForm.token(), request);
        if (authenticationOptional.isEmpty()) {
            return HttpResponse.badRequest();
        }
        Authentication authentication = authenticationOptional.get();
        resetPasswordService.resetPassword(authentication.getName(), resetPasswordForm.password());
        return HttpResponse.seeOther(URI.create(loginControllerConfiguration.getPath()));
    }

    @Error
    public HttpResponse<ModelAndView<Map<String, Object>>> error(ConstraintViolationException e) {
        return HttpResponse.badRequest();
    }
}

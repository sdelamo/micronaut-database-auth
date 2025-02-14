package com.softamo.micronaut.dbauth.ui.pages;

import geb.Module
import geb.module.EmailInput;

class ForgotPasswordModule extends Module {
    static content = {
        emailInput { $("input", name: 'email').module(EmailInput) }
        inputSubmit { $("input", type: 'submit') }
    }

    void setEmail(String email) {
        emailInput.value(email)
    }

    void submit() {
        inputSubmit.click()
    }
}

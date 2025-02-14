package com.softamo.micronaut.dbauth.ui.pages

import geb.Page

class ForgotPasswordPage extends Page {
    static url = "/forgotPassword"
    static at = { title == "Forgot Password" }
    static content = {
        form { $("form", action: '/forgotPassword').module(ForgotPasswordModule) }
    }
}

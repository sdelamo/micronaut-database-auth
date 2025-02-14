package com.softamo.micronaut.dbauth.ui

import com.softamo.micronaut.dbauth.ui.pages.ForgotPasswordInstructionsPage
import com.softamo.micronaut.dbauth.ui.pages.ForgotPasswordPage
import geb.Browser
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.IgnoreIf
import spock.lang.Specification

class ForgotPasswordUiSpec extends Specification {

    @IgnoreIf({ env['CI'] })
    void "forgot password form should be displayed"() {
        given:
        Map<String, Object> config = Map.of("micronaut.email.from.email", "sender@email.com")
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer, config)
        Browser browser = new Browser()
        browser.baseUrl = server.getURL().toString()

        when: 'go to forgot password page'
        browser.to(ForgotPasswordPage)

        then: 'browser is at forgot password page'
        browser.at(ForgotPasswordPage)

        when: 'submit with a valid email, you are presented with a instructions page'
        ForgotPasswordPage page = browser.page(ForgotPasswordPage)
        page.form.email = "sergio@example.com"
        page.form.submit()

        then:
        browser.at(ForgotPasswordInstructionsPage)

        when: 'submit with an in valid email, form validation fails and you are presented with the forgot password page'
        page = browser.to(ForgotPasswordPage)
        page.form.email = ""
        page.form.submit()

        then:
        browser.at(ForgotPasswordPage)

        cleanup:
        server.stop()
    }
}

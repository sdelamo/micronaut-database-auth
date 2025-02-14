package com.softamo.micronaut.dbauth.ui.stubs

import io.micronaut.core.annotation.NonNull
import io.micronaut.email.Email
import io.micronaut.email.EmailException
import io.micronaut.email.EmailSender
import jakarta.inject.Named
import jakarta.validation.constraints.NotNull
import jakarta.inject.Singleton
import java.util.function.Consumer

@Named("mockemail")
@Singleton
class MockEmailSender<I, O> implements EmailSender<I, O> {

    @Override
    O send(@NonNull @NotNull Email.Builder emailBuilder,
           @NonNull @NotNull Consumer<I> emailRequest) throws EmailException {
        return null
    }

    @Override
    String getName() {
        "mockemail"
    }
}

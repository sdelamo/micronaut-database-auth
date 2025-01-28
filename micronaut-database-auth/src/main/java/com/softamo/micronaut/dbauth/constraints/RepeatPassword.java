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
package com.softamo.micronaut.dbauth.constraints;

/**
 * API for form which require password repetition. For example, signup forms, reset password forms or change password forms.
 * @since 0.0.1
 * @author Sergio del Amo
 */
public interface RepeatPassword {
    /**
     *
     * @return Password
     */
    String password();

    /**
     *
     * @return Repeated Password
     */
    String repeatPassword();
}

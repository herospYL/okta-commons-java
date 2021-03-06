/*
 * Copyright 2018-Present Okta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.okta.commons.configcheck;

import java.util.function.Consumer;

public class ValidationResponse {

    private boolean valid = true;
    private String message;
    private Exception exception;

    ValidationResponse() {}

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    public ValidationResponse setMessage(String message) {
        this.message = message;
        this.valid = false;
        return this;
    }

    public void ifInvalidThrow() {
        ifInvalid(res -> {
            throw new IllegalArgumentException(message, exception);
        });
    }

    public void ifInvalid(Consumer<ValidationResponse> consumer) {
        if (!valid) {
            consumer.accept(this);
        }
    }

    public Exception getException() {
        return exception;
    }

    public ValidationResponse setException(Exception exception) {
        this.exception = exception;
        return this;
    }

    static ValidationResponse valid() {
        return new ValidationResponse();
    }
}

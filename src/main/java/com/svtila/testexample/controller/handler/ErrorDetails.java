package com.svtila.testexample.controller.handler;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private String path;

    private ErrorDetails(ErrorDetailsBuilder builder) {
        this.timestamp = builder.timestamp;
        this.message = builder.message;
        this.details = builder.details;
        this.path = builder.path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getPath() {
        return path;
    }

    public static class ErrorDetailsBuilder {
        private LocalDateTime timestamp;
        private String message;
        private String details;
        private String path;

        public ErrorDetailsBuilder() {
            this.timestamp = LocalDateTime.now();
        }

        public ErrorDetailsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorDetailsBuilder details(String details) {
            this.details = details;
            return this;
        }

        public ErrorDetailsBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorDetails build(){
            return new ErrorDetails(this);
        }

    }
}


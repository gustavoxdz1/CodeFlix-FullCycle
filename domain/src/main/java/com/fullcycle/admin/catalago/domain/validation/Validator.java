package com.fullcycle.admin.catalago.domain.validation;

public abstract class Validator {

    private final ValidationHandler handler;

    protected Validator(final ValidationHandler aHandler) {
        this.handler = aHandler;
    }

    public abstract void Validate();

    protected  ValidationHandler validationHandler() {
        return this.handler;
    }

}

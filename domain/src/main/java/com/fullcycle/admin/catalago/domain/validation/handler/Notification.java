package com.fullcycle.admin.catalago.domain.validation.handler;

import com.fullcycle.admin.catalago.domain.exceptions.DomainException;
import com.fullcycle.admin.catalago.domain.validation.Error;
import com.fullcycle.admin.catalago.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {

    private final List<Error> errors;

    private Notification(final List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }


    public static Notification create(final Throwable throwable) {
        return create(new Error(throwable.getMessage()));
    }

    public static Notification create(final Error anError) {
        return new Notification(new ArrayList<>()).append(anError);
    }

    @Override
    public Notification append(final Error anError) {
        this.errors.add(anError);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler anHandler) {
        this.errors.addAll(anHandler.getErrors());
        return this;
    }

    @Override
    public Notification validate(final Validation aValidation) {

        try {
            aValidation.validate();
        } catch (final DomainException ex) {
            this.errors.addAll(ex.getErrors());
        } catch (final Throwable throwable) {
            this.errors.add(new Error(throwable.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }
}

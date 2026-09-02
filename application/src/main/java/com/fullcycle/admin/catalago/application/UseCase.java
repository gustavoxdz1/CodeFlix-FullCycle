package com.fullcycle.admin.catalago.application;

import com.fullcycle.admin.catalago.application.category.create.CreateCategoryOutput;
import com.fullcycle.admin.catalago.domain.validation.handler.Notification;
import io.vavr.control.Either;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public abstract class UseCase <IN, OUT> {

    public abstract Either<Notification, CreateCategoryOutput> execute(IN anIn );
}

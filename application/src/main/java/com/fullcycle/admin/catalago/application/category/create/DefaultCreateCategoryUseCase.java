package com.fullcycle.admin.catalago.application.category.create;

import com.fullcycle.admin.catalago.application.UseCase;
import com.fullcycle.admin.catalago.domain.category.Category;
import com.fullcycle.admin.catalago.domain.category.CategoryGateway;
import com.fullcycle.admin.catalago.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends UseCase<CreateCategoryCommand, CreateCategoryOutput> {

    private final CategoryGateway categoryGateway;
    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var aName =  aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();

        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(notification);

        return notification.hasError() ? Either.left(notification): create(aCategory);
        }

        private Either<Notification, CreateCategoryOutput> create(final Category aCategory) {
           return API.Try(() -> this.categoryGateway.create(aCategory))
                    .toEither()
                    .bimap(Notification::create, CreateCategoryOutput::from);
        }
    }



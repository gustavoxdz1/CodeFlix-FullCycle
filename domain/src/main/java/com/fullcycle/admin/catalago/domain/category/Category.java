package com.fullcycle.admin.catalago.domain.category;

import com.fullcycle.admin.catalago.domain.AggregateRoot;
import com.fullcycle.admin.catalago.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class Category extends AggregateRoot <CategoryID> {

    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updateAt;
    private Instant deletedAt;

    private Category(
            final CategoryID anId,
            final String aName,
            final String aDescription,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    ) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = isActive;
        this.createdAt = aCreationDate;
        this.updateAt = aUpdateDate;
        this.deletedAt = aDeleteDate;


    }

    public static Category newCategory(final String aName, final String aDescription, final boolean aIsActive) {
        final var id = CategoryID.unique();
        final var now = Instant.now();
        final var deletedAt = aIsActive ? null : now;
        return new Category(id, aName, aDescription, aIsActive, now, now, deletedAt );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).Validate();
    }

    public Category activate() {
        this.deletedAt = null;
        this.active = true;
        this.updateAt = Instant.now();
        return this;
        }


    public Category deactivate() {
        if(getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updateAt = Instant.now();
        return this;
    }

    public  Category update(
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = aName;
        this.description = aDescription;
        this.updateAt = Instant.now();
        return this;
    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}

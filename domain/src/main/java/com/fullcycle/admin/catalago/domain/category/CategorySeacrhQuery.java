package com.fullcycle.admin.catalago.domain.category;

public record CategorySeacrhQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}

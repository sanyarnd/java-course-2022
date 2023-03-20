package ru.tinkoff.lecture.testing.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public record GetClientsRequest(int limit, int offset, String sort, String lastName) {

    private static final String DESC_ORDER = "desc";

    public Pageable page() {
        return page(this);
    }

    @Getter
    public static class WastedPageRequest extends PageRequest {

        private final int headWaste;
        private final int tailWaste;

        public WastedPageRequest(
                int page, int size, Sort sort,
                int headWaste, int tailWaste) {

            super(page, size, sort);
            this.headWaste = headWaste;
            this.tailWaste = tailWaste;
        }

    }

    private static Pageable page(GetClientsRequest request) {
        for (int window = request.limit; window <= request.limit + request.offset; window++) {
            for (int leftShift = 0; leftShift <= window - request.limit; leftShift++) {
                if ((request.offset - leftShift) % window == 0) {
                    int page = (request.offset - leftShift) / window;
                    int tailWaste = ((page + 1) * window) - (request.offset + request.limit);
                    return new WastedPageRequest(page, window, sort(request), leftShift, tailWaste);
                }
            }
        }
        return Pageable.unpaged();
    }

    private static Sort sort(GetClientsRequest request) {
        var sortingParams = request.sort().split(",");

        var field = sortingParams[0];
        if (sortingParams.length > 1) {
            var order = DESC_ORDER.equals(sortingParams[1])
                    ? Sort.Order.desc(field)
                    : Sort.Order.asc(field);
            return Sort.by(order);
        }
        return Sort.by(field);
    }

}

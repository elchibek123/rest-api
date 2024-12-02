package java15.library.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BookResponse(
        Long id,
        String name,
        String author,
        int publishedYear,
        String description,
        BigDecimal price
) {
}

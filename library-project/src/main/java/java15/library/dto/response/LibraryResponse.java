package java15.library.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record LibraryResponse(
        Long id,
        String name,
        String address,
        List<BookResponse> books
) {
}

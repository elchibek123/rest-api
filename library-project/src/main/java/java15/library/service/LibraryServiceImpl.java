package java15.library.service;

import jakarta.transaction.Transactional;
import java15.library.dto.request.LibraryRequest;
import java15.library.dto.response.BookResponse;
import java15.library.dto.response.LibraryResponse;
import java15.library.dto.response.SimpleResponse;
import java15.library.entity.Book;
import java15.library.entity.Library;
import java15.library.repository.BookRepository;
import java15.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    @Override
    public List<LibraryResponse> findAll() {
        return libraryRepository.findAll().stream()
                .map(library -> LibraryResponse.builder()
                        .id(library.getId())
                        .name(library.getName())
                        .address(library.getAddress())
                        .books(getBookResponses(library.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    public List<BookResponse> getBookResponses(Long libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found with ID: " + libraryId));

        return library.getBooks().stream()
                .map(book -> BookResponse.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .author(book.getAuthor())
                        .publishedYear(book.getPublishedYear())
                        .description(book.getDescription())
                        .price(book.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public SimpleResponse save(LibraryRequest libraryRequest) {
        Library library = new Library();
        library.setName(libraryRequest.name());
        library.setAddress(libraryRequest.address());
        libraryRepository.save(library);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Library successfully saved")
                .build();
    }

    @Override
    public SimpleResponse assignBookToLibrary(Long libraryId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found with ID: " + libraryId));

        book.setLibrary(library);
        bookRepository.save(book);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success")
                .build();
    }

    @Override
    public LibraryResponse updateLibrary(Long id, LibraryRequest libraryRequest) {
        Library foundLibrary = libraryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Library not found with ID: " + id));
        foundLibrary.setName(libraryRequest.name());
        foundLibrary.setAddress(libraryRequest.address());
        libraryRepository.save(foundLibrary);
        return LibraryResponse.builder()
                .id(foundLibrary.getId())
                .name(foundLibrary.getName())
                .address(foundLibrary.getAddress())
                .build();
    }
}

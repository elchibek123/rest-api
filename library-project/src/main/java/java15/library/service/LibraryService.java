package java15.library.service;

import java15.library.dto.request.LibraryRequest;
import java15.library.dto.response.LibraryResponse;
import java15.library.dto.response.SimpleResponse;
import java15.library.entity.Book;
import java15.library.entity.Library;

import java.util.List;

public interface LibraryService {
    List<LibraryResponse> findAll();

    SimpleResponse save(LibraryRequest libraryRequest);

    SimpleResponse assignBookToLibrary(Long libraryId, Long bookId);

    LibraryResponse updateLibrary(Long id, LibraryRequest libraryRequest);
}

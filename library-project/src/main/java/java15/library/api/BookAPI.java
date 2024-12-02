package java15.library.api;

import java15.library.dto.response.SimpleResponse;
import java15.library.entity.Book;
import java15.library.service.BookService;
import java15.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookAPI {
    private final BookService bookService;
    private final LibraryService libraryService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/{id}/find")
    public Book findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PutMapping("/assign")
    public SimpleResponse assign(@RequestParam Long libraryId, @RequestParam Long bookId) {
        return libraryService.assignBookToLibrary(libraryId, bookId);
    }
}

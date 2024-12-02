package java15.library.service;

import java15.library.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book save(Book book);

    Book findById(Long id);
}

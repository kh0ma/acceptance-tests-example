package com.kh0ma.playground.acceptance.test.example.service.book;

import java.util.UUID;

import com.kh0ma.playground.acceptance.test.example.data.entity.Book;
import com.kh0ma.playground.acceptance.test.example.data.repository.BookRepository;
import com.kh0ma.playground.acceptance.test.example.service.IdGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final IdGeneratorService idGeneratorService;

    public Book create(Book book) {
        book.setId(idGeneratorService.generateUuid());
        return bookRepository.save(book);
    }

    public Book update(UUID id, Book book) {
        val existingBook = get(id);
        existingBook.setName(book.getName());
        return bookRepository.save(existingBook);
    }

    public void delete(UUID id) {
        val existingBook = get(id);
        bookRepository.delete(existingBook);
    }

    public Book get(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow();
    }

    public Page<Book> getBooks(UUID id, String name, Pageable pageable) {
        return bookRepository.findAll(
                Example.of(
                        Book.builder()
                                .id(id)
                                .name(name)
                                .build()
                ),
                pageable
        );
    }
}

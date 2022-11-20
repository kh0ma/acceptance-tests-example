package com.kh0ma.playground.acceptance.test.example.api.v1.facade.book;

import java.util.List;
import java.util.UUID;

import com.kh0ma.playground.acceptance.test.example.api.v1.controller.model.BookDto;
import com.kh0ma.playground.acceptance.test.example.api.v1.controller.model.CreateBookDto;
import com.kh0ma.playground.acceptance.test.example.api.v1.controller.model.UpdateBookDto;
import com.kh0ma.playground.acceptance.test.example.data.entity.Book;
import com.kh0ma.playground.acceptance.test.example.service.book.BookService;
import com.kh0ma.playground.acceptance.test.example.util.PageUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@Component
@RequiredArgsConstructor
public class BookFacade {
    private final BookService bookService;
    private final ConversionService conversionService;

    public BookDto create(CreateBookDto createBookDto) {
        val createBook = conversionService.convert(createBookDto, Book.class);
        val createdBook = bookService.create(createBook);
        return conversionService.convert(createdBook, BookDto.class);
    }

    public BookDto update(UUID id, UpdateBookDto updateBookDto) {
        val updateBook = conversionService.convert(updateBookDto, Book.class);
        val updatedBook = bookService.update(id, updateBook);
        return conversionService.convert(updatedBook, BookDto.class);
    }

    public void delete(UUID id) {
        bookService.delete(id);
    }

    public BookDto get(UUID id) {
        val retrievedBook = bookService.get(id);
        return conversionService.convert(retrievedBook, BookDto.class);
    }

    public Page<BookDto> query(UUID id, String name, String filter, List<String> sort, Integer pageNum, Integer pageSize) {
        return bookService.getBooks(id, name, PageUtils.buildPageable(pageNum, pageSize, sort))
                .map(book -> conversionService.convert(book, BookDto.class));
    }
}

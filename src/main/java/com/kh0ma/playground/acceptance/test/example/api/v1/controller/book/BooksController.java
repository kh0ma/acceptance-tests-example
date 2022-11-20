package com.kh0ma.playground.acceptance.test.example.api.v1.controller.book;

import java.util.List;
import java.util.UUID;

import com.kh0ma.playground.acceptance.test.example.api.v1.controller.BaseController;
import com.kh0ma.playground.acceptance.test.example.api.v1.controller.BooksApi;
import com.kh0ma.playground.acceptance.test.example.api.v1.controller.model.BookDto;
import com.kh0ma.playground.acceptance.test.example.api.v1.controller.model.CreateBookDto;
import com.kh0ma.playground.acceptance.test.example.api.v1.controller.model.UpdateBookDto;
import com.kh0ma.playground.acceptance.test.example.api.v1.facade.book.BookFacade;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */

@RestController
@RequiredArgsConstructor
public class BooksController extends BaseController implements BooksApi {

    private final BookFacade bookFacade;

    @Override
    public ResponseEntity<BookDto> createBook(CreateBookDto createBookDto) {
        val bookDto = bookFacade.create(createBookDto);
        val location = buildLocation(bookDto.getId());
        return ResponseEntity.created(location).body(bookDto);
    }

    @Override
    public ResponseEntity<Void> deleteBook(UUID id) {
        bookFacade.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BookDto> getBook(UUID id) {
        val bookDto = bookFacade.get(id);
        return ResponseEntity.ok(bookDto);
    }

    @Override
    public ResponseEntity<List<BookDto>> getBooks(UUID id, String name, String filter, List<String> sort, Integer pageNum, Integer pageSize) {
        val bookDtoPage = bookFacade.query(id, name, filter, sort, pageNum, pageSize);
        return buildQueryResponse(bookDtoPage);
    }

    @Override
    public ResponseEntity<BookDto> updateBook(UUID id, UpdateBookDto updateBookDto) {
        val bookDto = bookFacade.update(id, updateBookDto);
        return ResponseEntity.ok(bookDto);
    }
}

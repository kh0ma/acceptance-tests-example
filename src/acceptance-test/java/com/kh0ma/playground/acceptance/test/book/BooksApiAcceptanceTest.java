package com.kh0ma.playground.acceptance.test.book;

import com.kh0ma.playground.acceptance.test.AcceptanceTest;
import com.kh0ma.playground.acceptance.test.example.api.client.api.BooksApi;
import com.kh0ma.playground.acceptance.test.example.api.client.dto.BookDto;
import com.kh0ma.playground.acceptance.test.example.api.client.dto.CreateBookDto;
import com.kh0ma.playground.acceptance.test.util.ApiFactory;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
class BooksApiAcceptanceTest extends AcceptanceTest {

    private final BooksApi bookApi = ApiFactory.getBookApi(apiClient);

    @Test
    void createBook() {
        val createBookDto = new CreateBookDto();
        val expectedName = "My book";
        createBookDto.setName(expectedName);
        BookDto book = bookApi.createBook(createBookDto);
        assertNotNull(book);
        assertNotNull(book.getId());
        assertEquals(expectedName, book.getName());
    }

//    @Test
//    void deleteBook() {
//    }
//
//    @Test
//    void getBook() {
//    }
//
//    @Test
//    void getBooks() {
//    }
//
//    @Test
//    void updateBook() {
//    }
}
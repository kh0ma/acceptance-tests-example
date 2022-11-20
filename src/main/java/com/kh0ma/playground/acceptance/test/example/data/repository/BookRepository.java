package com.kh0ma.playground.acceptance.test.example.data.repository;

import java.util.List;
import java.util.UUID;

import com.kh0ma.playground.acceptance.test.example.data.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
public interface BookRepository extends CrudRepository<Book, UUID>, QueryByExampleExecutor<Book> {
}

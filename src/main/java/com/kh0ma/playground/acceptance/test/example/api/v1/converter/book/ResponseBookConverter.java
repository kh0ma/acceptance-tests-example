package com.kh0ma.playground.acceptance.test.example.api.v1.converter.book;

import com.kh0ma.playground.acceptance.test.example.api.v1.controller.model.BookDto;
import com.kh0ma.playground.acceptance.test.example.api.v1.converter.MapperSpringConfig;
import com.kh0ma.playground.acceptance.test.example.data.entity.Book;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@Mapper(config = MapperSpringConfig.class)
public interface ResponseBookConverter extends Converter<Book, BookDto> {

    @Override
    BookDto convert(@Nullable Book source);
}

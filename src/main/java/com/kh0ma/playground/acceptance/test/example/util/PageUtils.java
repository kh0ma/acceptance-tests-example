package com.kh0ma.playground.acceptance.test.example.util;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@UtilityClass
public class PageUtils {

    @NotNull
    public static PageRequest buildPageable(Integer pageNum,
                                            Integer pageSize,
                                            List<String> sortItems) {
        Sort sort = Optional.ofNullable(sortItems)
                .stream()
                .flatMap(Collection::stream)
                .map(PageUtils::mapToSort)
                .reduce(Sort::and)
                .orElse(Sort.unsorted());
        return PageRequest.of(pageNum, pageSize, sort);
    }

    @NotNull
    private static Sort mapToSort(String sortItem) {
        Sort.Direction sortDirection;
        String sortProperty;
        if (sortItem.startsWith("-")) {
            sortDirection = Sort.Direction.DESC;
            sortProperty = sortItem;
        } else {
            sortDirection = Sort.Direction.ASC;
            sortProperty = sortItem.substring(1);
        }
        return Sort.by(sortDirection, sortProperty);
    }
}

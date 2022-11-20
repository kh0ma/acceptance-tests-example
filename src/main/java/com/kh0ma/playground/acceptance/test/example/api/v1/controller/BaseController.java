package com.kh0ma.playground.acceptance.test.example.api.v1.controller;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@RequestMapping("api/v1")
public abstract class BaseController {

    protected URI buildLocation(@Valid UUID id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    protected <T> ResponseEntity<List<T>> buildQueryResponse(Page<T> page) {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                .header("X-Element-Count", String.valueOf(page.getNumberOfElements()))
                .header("X-Total-Pages", String.valueOf(page.getTotalPages()))
                .body(page.getContent());
    }
}

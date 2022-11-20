package com.kh0ma.playground.acceptance.test.example.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@Service
public class IdGeneratorService {

    public UUID generateUuid() {
        return UUID.randomUUID();
    }
}

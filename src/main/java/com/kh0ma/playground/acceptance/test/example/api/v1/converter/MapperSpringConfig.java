package com.kh0ma.playground.acceptance.test.example.api.v1.converter;

import org.mapstruct.MapperConfig;
import org.mapstruct.extensions.spring.SpringMapperConfig;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@MapperConfig(componentModel = "spring", uses = ConversionServiceAdapter.class)
@SpringMapperConfig(conversionServiceAdapterPackage = "com.kh0ma.playground.acceptance.test.example.api.v1.converter")
public interface MapperSpringConfig {
}


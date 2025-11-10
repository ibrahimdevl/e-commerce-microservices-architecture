package com.myshop.payment.config.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;

public class MapperConfig {

    @Bean
    public ObjectMapper objectMapperBean() {
        return new JsonMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
    }
}

package com.ecommerce.apih2.price.infrastructure.adapter;


import com.ecommerce.apih2.price.domain.port.DateMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateMapperImpl implements DateMapper {
    private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";

    @Override
    public LocalDateTime mapToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        return LocalDateTime.parse(date, formatter);
    }
}

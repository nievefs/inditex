package com.ecommerce.apih2.price.application.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateMapper {

    private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";

     public static LocalDateTime mapToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        return LocalDateTime.parse(date, formatter);
    }

}

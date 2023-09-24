package com.ecommerce.apih2.price.domain.port;

import java.time.LocalDateTime;

public interface DateMapper {
    LocalDateTime mapToLocalDateTime(String date);
}

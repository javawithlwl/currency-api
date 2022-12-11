package com.lwl.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorMessage {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;
}

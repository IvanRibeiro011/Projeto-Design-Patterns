package com.tads.biblioteca.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
@Getter
@AllArgsConstructor
public class ApiErrorMessage {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}

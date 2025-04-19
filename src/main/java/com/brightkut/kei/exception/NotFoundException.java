package com.brightkut.kei.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Data
public class NotFoundException extends RuntimeException{
    private final String errorMessage;
}
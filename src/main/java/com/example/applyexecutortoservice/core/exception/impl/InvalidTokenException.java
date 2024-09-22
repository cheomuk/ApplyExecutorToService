package com.example.applyexecutortoservice.core.exception.impl;

import com.example.applyexecutortoservice.core.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

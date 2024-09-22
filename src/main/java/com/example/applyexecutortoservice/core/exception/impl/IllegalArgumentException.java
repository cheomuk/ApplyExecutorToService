package com.example.applyexecutortoservice.core.exception.impl;

import com.example.applyexecutortoservice.core.exception.ErrorCode;

public class IllegalArgumentException extends BusinessException {

    public IllegalArgumentException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

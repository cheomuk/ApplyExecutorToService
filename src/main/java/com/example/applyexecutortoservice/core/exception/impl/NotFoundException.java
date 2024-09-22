package com.example.applyexecutortoservice.core.exception.impl;

import com.example.applyexecutortoservice.core.exception.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

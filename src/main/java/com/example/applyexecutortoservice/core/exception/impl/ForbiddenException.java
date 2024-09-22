package com.example.applyexecutortoservice.core.exception.impl;

import com.example.applyexecutortoservice.core.exception.ErrorCode;

public class ForbiddenException extends BusinessException {

    public ForbiddenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

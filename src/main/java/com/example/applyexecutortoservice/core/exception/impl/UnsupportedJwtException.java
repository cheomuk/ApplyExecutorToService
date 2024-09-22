package com.example.applyexecutortoservice.core.exception.impl;

import com.example.applyexecutortoservice.core.exception.ErrorCode;

public class UnsupportedJwtException extends BusinessException {

    public UnsupportedJwtException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

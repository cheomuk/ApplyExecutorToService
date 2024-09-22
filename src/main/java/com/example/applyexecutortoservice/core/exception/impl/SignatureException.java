package com.example.applyexecutortoservice.core.exception.impl;

import com.example.applyexecutortoservice.core.exception.ErrorCode;

public class SignatureException extends BusinessException {

    public SignatureException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

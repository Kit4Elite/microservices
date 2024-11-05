package com.kit4elite.execption;

import com.kit4elite.exception.DomainException;

public class OrderDomainException extends DomainException {
    public OrderDomainException(String message) {
        super(message);
    }
    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

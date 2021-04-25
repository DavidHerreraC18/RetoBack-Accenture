package com.payment.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The exception used to return a status and a message to the calling system.
 */
@SuppressWarnings("ClassWithoutNoArgConstructor")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message){
        super("Exception: "+message);
    }
}
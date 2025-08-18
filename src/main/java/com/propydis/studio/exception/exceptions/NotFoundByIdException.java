package com.propydis.studio.exception.exceptions;

public class NotFoundByIdException extends RuntimeException {
    public NotFoundByIdException(Object id, String entityName) {
        super( entityName + " con id " + id + " no registrado");
    }
}

package com.propydis.studio.shared.exception.exceptions;

public class NotFoundByIdException extends RuntimeException {
    public NotFoundByIdException(Object id, String entityName) {
        super( entityName + " con id " + id + " no registrado");
    }
}

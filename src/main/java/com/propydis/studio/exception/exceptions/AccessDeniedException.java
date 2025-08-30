package com.propydis.studio.exception.exceptions;

import com.propydis.studio.model.mysql.Role;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(Role roleType) {
        super("No tenes permisos para acceder a este recurso");
    }
}

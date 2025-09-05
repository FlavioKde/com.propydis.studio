package com.propydis.studio.infrastucture.validation;


import org.springframework.web.multipart.MultipartFile;

public class ImageValidator {

        private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

        public static void validate(MultipartFile photo) {
            if (photo == null) {
                throw new IllegalArgumentException("Archivo nulo");
            }

            if (!photo.getContentType().startsWith("image/")) {
                throw new IllegalArgumentException("Tipo de archivo no permitido: " + photo.getContentType());
            }

            if (photo.getSize() > MAX_SIZE) {
                throw new IllegalArgumentException("El archivo excede el tamaño máximo permitido (5MB)");
            }

        }
}


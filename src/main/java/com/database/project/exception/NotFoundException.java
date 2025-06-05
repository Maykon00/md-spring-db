package com.database.project.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private final Long id;

    public NotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }

}

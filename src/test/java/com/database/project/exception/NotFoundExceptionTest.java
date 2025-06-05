package com.database.project.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {
    @Test
    void testNotFoundExceptionProperties() {

        NotFoundException exception = new NotFoundException(1L, "test");

        assertEquals(1L, exception.getId());
        assertEquals("test", exception.getMessage());
    }
}
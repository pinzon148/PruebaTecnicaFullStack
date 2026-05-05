package exception;

package com.example.orchestrator.exception;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

import java.util.Map;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return Response.status(400)
                .entity(Map.of("error", e.getMessage()))
                .build();
    }
}

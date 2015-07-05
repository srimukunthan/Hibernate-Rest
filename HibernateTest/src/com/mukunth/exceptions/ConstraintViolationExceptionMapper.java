package com.mukunth.exceptions;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        return Response
                // Define your own status.
                .status(Status.BAD_REQUEST)
                // Put an instance of Viewable in the response so that jersey-mvc-jsp can handle it.
                .entity("mmmmmmmm")
                .build();
    }
}

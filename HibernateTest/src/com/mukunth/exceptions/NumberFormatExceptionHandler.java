package com.mukunth.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NumberFormatExceptionHandler implements ExceptionMapper<NumberFormatException> {

	@Override
	public Response toResponse(NumberFormatException e) {
		return  Response.status(Status.BAD_REQUEST).entity(e.getMessage()).type("text/plain").build();
	}

}

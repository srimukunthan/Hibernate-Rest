package com.mukunth.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceExceptionHandler implements ExceptionMapper<ResourceException> {

	@Override
	public Response toResponse(ResourceException exception) {
		return Response.status(Status.NOT_FOUND).entity("Requested resource not availabele").type("text/plain").build();
	}
	
}


package br.com.github.dungeon.creator.controller.common;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExeptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
	    return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();  
	}
}
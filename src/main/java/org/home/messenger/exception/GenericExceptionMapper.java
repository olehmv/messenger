package org.home.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.home.messenger.model.ErroMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErroMessage message=new ErroMessage(ex.getMessage(),500,"hppt://exception.home.org");
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}

}

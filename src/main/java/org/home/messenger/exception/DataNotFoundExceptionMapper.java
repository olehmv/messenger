package org.home.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.home.messenger.model.ErroMessage;
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErroMessage message=new ErroMessage("data not found",404,"http://exception.home.org");
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

}

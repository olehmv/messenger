package org.home.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.home.messenger.model.Message;
import org.home.messenger.resources.Beans.MessageFilterBean;
import org.home.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	private static MessageService service = new MessageService();

	@DELETE
	@Path("/{messageId}")
	public Message delete(@PathParam("messageId") long id) {
		return service.removeMessage(id);
	}

	@PUT
	@Path("/{messageId}")
	public Message update(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return service.updateMessage(message);
	}

	// set location, cookie, status 201 created in response object
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = service.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(message.getId())).build();
		return Response.created(uri).entity(newMessage).cookie(new NewCookie("name", "cookie message")).build();
	}

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filter) {
		if (filter.getYear() > 0) {
			return service.getAllMessagesFromYear(filter.getYear());
		}
		if (filter.getStart() >= 0 && filter.getSize() > 0) {
			return service.getAllMessagesPaginated(filter.getStart(), filter.getSize());

		}
		return service.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message = service.getMessage(id);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForAuthor(uriInfo, message), "author");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.resolveTemplate("messageId", message.getId()).build().toString();
		return uri;
	}

	private String getUriForAuthor(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build()
				.toString();
		return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(String.valueOf(message.getId()))
				.build().toString();
		return uri;
	}

	// sub resources ->comments !!!
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}

// @POST
// public Message addMessage(Message message){
// return service.addMessage(message);
// }
// @GET
// public List<Message> getMessages(@QueryParam("year")int year,
// @QueryParam("start")int start,@QueryParam("size")int size){
// System.out.println(year);
// if(year>0){
// return service.getAllMessagesFromYear(year);
// }
// if(start>0 && size>0){
// return service.getAllMessagesPaginated(start, size);
//
// }
// return service.getAllMessages();
// }
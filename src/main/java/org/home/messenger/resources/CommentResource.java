package org.home.messenger.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.home.messenger.model.Comment;
import org.home.messenger.service.CommentService;

@Path("/")
public class CommentResource {
	
	
	private static CommentService service=new CommentService();	
	
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId")long messageId){
		return service.getAllComments(messageId);
	}
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId")long messageId,@PathParam("commentId")long commentId){
		return service.getComment(messageId, commentId);
	}
	@POST
	public Comment addComment(@PathParam("messageId")long messageId,Comment comment){
		return service.addComment(messageId, comment);
	}
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId")long messageId,@PathParam("commentId")long commentId,Comment comment){
		comment.setId(commentId);
		return service.updateComment(messageId, comment);	
	}
	@DELETE
	@Path("/{commentId}")
	public Comment removeComment(@PathParam("messageId")long messageId,@PathParam("commentId")long commentId){
		return service.removeComment(messageId, commentId);
	}
}

package org.home.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.home.messenger.database.DatabaseClass;
import org.home.messenger.model.Comment;
import org.home.messenger.model.ErroMessage;
import org.home.messenger.model.Message;

public class CommentService {

	private static Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> map = messages.get(messageId).getList();
		return new ArrayList<>(map.values());
	}

	public Comment getComment(long messageId,long commentId){
		Message message=messages.get(messageId);
		ErroMessage erroMessage=new ErroMessage(" not found",404,"http://exception.home.org");
		Response response=Response.status(Status.NOT_FOUND).entity(erroMessage).build();
		if(message==null){
			throw new WebApplicationException(response);
		}
		Map<Long,Comment>map=messages.get(messageId).getList();
		Comment comment=map.get(commentId);
		if(comment==null){
			throw new NotFoundException();
		}
		return comment;	
	}

	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> map = messages.get(messageId).getList();
		comment.setId(map.size() + 1);
		map.put(comment.getId(), comment);
		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> map = messages.get(messageId).getList();
		if (comment.getId() <= 0) {
			return null;
		}
		return map.put(comment.getId(), comment);
	}

	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> map = messages.get(messageId).getList();
		return map.remove(commentId);
	}
}

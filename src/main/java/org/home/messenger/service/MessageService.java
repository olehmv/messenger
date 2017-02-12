package org.home.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.home.messenger.database.DatabaseClass;
import org.home.messenger.exception.DataNotFoundException;
import org.home.messenger.model.Message;

public class MessageService {
	
	private static Map<Long,Message>messages=DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L,new Message(1l,"hello world","oleg"));
		messages.put(2L,new Message(2l,"hello jersey","oleg"));
	}
	
	public List<Message>getAllMessagesFromYear(int year){
		List<Message>messageFromYear=new ArrayList<>();
		Calendar cal=Calendar.getInstance();
		for (Message message:messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year){
				messageFromYear.add(message);
			}
			
			
		}
		return messageFromYear;
		
	}
	public List<Message>getAllMessagesPaginated(int start,int size){
		List<Message>messagesPaginated=new ArrayList<>(messages.values());
		if(start+size>messagesPaginated.size())return new ArrayList<Message>();
		return messagesPaginated.subList(start, start+size);
	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	public Message getMessage(long id){
		Message message= messages.get(id);
		if(message==null){
			throw new DataNotFoundException("message with id - "+id+" not found");
		}
		return message;
		
	}
	public Message updateMessage(Message message){
		if(message.getId()<=0){
			return null;
		}
		messages.put(message.getId(),message);
		return message;
	}
	public Message removeMessage(long id){
		return messages.remove(id);
	}

}

//		Message m1=new Message(1l,"hello world","oleg");
//		Message m2=new Message(2l,"hello jersey","oleg");
//		List<Message>list=new ArrayList<>();
//		list.add(m1);
//		list.add(m2);
//		return list;
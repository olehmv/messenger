package org.home.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErroMessage {
	
	private String erroMessage;
	private int errpCode;
	private String documentation;
	
	public ErroMessage() {
		super();
	}
	
	public ErroMessage(String erroMessage, int errpCode, String documentation) {
		super();
		this.erroMessage = erroMessage;
		this.errpCode = errpCode;
		this.documentation = documentation;
	}

	public String getErroMessage() {
		return erroMessage;
	}
	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}
	public int getErrpCode() {
		return errpCode;
	}
	public void setErrpCode(int errpCode) {
		this.errpCode = errpCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	

}

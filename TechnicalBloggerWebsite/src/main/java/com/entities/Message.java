package com.entities;

//Msg class for login failed 

public class Message {
	
	private String content;
	private String type;
	private  String cssclass;
	
	
	public Message(String content, String type, String cssclass) {
		super();
		this.content = content;
		this.type = type;
		this.cssclass = cssclass;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCssclass() {
		return cssclass;
	}


	public void setCssclass(String cssclass) {
		this.cssclass = cssclass;
	}
	

	
	
}

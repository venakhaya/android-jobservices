package com.usermanager.app.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("message")
	private String message;
	public MessageResponse(String message) {
		super();
		this.message = message;
	}
	

}

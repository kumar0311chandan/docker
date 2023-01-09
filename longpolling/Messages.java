package com.chandan.webservices.restfulwebservices.longpolling;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Messages {
	
	@Id
	private String id;
	
	private String text;
	private String date;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}

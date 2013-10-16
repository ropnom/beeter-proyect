package edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.model;

import java.util.Date;

public class Sting {
	
	private String stingid;
	private String username;
	private String author;
	private String subject;
	private String content;
	private Date creationTimestamp;
	
	public String getStingid() {
		return stingid;
	}
	public void setStingid(String id) {
		this.stingid = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subjet) {
		this.subject = subjet;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getcreationTimestamp() {
		return creationTimestamp;
	}
	public void setcreationTimestamp(Date timestamp) {
		creationTimestamp = timestamp;
	}
	

}

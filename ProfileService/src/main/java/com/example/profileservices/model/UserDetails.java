package com.example.profileservices.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDetails {

	private String firstname;
	private String lastname;
	List<NameValuePair> attributes = new ArrayList<NameValuePair>();
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public List<NameValuePair> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<NameValuePair> attributes) {
		this.attributes = attributes;
	}
	
}

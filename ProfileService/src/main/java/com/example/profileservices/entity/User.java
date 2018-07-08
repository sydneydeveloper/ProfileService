package com.example.profileservices.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
    private int userId; 
	
	@NotNull @NotBlank @NotEmpty
	@Column(name="firstname")
	@ApiModelProperty(notes = "First name of the user.", example = "John", required = true, position = 1)
    private String firstName;
	
	@NotNull @NotBlank @NotEmpty
	@Column(name="lastname")
	@ApiModelProperty(notes = "Last name of the user.", example = "Doe", required = true, position = 2)
    private String lastName;
	
	@ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="user_details", joinColumns=@JoinColumn(name="user_id"))
	@ApiModelProperty(notes = "Details of the user", required = true, position = 3)
    Map<String, String> attributes = new HashMap<String, String>(); 

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}

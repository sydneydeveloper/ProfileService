package com.example.profileservices.dao;

import java.util.List;
import java.util.Map;

import com.example.profileservices.entity.User;
import com.example.profileservices.model.UserDetails;
import com.example.profileservices.utility.CreateUserException;
import com.example.profileservices.utility.UserNotInDatabaseException;



public interface CRUDDao {

	void create(String firstName, String lastName, Map<String, String> pair) throws CreateUserException;
	void update(String firstName, String lastName, Map<String, String> pair) throws UserNotInDatabaseException;
	void delete(String firstName, String lastName) throws UserNotInDatabaseException;
	List<User> getAllProfiles();
}

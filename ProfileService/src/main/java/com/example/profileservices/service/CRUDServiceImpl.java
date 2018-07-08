package com.example.profileservices.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profileservices.dao.CRUDDao;
import com.example.profileservices.entity.User;
import com.example.profileservices.model.UserDetails;
import com.example.profileservices.utility.CreateUserException;
import com.example.profileservices.utility.UserNotInDatabaseException;

@Service
public class CRUDServiceImpl implements CRUDService{

	@Autowired
	private CRUDDao crudDAO;

	@Override
	public void create(String firstName, String lastName, Map<String, String> pair) throws CreateUserException  {
		 try {
			crudDAO.create(firstName, lastName, pair);
		} catch (CreateUserException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
	}

	@Override
	public void update(String firstName, String lastName, Map<String, String> pair) throws UserNotInDatabaseException{
		try {
		 crudDAO.update(firstName, lastName, pair);
		}catch(UserNotInDatabaseException e) {
			throw e;
		}
		
	}

	@Override
	public void delete(String firstName, String lastName) throws UserNotInDatabaseException{
		try {
		 crudDAO.delete(firstName, lastName);
		}catch(UserNotInDatabaseException e) {
			throw e;
		}
		
	}
	
	@Override
	public List<User> getAllProfiles(){
		try {
		return crudDAO.getAllProfiles();
		}catch(Exception e) {
			throw e;
		}
	}
	
	

}

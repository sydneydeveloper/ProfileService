package com.example.profileservices.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.example.profileservices.entity.*;
import com.example.profileservices.model.UserDetails;
import com.example.profileservices.utility.CreateUserException;
import com.example.profileservices.utility.UserNotInDatabaseException;

@Transactional
@Repository
public class CRUDDaoImpl implements CRUDDao {

	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public void create(String firstName, String lastName, Map<String, String> pair) throws CreateUserException {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAttributes(pair);
		try {	
			entityManager.persist(user);
		}catch(Exception ex) {
			throw new CreateUserException("Error creating user!");
		}

	}

	@Override
	public void update(String firstName, String lastName, Map<String, String> pair) throws UserNotInDatabaseException {
		try {
			String hql = "FROM User as user WHERE user.firstName = ? and user.lastName = ?";
			List<User> user = entityManager.createQuery(hql).setParameter(1, firstName)
					.setParameter(2, lastName).getResultList();
			if(user.isEmpty()) {
				throw new UserNotInDatabaseException("User not present!");
			}
			user.get(0).setAttributes(pair);
			entityManager.flush();	
		}catch(Exception ex) {
			throw ex;
		}

	}

	@Override
	public void delete(String firstName, String lastName) throws UserNotInDatabaseException {
		try {
			String hql = "FROM User as user WHERE user.firstName = ? and user.lastName = ?";
			List<User> user = entityManager.createQuery(hql).setParameter(1, firstName)
					.setParameter(2, lastName).getResultList();
			if(user.isEmpty()) {
				throw new UserNotInDatabaseException("User not present!");
			}
			entityManager.remove(user.get(0));
		}catch(Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<User> getAllProfiles(){
		try {
			String hql = "FROM User as user ORDER BY user.userId";
			List<User>  users = (List<User>) entityManager.createQuery(hql).getResultList();
			return users;
		}catch(Exception ex) {
			throw ex;
		}

	}




}

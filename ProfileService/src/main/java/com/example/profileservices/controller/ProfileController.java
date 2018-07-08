package com.example.profileservices.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.profileservices.entity.User;
import com.example.profileservices.model.NameValuePair;
import com.example.profileservices.model.UserDetails;
import com.example.profileservices.service.CRUDService;
import com.example.profileservices.utility.CreateUserException;
import com.example.profileservices.utility.JsonResponseBody;
import com.example.profileservices.utility.UserNotInDatabaseException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/profileservices")
@Api(value="Profile Services", description = "Set of endpoints for Creating, Updating and Deleting of users.")
public class ProfileController {

	@Autowired
	CRUDService crudService;
	
	@RequestMapping(value = "/createProfile", method= POST)
	@ApiOperation("Creates profile for a user")
	public ResponseEntity<JsonResponseBody> createProfile(@ApiParam("Details of the new user - firstname,lastname and more attirbutes of user") @RequestBody UserDetails user) {

		try {
			List<NameValuePair> obj =user.getAttributes();
			Map<String, String> map = new HashMap<String,String>();
			for(NameValuePair pair : obj) {
				map.put(pair.getName(), pair.getValue());
			}
			crudService.create(user.getFirstname(), user.getLastname(),map);
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "Profile Created"));
		}catch(CreateUserException e2){
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(new JsonResponseBody(HttpStatus.METHOD_FAILURE.value(), "Create Profile Failed " + e2.toString()));
		}catch(Exception e2){
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(new JsonResponseBody(HttpStatus.METHOD_FAILURE.value(), "Method Failure: " + e2.toString()));
		}
	}

	@RequestMapping(value = "/updateProfile", method= POST)
	@ApiOperation("Updates profile for a user")
	public ResponseEntity<JsonResponseBody> updateProfile(@ApiParam("Details to update for the user - firstname,lastname and new attributes to add for user") @RequestBody UserDetails user) {
		
		try {
			List<NameValuePair> obj =user.getAttributes();
			Map<String, String> map = new HashMap<String,String>();
			for(NameValuePair pair : obj) {
				map.put(pair.getName(), pair.getValue());
			}
			crudService.update(user.getFirstname(), user.getLastname(),map);
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "Profile Updated"));
		}catch(UserNotInDatabaseException e2){
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(new JsonResponseBody(HttpStatus.METHOD_FAILURE.value(), "Update Profile Failed " + e2.toString()));
		}catch(Exception e2){
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(new JsonResponseBody(HttpStatus.METHOD_FAILURE.value(), "Method Failure: " + e2.toString()));
		}
	}
	
	@RequestMapping(value = "/deleteProfile", method= POST)
	@ApiOperation("Deletes profile of a user")
	public ResponseEntity<JsonResponseBody> deleteProfile(@ApiParam("firstname of the user to delete") @RequestParam(value="firstname") String firstName, 
			@ApiParam("lastname of the user to delete") @RequestParam(value="lastname") String lastName) {
		
		try {
			crudService.delete(firstName, lastName);
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "Profile Deleted"));
		}catch(UserNotInDatabaseException e2){
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(new JsonResponseBody(HttpStatus.METHOD_FAILURE.value(), "Delete Profile Failed " + e2.toString()));
		}catch(Exception e2){			
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(new JsonResponseBody(HttpStatus.METHOD_FAILURE.value(), "Method Failure: " + e2.toString()));
		}
	}
	
	@RequestMapping(value = "/getAllProfiles", method= POST)
	@ApiOperation("Get profile of all users")
	public ResponseEntity<JsonResponseBody> getAllProfiles() {
		
		try {
			List<User> response = crudService.getAllProfiles();
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), response));
		}catch(Exception e2){			
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(new JsonResponseBody(HttpStatus.METHOD_FAILURE.value(), "Method Failure: " + e2.toString()));
		}

	}

}

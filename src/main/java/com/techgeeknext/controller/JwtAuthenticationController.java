package com.techgeeknext.controller;

import com.techgeeknext.config.JwtTokenUtil;


import com.techgeeknext.model.JwtRequest;
import com.techgeeknext.model.JwtResponse;
import com.techgeeknext.model.UserDao;
import com.techgeeknext.model.UserDto;
import com.techgeeknext.exception.*;
import com.techgeeknext.repository.UserRepository;
import com.techgeeknext.service.JwtUserDetailsService;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	UserRepository userRepository;
	

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
        return ResponseEntity.ok(new JwtResponse(token));
		
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@ModelAttribute UserDto user) throws FileUploadException {
		String username = user.getUsername();
		String email = user.getEmail();
		int c = 0;
		System.out.println(username);
    	List<UserDao> userList = null;
    	userList = (List<UserDao>) userRepository.findAll();
    	if (userList.size() > 0)
    	{
    		for(UserDao usr : userList)
    		{
    			if(usr.getUsername().equals(username) )
    			{
    				c = 1;
    				break;
    			}
    		}
    	}
    	if ( c == 0  && email.contains("@") )
            return ResponseEntity.ok(userDetailsService.save(user));
        else
            return ResponseEntity.unprocessableEntity().body("Username Already Exists or Invalid Email"); //appropriate error code
		
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@GetMapping
    @RequestMapping("/list")
	List<UserDao> getStudents(){
		return (List<UserDao>) userRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("/user/{username}")
    public UserDao findUser(@PathVariable String username)
    {
		System.out.println(username);
    	UserDao retUsr = new UserDao();
    	List<UserDao> userList = null;
    	userList = (List<UserDao>) userRepository.findAll();
    	if (userList.size() > 0)
    	{
    		for(UserDao usr : userList)
    		{
    			if(usr.getUsername().equals(username) )
    			{
    				retUsr.setUserID(usr.getUserID());
    				retUsr.setbio(usr.getbio());
    				retUsr.setUsername(usr.getUsername());
    				return retUsr;
    			}
    		}
    	}
    	return null;
    }
}

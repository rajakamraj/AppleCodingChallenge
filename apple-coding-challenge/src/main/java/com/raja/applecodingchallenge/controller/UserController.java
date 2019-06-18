package com.raja.applecodingchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raja.applecodingchallenge.model.Role;
import com.raja.applecodingchallenge.model.User;
import com.raja.applecodingchallenge.service.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    

    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<?> auth(Principal principal){
        if(principal==null || principal.getName()==null){
            return ResponseEntity.ok(principal);
        }
        return new ResponseEntity<>(
                userService.findByUsername(principal.getName()),
                HttpStatus.OK);
    }

	@GetMapping("/api/user/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
    
    @GetMapping("/api/user/calculate")
    public ResponseEntity<?> register(@RequestParam String data){
    	
    	try {
			data =  URLDecoder.decode(data, "UTF-8");
			System.out.println(data);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
        
        if(data == null || data.length() == 0)
        {
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        return new ResponseEntity<>(userService.calculateMaxSum(data), HttpStatus.OK);
    }
}
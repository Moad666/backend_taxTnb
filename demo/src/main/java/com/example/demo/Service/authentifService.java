package com.example.demo.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.User;


@Service
public class authentifService {

	private final RestTemplate restTemplate;
	
	@Autowired
    public authentifService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	public void makeRequest() {
	    String uri = "http://localhost:8081/auth/login";

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	    // Assuming you have a User object with username and password fields
	    User user = new User();
	    user.setUsername("admin");
	    user.setPassword("moadmoad");

	    HttpEntity<User> entity = new HttpEntity<>(user, headers);

	    try {
	        ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
	        System.out.println("Response: " + result.getBody());
	    } catch (HttpClientErrorException e) {
	        System.err.println("Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
	    }
	}



}

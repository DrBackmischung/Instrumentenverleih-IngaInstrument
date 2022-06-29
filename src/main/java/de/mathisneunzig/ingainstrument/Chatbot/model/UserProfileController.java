package de.mathisneunzig.ingainstrument.Chatbot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/up")
public class UserProfileController {
	
	@Autowired
	UserProfileRepository repo;
	
	@GetMapping("/")
	public ResponseEntity<Object> getQueryResponse(){
		
		return new ResponseEntity<Object>(repo.findAll(), HttpStatus.OK);
		
	}
	
}

package de.mathisneunzig.ingainstrument.Chatbot.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mathisneunzig.ingainstrument.Chatbot.model.UserProfile;
import de.mathisneunzig.ingainstrument.Chatbot.model.UserProfileRepository;

@Controller
@RestController
@RequestMapping("/query")
public class QueryController {
	
	String baseURL = "http://localhost:3000/";
	
	@Autowired
	UserProfileRepository repo;
	
	@GetMapping("/{ip}/{query}")
	public ResponseEntity<Object> getQueryResponse(@PathVariable String query, @PathVariable String ip){
		
		query = query.replace("_", " ");
		
		if(QueryValidator.contains(query, "ausleihe", "ausleihen", "rent", "rental")) {
			
			String uri = baseURL+"ausleihe";
			return new ResponseEntity<Object>(new Response(ResponseType.LINK, uri), HttpStatus.OK);
			
		} else if(QueryValidator.somewhereRoughlyContains(query, "profil")) {
			
			String uri = baseURL+"profil";
			return new ResponseEntity<Object>(new Response(ResponseType.LINK, uri), HttpStatus.OK);
			
		} else if(QueryValidator.somewhereRoughlyContains(query, "login") || QueryValidator.somewhereRoughlyContains(query,  "signin")) {
			
			String uri = baseURL+"login";
			return new ResponseEntity<Object>(new Response(ResponseType.LINK, uri), HttpStatus.OK);
			
		} else if(QueryValidator.somewhereRoughlyContains(query, "registrierung")
				|| (QueryValidator.somewhereRoughlyContains(query, "registrieren"))
				|| (QueryValidator.somewhereRoughlyContains(query, "new account")
						|| QueryValidator.somewhereRoughlyContains(query, "signup"))) {
			
			String uri = baseURL+"signup";
			return new ResponseEntity<Object>(new Response(ResponseType.LINK, uri), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "start")) {
		
			UserProfile up = new UserProfile(ip);
			repo.save(up);
			
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Hier ein kleiner Test!\nWo findet dein Lieblingsfestival statt?\n1 - Wiese (WIESE)\n2 - "), HttpStatus.OK);
			
		} else {
			
			String uri = "Sorry, ich weiß nicht, was ich machen soll :-(";
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, uri), HttpStatus.OK);
			
		}
		
	}

}

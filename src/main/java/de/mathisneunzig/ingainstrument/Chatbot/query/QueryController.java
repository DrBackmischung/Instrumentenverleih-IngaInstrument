package de.mathisneunzig.ingainstrument.Chatbot.query;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
@RestController
@RequestMapping("/query")
public class QueryController {
	
	String baseURL = "http://localhost:3000/";
	
	@GetMapping("/{query}")
	public ResponseEntity<Object> getQueryResponse(@PathVariable String query){
		
		query = query.replace("_", " ");
		
		if(QueryValidator.contains(query, "ausleihe", "ausleihen", "rent", "rental")) {
			
			String uri = baseURL+"ausleihe";
			RestTemplate t = new RestTemplate();
		    Object result = t.getForObject(uri, Object.class);
			return new ResponseEntity<Object>(new Response(ResponseType.LINK, result), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "profil", "profile")) {
			
			String uri = baseURL+"profil";
			RestTemplate t = new RestTemplate();
		    Object result = t.getForObject(uri, Object.class);
			return new ResponseEntity<Object>(new Response(ResponseType.LINK, result), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "login", "einloggen", "signin")) {
			
			String uri = baseURL+"login";
			RestTemplate t = new RestTemplate();
		    Object result = t.getForObject(uri, Object.class);
			return new ResponseEntity<Object>(new Response(ResponseType.LINK, result), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "registrierung", "registrieren", "signup", "new account")) {
			
			String uri = baseURL+"signup";
			RestTemplate t = new RestTemplate();
		    Object result = t.getForObject(uri, Object.class);
			return new ResponseEntity<Object>(new Response(ResponseType.LINK, result), HttpStatus.OK);
			
		} else {
			
			String uri = "Sorry, ich weiß nicht, was ich machen soll :-(";
			RestTemplate t = new RestTemplate();
		    Object result = t.getForObject(uri, Object.class);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, result), HttpStatus.OK);
			
		}
		
	}

}

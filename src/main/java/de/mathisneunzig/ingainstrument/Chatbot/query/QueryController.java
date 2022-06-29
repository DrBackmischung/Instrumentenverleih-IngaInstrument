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
			
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Dann wollen wir mal herausfinden, welches Instrument am besten zu dir passt :) Dafür einfach die folgenden Fragen beantworten. Wenn du bereit bist, schreib bitte 'bereit'! Die Fragen beantwortest du, indem du den zutreffenden Begriff in den Chat schreibst."), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "bereit")) {
			
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Wo würde dein Traumkonzert stattfinden? Auf einer Wiese (WIESE), in einer großen Konzerthalle (HALLE) oder in einer gruftigen Kirche (KIRCHE)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "wiese")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setGitarre(up.getGitarre() + 14);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Wo spielst du am liebsten dein Instrument? Im eigenen Zimmer (ZIMMER), im Keller (KELLER) oder da, wo es halt steht - es ist eh zu groß (GROß)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "halle")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setSchlagzeug(up.getSchlagzeug() + 10);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Wo spielst du am liebsten dein Instrument? Im eigenen Zimmer (ZIMMER), im Keller (KELLER) oder da, wo es halt steht - es ist eh zu groß (GROß)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "kirche")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setOrgel(up.getOrgel() + 10);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Wo spielst du am liebsten dein Instrument? Im eigenen Zimmer (ZIMMER), im Keller (KELLER) oder da, wo es halt steht - es ist eh zu groß (GROß)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "zimmer")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setGitarre(up.getGitarre() + 10);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was ist dein Lieblingsgenre? Rock (ROCK), Hip Hop (HIPHOP) oder was ganz ausgefallenes (SONSTIGES)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "keller")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setSchlagzeug(up.getSchlagzeug() + 16);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was ist dein Lieblingsgenre? Rock (ROCK), Hip Hop (HIPHOP) oder was ganz ausgefallenes (SONSTIGES)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "groß")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setOrgel(up.getOrgel() + 13);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was ist dein Lieblingsgenre? Rock (ROCK), Hip Hop (HIPHOP) oder was ganz ausgefallenes (SONSTIGES)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "rock")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setGitarre(up.getGitarre() + 15);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was ist dein Lieblingstier? Was normales wie Hund, Katze (NORMAL), Was wildes wie ein Schaf oder Hühner (WILD) oder was ganz exotisches, zum Beispiel eine Schlange (EXOTISCH)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "hiphop")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setSchlagzeug(up.getSchlagzeug() + 10);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was ist dein Lieblingstier? Was normales wie Hund, Katze (NORMAL), Was wildes wie ein Schaf oder Hühner (WILD) oder was ganz exotisches, zum Beispiel eine Schlange (EXOTISCH)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "sonstiges")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setOrgel(up.getOrgel() + 3);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was ist dein Lieblingstier? Was normales wie Hund, Katze (NORMAL), Was wildes wie ein Schaf oder Hühner (WILD) oder was ganz exotisches, zum Beispiel eine Schlange (EXOTISCH)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "normal")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setGitarre(up.getGitarre() + 10);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was trinkst du am liebsten auf einer Party? Softgetränke (SOFT), Alkohol und Bier (ALKOHOL) oder Blut (BLUT)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "wild")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setSchlagzeug(up.getSchlagzeug() + 6);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was trinkst du am liebsten auf einer Party? Softgetränke (SOFT), Alkohol und Bier (ALKOHOL) oder Blut (BLUT)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "exotisch")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setOrgel(up.getOrgel() + 11);
			repo.save(up);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was trinkst du am liebsten auf einer Party? Softgetränke (SOFT), Alkohol und Bier (ALKOHOL) oder Blut (BLUT)?"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "soft")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setGitarre(up.getGitarre() + 10);
			repo.save(up);
			String s = up.getHighest();
			if(s == "G")
				return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Du bist ein waschechter Gitarrist! Leih dir doch eine Gitarre aus :)"), HttpStatus.OK);
			if(s == "S")
				return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Zu dir passt am besten ein Schlagzeug. Warn aber lieber deine Eltern vor ;)"), HttpStatus.OK);
			if(s == "O")
				return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was ganz anderes, zu dir passt eine Orgel! Probier doch mal was neues aus :)"), HttpStatus.OK);
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, up.getHighest()), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "ALKOHOL")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setSchlagzeug(up.getSchlagzeug() + 16);
			repo.save(up);
			String s = up.getHighest();
			if(s == "G")
				return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Du bist ein waschechter Gitarrist! Leih dir doch eine Gitarre aus :)"), HttpStatus.OK);
			if(s == "S")
				return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Zu dir passt am besten ein Schlagzeug. Warn aber lieber deine Eltern vor ;)"), HttpStatus.OK);
			if(s == "O")
				return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was ganz anderes, zu dir passt eine Orgel! Probier doch mal was neues aus :)"), HttpStatus.OK);
			
		} else if(QueryValidator.contains(query, "BLUT")) {
			
			UserProfile up = repo.findByIp(ip).get();
			up.setOrgel(up.getOrgel() + 11);
			repo.save(up);
			String s = up.getHighest();
			if(s == "G")
				return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Du bist ein waschechter Gitarrist! Leih dir doch eine Gitarre aus :)"), HttpStatus.OK);
			if(s == "S")
				return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Zu dir passt am besten ein Schlagzeug. Warn aber lieber deine Eltern vor ;)"), HttpStatus.OK);
			if(s == "O")
				return new ResponseEntity<Object>(new Response(ResponseType.STRING, "Was ganz anderes, zu dir passt eine Orgel! Probier doch mal was neues aus :)"), HttpStatus.OK);
			
		} else {
			
			String uri = "Sorry, ich weiß nicht, was ich machen soll :-(";
			return new ResponseEntity<Object>(new Response(ResponseType.STRING, uri), HttpStatus.OK);
			
		}
		return null;
		
	}

}

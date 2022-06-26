package de.mathisneunzig.ingainstrument.Chatbot.query;

public enum ResponseType {
	
	STRING("String"),
	LINK("Link");
	
	private String value;

	ResponseType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
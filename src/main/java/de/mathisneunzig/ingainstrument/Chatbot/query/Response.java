package de.mathisneunzig.ingainstrument.Chatbot.query;

public class Response {
	
	private ResponseType type;
	private Object content;
	
	public Response(ResponseType type, Object content) {
		this.type = type;
		this.content = content;
	}

	public ResponseType getType() {
		return type;
	}

	public void setType(ResponseType type) {
		this.type = type;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
package com.hit.server;

import java.util.Map;

public class Request {
	private Map<String, String> headers;
	private String content;

	public Request(Map<String, String> headers, String content) {
		super();
		this.headers = headers;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public Map<String, String> getHeaders() {

		return headers;
	}

	@Override
	public String toString() {
		return "Request [headers=" + headers + ", content=" + content + "]";
	}

}
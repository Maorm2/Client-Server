package com.hit.model;

import java.util.Map;

public class Response {
	private Map<String, String> headers;
	private String content;

	public Response(Map<String, String> headers, String content) {
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
		return "Response [headers=" + headers + ", content=" + content + "]";
	}

}
package com.hit.model;

import java.util.HashMap;
import java.util.Map;

public class Request {
	private Map<String, String> headers;
	private String content;

	public Request(Map<String, String> headers, String content) {
		super();
		this.headers = headers;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Request [headers=" + headers + ", content=" + content + "]";
	}

	public Request(Builder builder) {
		super();
		this.headers = builder.headers;
		this.content = builder.content;
	}

	public String getContent() {
		return content;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public static class Builder {
		private Map<String, String> headers = new HashMap<String, String>();
		private String content;

		public Builder setAction(String action) {
			headers.put("action", action);
			return this;
		}

		public Builder setContent(String content) {
			this.content = content;
			return this;
		}

		public Request build() {
			return new Request(this);
		}

		@Override
		public String toString() {
			return "Builder [headers=" + headers + ", content=" + content + "]";
		}

	}
}
package com.hit.service;

import java.util.UUID;

public class ServiceId {
	private static ServiceId service;

	public static ServiceId getInstance() {
		if (service == null)
			service = new ServiceId();

		return service;
	}

	public UUID generateId() {
		UUID id = UUID.randomUUID();
		return id;
	}

}
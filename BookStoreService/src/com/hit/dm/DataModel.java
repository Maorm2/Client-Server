package com.hit.dm;

import java.util.UUID;

import com.hit.service.ServiceId;

public class DataModel<T> {

	public T book;
	public final UUID id;

	public DataModel(T book, UUID id) {
		this.book = book;
		this.id = ServiceId.getInstance().generateId();
	}

	@Override
	public String toString() {
		return "DataModel [algo=" + book + ", id=" + id + "]";
	}

}

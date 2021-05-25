package com.hit.model;

public class DataModel<T> {
	public final T book;

	public DataModel(T book) {
		this.book = book;
	}

	public T getBook() {
		return book;
	}

	@Override
	public String toString() {
		return "DataModel [ , bookName=" + book + "]";
	}

}
package com.hit.model;

public class Book {

	public String title;
	public String author;
	public String publisher;
	public int year;
	public int numOfpages;
	public String id;

	public Book(String title, String author, String publisher, int year, int numOfpages) {

		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		this.numOfpages = numOfpages;
	}

	public Book() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher=" + publisher + ", year=" + year
				+ ", numOfpages=" + numOfpages + ", id=" + id + "]";
	}


}

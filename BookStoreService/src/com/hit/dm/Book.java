package com.hit.dm;

public class Book {

	public String title;
	public String author;
	public String publisher;
	public int year;
	public int numOfpages;
	

	public Book(String title, String author, String publisher, int year, int numOfpages) {

		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		this.numOfpages = numOfpages;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher=" + publisher + ", year=" + year
				+ ", numOfpages=" + numOfpages + "]";
	}

}

package com.statech.mockito.annotations.support;

import java.time.LocalDate;

public class Book {

	private int bookId;
	private String title;
	private double price;
	private LocalDate publishedOn;
	
	public Book(int bookId, String title, double price, LocalDate publishedOn) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.publishedOn = publishedOn;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getPublishedOn() {
		return publishedOn;
	}

	public void setPublishedOn(LocalDate publishedOn) {
		this.publishedOn = publishedOn;
	}
	
	
}

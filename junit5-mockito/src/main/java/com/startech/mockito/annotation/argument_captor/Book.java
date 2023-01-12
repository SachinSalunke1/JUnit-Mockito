package com.startech.mockito.annotation.argument_captor;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

	private int bookId;
	private String title;
	private double price;
	private LocalDate publishedOn;
	
	public Book() {
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(price, publishedOn, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(publishedOn, other.publishedOn) && Objects.equals(title, other.title);
	}

	
	
}

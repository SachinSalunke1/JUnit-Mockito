package com.startech.mockito.annotation.exception_handling;

import java.sql.SQLException;
import java.util.List;

public class BookService {

	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public double getTotalPriceOfBooks() {
		List<Book> books = null;
		try {
			books = bookRepository.findAllBooks();
		} catch (SQLException e) {
			// log the exception
			throw new DatabaseReadException("Unable to read from database due to " + e.getMessage());

		}
		double totalPrice = 0;
		for (Book book : books) {
			totalPrice = totalPrice + book.getPrice();
		}
		return totalPrice;
	}

	public void addBook(Book book) {
		try {
			bookRepository.save(book);
		} catch (SQLException e) {
			// log exception
			throw new DatabaseWriteException("Unable to write data in database due to :" + e.getMessage());
		}
	}
}

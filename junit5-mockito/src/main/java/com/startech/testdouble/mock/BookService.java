package com.startech.testdouble.mock;

public class BookService {

	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void save(Book book) {
		if (book.getPrice() > 2500) {
			return;
		}
		bookRepository.save(book);
	}

}

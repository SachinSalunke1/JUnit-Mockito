package com.startech.mockito.annotation.argumentmatchers;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class BookService {

	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void addBook(Book book) {
		bookRepository.save(book);
	}

	public void addBook(BookRequest bookRequest) {
		if (bookRequest.getPrice() <= 599) {
			return;
		}
		Book book = new Book();
		book.setTitle(bookRequest.getTitle());
		book.setPrice(bookRequest.getPrice());
		book.setPublishedOn(bookRequest.getPublishedOn());
		bookRepository.save(book);
	}

	public void updatePrice(int bookId, double updatedPrice) {
		Book book = bookRepository.findBookById(bookId);
		book.setPrice(updatedPrice);
		bookRepository.save(book);
	}

	public Book getBookByTitleAndPublishedDate(String title, LocalDate publishedDate) {
		return bookRepository.findBookByTitleAndPublishedDate(title, publishedDate);
	}

	public Book getBookByTitleAndPriceAndIsDigital(String title, double price, boolean isDigital) {
		return bookRepository.findBookByTitleAndPriceAndIsDigital(title, price, isDigital);
	}
	
	public void addBooks(Collection<Book> books) {
		bookRepository.saveAll(books);
	}
}

package com.startech.mockito.annotation.argument_captor;

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
//		if (bookRequest.getPrice() <= 599) {
//			return;
//		}
		Book book = new Book();
		book.setTitle(bookRequest.getTitle());
		book.setPrice(bookRequest.getPrice());
		book.setPublishedOn(bookRequest.getPublishedOn());
		bookRepository.save(book);
	}

	public void updatePrice(int bookId, double updatedPrice) {
		if (bookId == 0) {
			return;
		}
		Book book = bookRepository.findBookById(bookId);
//		if (book.getPrice() == updatedPrice) {
//			return;
//		}
		book.setPrice(updatedPrice);
		bookRepository.save(book);
	}

}

package com.startech.testdoubles.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.startech.testdouble.mock.Book;
import com.startech.testdouble.mock.BookRepository;

public class MockBookRepositoryImpl implements BookRepository{

	int saveCalled = 0;
	Book lastSavedBook = null;

	@Override
	public void save(Book book) {
		saveCalled++;
		lastSavedBook = book;
	}
	
	public void verify(Book book,int times) {
		assertEquals(times, saveCalled);
		assertEquals(book, lastSavedBook);
	}
}

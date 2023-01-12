package com.startech.testdoubles.spy;

import com.startech.testdouble.spy.Book;
import com.startech.testdouble.spy.BookRepository;

public class SpyBookRepositoryImpl implements BookRepository {

	int saveCalled = 0;
	Book lastSavedBook = null;

	@Override
	public void save(Book book) {
		saveCalled++;
		lastSavedBook = book;
	}

	public int timesCalled() {
		return saveCalled;
	}

	public boolean calledWith(Book book) {
		return lastSavedBook.equals(book);
	}

}

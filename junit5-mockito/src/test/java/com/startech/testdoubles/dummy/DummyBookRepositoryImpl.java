package com.startech.testdoubles.dummy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.startech.testdouble.dummy.Book;
import com.startech.testdouble.dummy.BookRepository;


public class DummyBookRepositoryImpl implements BookRepository {

	// In memory Database or Hashmap, List

	Map<Integer, Book> bookStore = new HashMap<>();

	@Override
	public void save(Book book) {
		bookStore.put(book.getBookId(), book);
	}

	@Override
	public Collection<Book> findAll() {
		return bookStore.values();
	}

}

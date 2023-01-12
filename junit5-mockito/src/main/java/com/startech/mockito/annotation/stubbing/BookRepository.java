package com.startech.mockito.annotation.stubbing;

import java.util.List;

public interface BookRepository {

	List<Book> findNewBooks(int days);

	Book findBookByBookId(int bookId);

	void save(Book book);
}
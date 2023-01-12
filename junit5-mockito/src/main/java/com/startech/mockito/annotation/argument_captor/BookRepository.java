package com.startech.mockito.annotation.argument_captor;

public interface BookRepository {
	
	void save(Book book);

	Book findBookById(int bookId);
}

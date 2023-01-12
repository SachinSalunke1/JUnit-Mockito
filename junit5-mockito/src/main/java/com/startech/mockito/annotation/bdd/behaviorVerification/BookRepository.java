package com.startech.mockito.annotation.bdd.behaviorVerification;

public interface BookRepository {
	
	void save(Book book);

	Book findBookById(int bookId);
}

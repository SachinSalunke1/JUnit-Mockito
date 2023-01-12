package com.startech.mockito.annotation.argumentmatchers;

import java.time.LocalDate;
import java.util.Collection;

public interface BookRepository {
	
	void save(Book book);

	Book findBookById(int bookId);

	Book findBookByTitleAndPublishedDate(String title, LocalDate publishedDate);

	Book findBookByTitleAndPriceAndIsDigital(String title, double price, boolean isDigital);

	void saveAll(Collection<Book> books);
}

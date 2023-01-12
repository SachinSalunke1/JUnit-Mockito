package com.startech.testdoubles.spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.startech.testdouble.spy.Book;
import com.startech.testdouble.spy.BookRepository;
import com.startech.testdouble.spy.BookService;

public class SpyTest {

	@Test
	public void test_mockWithoutMockito() {
		SpyBookRepositoryImpl bookRepositorySpy = new SpyBookRepositoryImpl();
		BookService bookService = new BookService(bookRepositorySpy);

		Book book1 = new Book(1001, "Spring", 3000.0, LocalDate.now());
		Book book2 = new Book(1002, "Hibernate", 2500.0, LocalDate.now());

		bookService.save(book1);
		assertEquals(0, bookRepositorySpy.timesCalled());

		bookService.save(book2);
		assertEquals(1, bookRepositorySpy.timesCalled());

		// assertTrue(bookRepositorySpy.calledWith(book2));
	}

	@Test
	public void test_mockWithMockito() {
		BookRepository bookRepository = Mockito.spy(BookRepository.class);
		BookService bookService = new BookService(bookRepository);

		Book book1 = new Book(1001, "Spring", 3000.0, LocalDate.now());
		Book book2 = new Book(1002, "Hibernate", 2500.0, LocalDate.now());
		
		bookService.save(book1);
		bookService.save(book2);

		Mockito.verify(bookRepository, Mockito.times(0)).save(book1);
		Mockito.verify(bookRepository, Mockito.times(1)).save(book2);
	}

}

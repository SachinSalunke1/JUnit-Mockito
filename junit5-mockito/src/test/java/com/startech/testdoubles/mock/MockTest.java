package com.startech.testdoubles.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.startech.testdouble.mock.Book;
import com.startech.testdouble.mock.BookRepository;
import com.startech.testdouble.mock.BookService;

public class MockTest {

	@Test
	public void test_mockWithoutMockito() {
		MockBookRepositoryImpl bookRepositoryMock = new MockBookRepositoryImpl();
		BookService bookService = new BookService(bookRepositoryMock);

		Book book1 = new Book(1001, "Spring", 3000.0, LocalDate.now());
		Book book2 = new Book(1002, "Hibernate", 2500.0, LocalDate.now());

		bookService.save(book1); // return

		bookService.save(book2); // save will be called

		bookRepositoryMock.verify(book2, 1);

	}

	@Test
	public void test_mockWithMockito() {
		BookRepository bookRepository = Mockito.mock(BookRepository.class);
		BookService bookService = new BookService(bookRepository);

		Book book1 = new Book(1001, "Spring", 3000.0, LocalDate.now());
		Book book2 = new Book(1002, "Hibernate", 2500.0, LocalDate.now());

		bookService.save(book1); // return

		bookService.save(book2); // save will be called

//		Mockito.verify(bookRepository).save(book1);
//		Mockito.verify(bookRepository).save(book2);
		
		Mockito.verify(bookRepository, Mockito.times(0)).save(book1);
		Mockito.verify(bookRepository, Mockito.times(1)).save(book2);

	}

}
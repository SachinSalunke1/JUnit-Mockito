package com.startech.testdoubles.fake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.startech.testdouble.fake.Book;
import com.startech.testdouble.fake.BookRepository;
import com.startech.testdouble.fake.BookService;

public class FakeTest {

	@Test
	public void test_fakeWithoutMockito() {
		BookRepository bookRepository = new FakeBookRepositoryImpl();
		BookService bookService = new BookService(bookRepository);

		bookService.save(new Book(1001, "Spring", 3000.0, LocalDate.now()));
		bookService.save(new Book(1002, "Hibernate", 2500.0, LocalDate.now()));

		assertEquals(2, bookService.findNumberOfBooks());
	}

	@Test
	public void test_fakeWithMockito() {
		BookRepository bookRepository = Mockito.mock(BookRepository.class);
		BookService bookService = new BookService(bookRepository);

		Book book1 = new Book(1001, "Spring", 3000.0, LocalDate.now());
		Book book2 = new Book(1002, "Hibernate", 2500.0, LocalDate.now());

		Collection<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);

		when(bookRepository.findAll()).thenReturn(books);
		assertEquals(2, bookService.findNumberOfBooks());
	}

}

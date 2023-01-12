package com.startech.testdoubles.stub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.startech.testdouble.stub.Book;
import com.startech.testdouble.stub.BookRepository;
import com.startech.testdouble.stub.BookService;

public class StubTest {

	@Test
	public void test_stubWithoutMockito() {
		BookRepository bookRepository = new StubBookRepositoryImpl();
		BookService bookService = new BookService(bookRepository);
		
		List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);

		assertEquals(2, newBooks.size());
		assertEquals(2700, newBooks.get(0).getPrice());
		assertEquals(2250, newBooks.get(1).getPrice());

	}
	
	@Test
	public void test_stubWithMockito() {
		
		BookRepository bookRepository = Mockito.mock(BookRepository.class);
		BookService bookService = new BookService(bookRepository);
		
		Book book1 = new Book(1001, "Spring", 3000.0, LocalDate.now());
		Book book2 = new Book(1002, "Hibernate", 2500.0, LocalDate.now());

		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepository.findNewBooks(7)).thenReturn(books);
		List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);

		assertEquals(2, newBooks.size());
		assertEquals(2700, newBooks.get(0).getPrice());
		assertEquals(2250, newBooks.get(1).getPrice());

	}

}

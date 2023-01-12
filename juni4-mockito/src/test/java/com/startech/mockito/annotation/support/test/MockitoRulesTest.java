package com.startech.mockito.annotation.support.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.statech.mockito.annotations.support.Book;
import com.statech.mockito.annotations.support.BookRepository;
import com.statech.mockito.annotations.support.BookService;

public class MockitoRulesTest {

	private static final double DELTA = 1e-15; // to avoid AssertionError :Use assertEquals(expected, actual, delta) to
												// compare floating-point numbers

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@Test
	public void test_createMockUsingAnnotation() {
		// BookRepository bookRepository = Mockito.mock(BookRepository.class);
		// BookService bookService = new BookService(bookRepository);

		Book book1 = new Book(1001, "Spring", 3000.0, LocalDate.now());
		Book book2 = new Book(1002, "Hibernate", 2500.0, LocalDate.now());

		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);

		when(bookRepository.findNewBooks(7)).thenReturn(books);
		List<Book> newBooks = bookService.getNewBooksWithAppliedDiscount(10, 7);

		assertEquals(2, newBooks.size());
		assertEquals(2700, newBooks.get(0).getPrice(), DELTA);
		assertEquals(2250, newBooks.get(1).getPrice(), DELTA);

	}
}

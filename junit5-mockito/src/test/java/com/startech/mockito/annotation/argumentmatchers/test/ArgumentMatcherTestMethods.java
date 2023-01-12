package com.startech.mockito.annotation.argumentmatchers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.startech.mockito.annotation.argumentmatchers.Book;
import com.startech.mockito.annotation.argumentmatchers.BookRepository;
import com.startech.mockito.annotation.argumentmatchers.BookService;

@ExtendWith(MockitoExtension.class)
public class ArgumentMatcherTestMethods {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void test_updatePrice2() {
		Book book1 = new Book(101, "HIbernate", 599, LocalDate.now());
		Book book2 = new Book(101, "HIbernate", 499, LocalDate.now());
		when(bookRepository.findBookById(any(Integer.class))).thenReturn(book1);
		bookService.updatePrice(101, 499);
		verify(bookRepository).save(book2);
	}

	@Test
	public void test_invalidUserOfArgumentMatchers() {
		Book book1 = new Book(101, "HIbernate", 599, LocalDate.now());
		// when(bookRepository.findBookByTitleAndPublishedDate("HIbernate",
		// LocalDate.now())).thenReturn(book1);
		when(bookRepository.findBookByTitleAndPublishedDate(eq("HIbernate"), any())).thenReturn(book1);

		// Argument Matcher should be provided for all arguments
		// when(bookRepository.findBookByTitleAndPublishedDate("HIbernate",
		// any())).thenReturn(book1);//exception :Invalid use of argument matchers!

		// Argument Matcher can not be outside stubbing or verification
		// when(bookRepository.findBookByTitleAndPublishedDate(eq("HIbernate"),any()));//exception
		// :Invalid use of argument matchers!

		Book book = bookService.getBookByTitleAndPublishedDate("HIbernate", LocalDate.now());
		assertEquals("HIbernate", book.getTitle());
	}

	@Test
	public void test_specificTypeArgumentMatchers() {
		Book book1 = new Book(101, "HIbernate", 599, LocalDate.now());
		when(bookRepository.findBookByTitleAndPriceAndIsDigital(anyString(), anyDouble(), anyBoolean()))
				.thenReturn(book1);
		Book book = bookService.getBookByTitleAndPriceAndIsDigital("HIbernate", 599, true);
		assertEquals("HIbernate", book.getTitle());
	}

	@Test
	public void test_collectionTypeArgumentMatcher() {
		Book book1 = new Book(101, "HIbernate", 599, LocalDate.now());
		Book book2 = new Book(102, "Spring", 799, LocalDate.now());
		Book book3 = new Book(103, "Mockito", 399, LocalDate.now());
		Book book4 = new Book(104, "Junit", 399, LocalDate.now());
		Book book5 = new Book(105, "Mysql", 499, LocalDate.now());
		List<Book> bookList = new ArrayList<>();
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);

		bookService.addBooks(bookList);
		verify(bookRepository).saveAll(anyList());// anySet() anyCollection() anyMap()
	}

	@Test
	public void test_stringTypeArgumentMatcher() throws UnknownHostException {
		Book book1 = new Book(101, "Mockito In Action", 599, LocalDate.now());
		when(bookRepository.findBookByTitleAndPriceAndIsDigital(startsWith("Mock"), anyDouble(), anyBoolean()))
				.thenReturn(book1);
		Book book = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 599, true);
		assertEquals("Mockito In Action", book.getTitle());
	}

}

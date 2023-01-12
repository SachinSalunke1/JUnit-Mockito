package com.startech.mockito.annotation.bdd.stubbing.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.BDDMockito.Then;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.startech.mockito.annotation.bdd.stubbing.Book;
import com.startech.mockito.annotation.bdd.stubbing.BookRepository;
import com.startech.mockito.annotation.bdd.stubbing.BookService;

@ExtendWith(MockitoExtension.class)
public class BehaviorDrivenDevelopmentTestMethods {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void test_stubWithMockito() {
		
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
	
	@Test
	@DisplayName("given newBooks when getNewBooksWithAppliedDiscountIscalled then newBooksWithAppliedDiscountReturned")
	public void test_given_newBooks_when_getNewBooksWithAppliedDiscountIscalled_then_newBooksWithAppliedDiscountReturned() {
		//Arrange :given
		Book book1 = new Book(1001, "Spring", 3000.0, LocalDate.now());
		Book book2 = new Book(1002, "Hibernate", 2500.0, LocalDate.now());

		List<Book> newBooks = new ArrayList<>();
		newBooks.add(book1);
		newBooks.add(book2);
		
		BDDMockito.given(bookRepository.findNewBooks(7)).willReturn(newBooks);
		
		//Act :when
		List<Book> newBookswithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);
		
		//Assert :then
//		assertEquals(2, newBooks.size());
//		assertEquals(2700, newBooks.get(0).getPrice());
//		assertEquals(2250, newBooks.get(1).getPrice());
		
		//AssertJ -BDDAssertions
		BDDAssertions.then(newBookswithAppliedDiscount).isNotNull();
		BDDAssertions.then(newBookswithAppliedDiscount.size()).isEqualTo(2);
		BDDAssertions.then(newBookswithAppliedDiscount.get(0).getPrice()).isEqualTo(2700);
	}
}

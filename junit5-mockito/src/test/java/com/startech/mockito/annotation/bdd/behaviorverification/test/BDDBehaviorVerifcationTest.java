package com.startech.mockito.annotation.bdd.behaviorverification.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.startech.mockito.annotation.bdd.behaviorVerification.Book;
import com.startech.mockito.annotation.bdd.behaviorVerification.BookRepository;
import com.startech.mockito.annotation.bdd.behaviorVerification.BookService;

public class BDDBehaviorVerifcationTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@Test
	public void test_updatePrice2() {
		Book book = new Book(101, "HIbernate", 599, LocalDate.now());
		when(bookRepository.findBookById(101)).thenReturn(book);
		bookService.updatePrice(101, 599);
		verify(bookRepository).findBookById(101);
		verify(bookRepository).save(null);
		verifyNoMoreInteractions(bookRepository);
	}

	@Test
	public void test_given_book_when_updatePriceIsCalledWithNewPrice_thenBookPriceIsUpated() {
		// Given :Arrange
		Book book = new Book(101, "HIbernate", 599, LocalDate.now());
		BDDMockito.given(bookRepository.findBookById(101)).willReturn(book);
		// when : Act
		bookService.updatePrice(101, 599);
		// then :Assert
		//BDDAssertions.then(bookRepository).should().save(book);
	}

}

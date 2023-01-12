package com.startech.mockito.annotation.spies.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.startech.mockito.annotation.spies.BookManager;
import com.startech.mockito.annotation.spies.BookService;
import com.startech.mockito.annotation.spies.Book;

@ExtendWith(MockitoExtension.class)
public class SpiesTest {

	@InjectMocks
	private BookManager bookManager;
	
	@Spy
	private BookService bookService;

	@Test
	public void test_mockitoSpy() {
		Book book1 = new Book(101, "Spring", 3500.0, LocalDate.now());
		doReturn(book1).when(bookService).findBook(101);
//		when(bookService.findBook(101)).thenReturn(book1);
		double actualDiscount = bookManager.applyDiscountOnBook(101, 10);
		assertEquals(3150, actualDiscount);
		
	}

}

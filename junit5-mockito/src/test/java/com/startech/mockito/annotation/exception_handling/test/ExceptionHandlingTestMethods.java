package com.startech.mockito.annotation.exception_handling.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.startech.mockito.annotation.exception_handling.Book;
import com.startech.mockito.annotation.exception_handling.BookRepository;
import com.startech.mockito.annotation.exception_handling.BookService;
import com.startech.mockito.annotation.exception_handling.DatabaseReadException;
import com.startech.mockito.annotation.exception_handling.DatabaseWriteException;

@ExtendWith(MockitoExtension.class)
public class ExceptionHandlingTestMethods {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;
	
	@Test
	public void test_totalPriceOfBooks() throws SQLException {
		when(bookRepository.findAllBooks()).thenThrow(SQLException.class);
		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
	}

	@Test
	public void test_totalPriceOfBooks1() throws SQLException {
		when(bookRepository.findAllBooks()).thenThrow(new SQLException("Database not available..!"));
		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
	}
	
	@Test
	public void test_totalPriceOfBooks2() throws SQLException {
		BDDMockito.given(bookRepository.findAllBooks()).willThrow(SQLException.class);
		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
	}

	@Test
	public void test_addBooks() throws SQLException {
		Book book = new Book(101, "Spring", 599, LocalDate.now());
		doThrow(SQLException.class).when(bookRepository).save(book);
		assertThrows(DatabaseWriteException.class, () -> bookService.addBook(book));
	}

}

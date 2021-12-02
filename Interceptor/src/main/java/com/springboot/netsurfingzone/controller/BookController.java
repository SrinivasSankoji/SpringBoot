package com.springboot.netsurfingzone.controller;

import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.netsurfingzone.model.Book;

@RestController
@RequestMapping("/book")
public class BookController {

	@RequestMapping(value = "/getbook", method = RequestMethod.GET)
	@ResponseBody
	public Book getBook() {
		Book book = new Book();
		book.setBookId(1);
		book.setBookName("rich dad poor dad");
		book.setBookPrice("10");
		return book;
	}

	@RequestMapping(value = "/getasync", method = RequestMethod.GET)
	public Callable<Book> handleTestRequest() {
		Callable<Book> callable = new Callable<Book>() {
			public Book call() throws Exception {
				Thread.sleep(100);
				Book book = new Book();
				book.setBookId(1);
				book.setBookName("rich dad poor dad");
				book.setBookPrice("10");
				return book;
			}
		};
		return callable;
	}
}

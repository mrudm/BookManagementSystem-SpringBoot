package com.mrudula.crud_mysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mrudula.crud_mysql.model.Book;
import com.mrudula.crud_mysql.services.BookService;

import jakarta.validation.Valid;

@RestController
public class BookController {
	
	@Autowired 
	BookService bookService;
	
	
	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks()
	{
		return bookService.getAllBooks();
		
	}
	
	
	@PostMapping("/books")
	public ResponseEntity<?> createBook(@RequestBody @Valid Book book)
	{	
		return bookService.createBook(book);
	}

	
	@GetMapping("/books/{id}")
	public ResponseEntity<?>getBookById(@PathVariable int id)
	{
		return bookService.getBookById(id);
		
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable int id)
	{
		return bookService.deleteBookById(id);
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<?> updateBookById(@PathVariable int id,@RequestBody Book book)
	{
		return bookService.updateBookById(id,book);
	}
	
	@GetMapping("/books/by-name")  //books/by-name?bookname=bootstrapp
	public ResponseEntity<?> getBookByName(String bookname)
	{
		return bookService.getBookByName(bookname);
	}
	
	
	@GetMapping("/books/by-author") 
	public ResponseEntity<?>getBookByAuthorName(@RequestParam String authorname)
	{
		return bookService.getBookByAuthorName(authorname);
	}
	
	@GetMapping("/books/by-name-author")
	public ResponseEntity<?>getBookByBookNameAuthorName(@RequestParam String bookname,String authorname)
	{
		return bookService.getBookByBookNameAndAuthorName(bookname, authorname);
		
	}
	
	@GetMapping("/books/by-price") 
	public ResponseEntity<?>getByPriceLessThan(@RequestParam Double price)
	{
		return bookService.getByPriceLessThan(price);
	}
	
	@GetMapping("/book-without-price/{id}")
	public ResponseEntity<?> getBookByWithoutPrice(@PathVariable int id)
	{
		return bookService.getBookByWithoutPrice(id);
	}
	
}

package com.mrudula.crud_mysql.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mrudula.crud_mysql.model.Book;
import com.mrudula.crud_mysql.projection.BookProjection;
import com.mrudula.crud_mysql.repsitories.BookRepository;
import com.mrudula.crud_mysql.wrapper.ResponseWrapper;

@Service
public class BookService {
	
	
	@Autowired
	BookRepository bookRespository;
	
	@Autowired
	ResponseWrapper responsewrapper;
	
	public ResponseEntity<?> getAllBooks()
	{
		Iterable<Book> allBooks=bookRespository.findAll();
		responsewrapper.setMessage("following books found");
		responsewrapper.setData(allBooks);
		return new ResponseEntity<>(responsewrapper,HttpStatus.OK);
	}
	
	public ResponseEntity<?> createBook(Book book)
	{
		Book createdBook=bookRespository.save(book);
		responsewrapper.setMessage("following book added");
		responsewrapper.setData(createdBook);
		return new ResponseEntity<>(responsewrapper,HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> getBookById(int id)
	{
		Book book_found=bookRespository.findById(id).orElseThrow(
				() ->
				{
					throw new ResponseStatusException
					(HttpStatus.NOT_FOUND,id+"id does not exist");
				}
				);
		responsewrapper.setMessage("following book found with id" + id);
		responsewrapper.setData(book_found);
		return new ResponseEntity<>(responsewrapper,HttpStatus.FOUND);
	}
	
	
	public ResponseEntity<?> deleteBookById(int id)
	{
		 bookRespository.findById(id).orElseThrow(
				() ->
				{
					throw new ResponseStatusException
					(HttpStatus.NOT_FOUND,id+"id does not exist");
				}
				);
		bookRespository.deleteById(id);
		responsewrapper.setMessage("book with id" + id +"deleted");
		responsewrapper.setData(null);
		return new ResponseEntity<>(responsewrapper,HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateBookById(int id,Book book)
	{
		 Book book_found=bookRespository.findById(id).orElseThrow(
				() ->
				{
					throw new ResponseStatusException
					(HttpStatus.NOT_FOUND,id+"id does not exist");
				}
				);
		book.setId(book_found.getId());
		book.setCreatedAt(book_found.getCreatedAt());
		Book updated_book=bookRespository.save(book);
		responsewrapper.setMessage("book with id" + id +"updated");
		responsewrapper.setData(updated_book);
		return new ResponseEntity<>(responsewrapper,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getBookByName(String bookname)
	{
		Book book_found=bookRespository.findByBookName(bookname).orElseThrow(
				() ->
				{
					throw new ResponseStatusException
					(HttpStatus.NOT_FOUND,"book with name "+bookname+" not found");
				}
				);
		responsewrapper.setMessage("following book details found for the book" + bookname);
		responsewrapper.setData(book_found);
		return new ResponseEntity<>(responsewrapper,HttpStatus.FOUND);
	}
	
	public ResponseEntity<?>getBookByAuthorName(String authorname)
	{
		List<Book> books=bookRespository.findByAuthorName(authorname);
		if(books.size()==0)
		{
			throw new ResponseStatusException
			(HttpStatus.NOT_FOUND,"There are no books written by author "+authorname);
		}
		
		responsewrapper.setMessage("following books for " +authorname+ " found");
		responsewrapper.setData(books);
		return new ResponseEntity<>(responsewrapper,HttpStatus.FOUND);
	}
	
	public ResponseEntity<?> getBookByBookNameAndAuthorName(String bookname,String authorname)
	{
		Book book_found=bookRespository.findByBookNameAndAuthorName(bookname,authorname)
				.orElseThrow(
				() ->
				{
					throw new ResponseStatusException
					(HttpStatus.NOT_FOUND,"there are no books found by given" + bookname + authorname);
				}
				);
		responsewrapper.setMessage("following books found");
		responsewrapper.setData(book_found);
		return new ResponseEntity<>(responsewrapper,HttpStatus.FOUND);
	}
	
	public ResponseEntity<?>getByPriceLessThan(Double price)
	{
		List<Book> books=bookRespository.findByPriceLessThan(price);
		if(books.size()==0)
		{
			throw new ResponseStatusException
			(HttpStatus.NOT_FOUND,"There are no books with price less than "+price);
		}
		
		responsewrapper.setMessage("following books for " +price+ " found");
		responsewrapper.setData(books);
		return new ResponseEntity<>(responsewrapper,HttpStatus.FOUND);
	}
	
	public ResponseEntity<?> getBookByWithoutPrice(int id)
	{
		BookProjection book_found=bookRespository.findProjectedById(id).orElseThrow(
				() ->
				{
					throw new ResponseStatusException
					(HttpStatus.NOT_FOUND,id+"id does not exist");
				}
				);
		responsewrapper.setMessage("following book found with id" + id);
		responsewrapper.setData(book_found);
		return new ResponseEntity<>(responsewrapper,HttpStatus.FOUND);
	}
	

}

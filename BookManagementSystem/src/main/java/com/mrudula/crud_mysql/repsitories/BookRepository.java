package com.mrudula.crud_mysql.repsitories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mrudula.crud_mysql.model.Book;
import com.mrudula.crud_mysql.projection.BookProjection;


@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {

	Optional<Book> findByBookName(String bookname);
	
	List<Book>findByAuthorName(String authorname);
	
	Optional<Book>findByBookNameAndAuthorName(String bookname,String authorname);
	
	List<Book>findByPriceLessThan(Double price);
	
	Optional<BookProjection>findProjectedById(int id);

}

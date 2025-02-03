package com.mrudula.crud_mysql.projection;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;

public interface BookProjection {
	
	@Value("#{target.id}")
	int getid();
	
	@Value("#{target.bookName}")
	String getBookName();
	
	@Value("#{target.authorName}")
	String getAuthorName();
	
	@Value("#{target.createdAt}")
	Instant getCreatedAt();
	
	@Value("#{target.updatedAt}")
	Instant getUpdatedAt();

}

package com.mrudula.crud_mysql.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data  //to create constructor ,getter and setter automatically
@EntityListeners(AuditingEntityListener.class)
public class Book {
	
	@Id //for primary key
	@GeneratedValue(strategy=GenerationType.AUTO)  //for auto increment
	private int id;
	
	
	@Size(min=5, max=10, message="Book name must be between 5 to 10 characters")
	@NotNull(message="Please provide book name")
	@Column(nullable=false , unique=true)
	private String bookName;
	
	@Size(min=5, max=10, message="Book name must be between 5 to 10 characters")
	@NotNull(message="Please provide author name")
	@Column(nullable=false)
	private String authorName;
	
	@Min(value=100, message="min price must be 100")
	@Max(value=200, message="max price must be 200")
	@NotNull(message="Please provide price")
	@Column(nullable=false)
	private double price;
	
	@CreatedDate
	private Instant createdAt;
	
	@LastModifiedDate
	private Instant updatedAt;
		
	

}

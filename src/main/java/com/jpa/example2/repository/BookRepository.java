package com.jpa.example2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.example2.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
	List<Book> findByAuthor(String author);
	List<Book> findByBooktitleContaining(String booktitle);
}

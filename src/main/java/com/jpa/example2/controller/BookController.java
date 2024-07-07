package com.jpa.example2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.example2.entity.Book;
import com.jpa.example2.repository.BookRepository;

@RestController
@RequestMapping("/e2")
public class BookController {
	@Autowired
	BookRepository bookRepo;
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required=false) String booktitle) //api1
	{
		try
		{
			List<Book> books=new ArrayList<Book>();
			if(booktitle==null)
				bookRepo.findAll().forEach(books::add);
			else
				bookRepo.findByBooktitleContaining(booktitle).forEach(books::add);
			if(books.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(books,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/books/{bookid}")
	public ResponseEntity<Book> getBookById(@PathVariable("bookid")long bookid) //api2
	{
		Optional<Book> bookData=bookRepo.findById(bookid);
		if(bookData.isPresent())
			return new ResponseEntity<>(bookData.get(),HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book) //api3
	{
		try
		{
			Book addBook=new Book(book.getBookid(),book.getBooktitle(),book.getBookdesc(),book.getAuthor());
			Book bookRecord=bookRepo.save(addBook);
			return new ResponseEntity<>(bookRecord,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/books/{bookid}")
	public ResponseEntity<Book> updateBook(@PathVariable("bookid") long bookid,@RequestBody Book book) //api4
	{
		Optional<Book> bookData=bookRepo.findById(bookid);
		if(bookData.isPresent())
		{
			Book updateBook=new Book(book.getBookid(),book.getBooktitle(),book.getBookdesc(),book.getAuthor());
			return new ResponseEntity<>(bookRepo.save(updateBook),HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/books/{bookid}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("bookid") long bookid) //api5
	{
		try
		{
			bookRepo.deleteById(bookid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/books")
	public ResponseEntity<HttpStatus>deleteAllBooks() //api6
	{
		try
		{
			bookRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	@GetMapping("/books/{author}")
//	public ResponseEntity<List<Book>> findByAuthor(@PathVariable("author") String author)
//	{
//		try
//		{
//			List<Book> books=bookRepo.findByAuthor(author);
//			if(books.isEmpty())
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			return new ResponseEntity<>(books,HttpStatus.OK);
//		}
//		catch(Exception e)
//		{
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}

//Post JSON
//{
//    "booktitle":"WATCH ME",
//    "bookdesc":"2024",
//    "author": "LIV MORGAN"
//}
package com.jpa.example2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long bookid;
	@Column(name="booktitle")
	private String booktitle;
	@Column(name="bookdesc")
	private String bookdesc;
	@Column(name="author")
	private String author;
	public Book(long bookid, String booktitle, String bookdesc, String author) {
		super();
		this.bookid = bookid;
		this.booktitle = booktitle;
		this.bookdesc = bookdesc;
		this.author = author;
	}
	public Book() {
		super();
	}
	public long getBookid() {
		return bookid;
	}
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getBookdesc() {
		return bookdesc;
	}
	public void setBookdesc(String bookdesc) {
		this.bookdesc = bookdesc;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", booktitle=" + booktitle + ", bookdesc=" + bookdesc + ", author=" + author
				+ "]";
	}
}

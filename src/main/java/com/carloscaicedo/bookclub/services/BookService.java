package com.carloscaicedo.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carloscaicedo.bookclub.models.Book;
import com.carloscaicedo.bookclub.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	// ******* CRUD *******
	
	// ******* find all *******
	public List<Book> allBooks(){
		return bookRepo.findAll();
	}
	// ******* create *******
	public Book createBook(Book book) {
		return bookRepo.save(book);
	}
	// ******* find one *******
	public Book findOneBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	// ******* update/edit *******
	public Book editBook(Book book) {
		return bookRepo.save(book);
	}
	// ****** delete ********
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}
	
}
// Book book books
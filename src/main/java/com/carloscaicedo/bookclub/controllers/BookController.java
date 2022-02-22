package com.carloscaicedo.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.carloscaicedo.bookclub.models.Book;
import com.carloscaicedo.bookclub.models.LoginUser;
import com.carloscaicedo.bookclub.models.User;
import com.carloscaicedo.bookclub.services.BookService;
import com.carloscaicedo.bookclub.services.UserService;

@Controller
public class BookController {

	@Autowired
	private BookService bookServ;

	@Autowired
	private UserService userServ;

	// ******** Create Book ********
	@GetMapping("/books/new")
	private String createBookPage(@ModelAttribute("book")Book book, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "login.jsp";
		}
		return "createBook.jsp";		
	}

	// ******** Process Book Form ********
	@PostMapping("/books/new")
	public String processBookForm(@Valid @ModelAttribute("book")Book book, BindingResult result,
			Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		if (result.hasErrors()) {
			return "createBook.jsp";
		}
		User user = userServ.getOneUser((Long) session.getAttribute("userId"));
		book.setUser(user);
		bookServ.createBook(book);
		return "redirect:/books";
	}

	// ******** Display Book ********
	@GetMapping("/books/{id}")
	private String showBook(@PathVariable("id")Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "login.jsp";
		}
		Book book = bookServ.findOneBook(id);
		User user = userServ.getOneUser((Long) session.getAttribute("userId"));
		model.addAttribute("userId", user.getId());
		model.addAttribute("book", book);
		return "displayBook.jsp";		
	}

	// ******** Edit Book ********
	@GetMapping("/books/{id}/edit")
	private String editBookPage(@PathVariable("id") Long id, Model model, HttpSession session) {
		Book book = bookServ.findOneBook(id);
		if (session.getAttribute("userId") == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "login.jsp";
		}
		if (session.getAttribute("userId") != (book.getUser().getId())) {
			User user = userServ.getOneUser((Long) session.getAttribute("userId"));
			List<Book> listBooks = bookServ.allBooks();
			model.addAttribute("listBooks", listBooks);
			model.addAttribute("user", user);
			return "dashboard.jsp";
		}
		model.addAttribute("book", book);
		return "editBook.jsp";		
	}
	// ******** Process Edit Book Form ********
	@PutMapping("/books/{id}/edit")
	private String processEditBook(@Valid @ModelAttribute("book") 
	Book book, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "editBook.jsp";
		}
		User user = userServ.getOneUser((Long) session.getAttribute("userId"));
		book.setUser(user);
		bookServ.editBook(book);
		return "redirect:/books";		
	}		
}

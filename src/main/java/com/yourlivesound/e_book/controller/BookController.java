package com.yourlivesound.e_book.controller;

import com.yourlivesound.e_book.model.EBook;
import com.yourlivesound.e_book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.print.Book;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }
    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new EBook());
        return "addBook";
    }
    @PostMapping("/books")
    public String addBook(@ModelAttribute EBook book){
        bookRepository.save(book);
        return "redirect:/books";
    }
}

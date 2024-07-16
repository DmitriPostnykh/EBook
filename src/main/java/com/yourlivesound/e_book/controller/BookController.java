package com.yourlivesound.e_book.controller;

import com.yourlivesound.e_book.model.EBook;
import com.yourlivesound.e_book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addBook(@ModelAttribute("book") EBook book, RedirectAttributes redirectAttributes) {
        bookRepository.save(book);
        redirectAttributes.addFlashAttribute("message", "Book added successfully!");
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        EBook book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/books/update")
    public String updateBook(@ModelAttribute("book") EBook book, RedirectAttributes redirectAttributes) {
        bookRepository.save(book);
        redirectAttributes.addFlashAttribute("message", "Book updated successfully!");
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            EBook book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
            bookRepository.delete(book);
            redirectAttributes.addFlashAttribute("message", "Book deleted successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/books";
    }
}
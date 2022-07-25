package ru.strelkov.lib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.strelkov.lib.dao.BookDAO;
import ru.strelkov.lib.dao.PersonDAO;
import ru.strelkov.lib.models.Book;
import ru.strelkov.lib.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        System.out.println(bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        Book book = new Book();
        book = bookDAO.show(id);
        if (book.getId() != null) {
            int x = book.getId();
            model.addAttribute("person", bookDAO.showPersonBook(x));
        }
        model.addAttribute("persons", personDAO.index());
        return "books/show";
    }

    @PatchMapping("/{id}/ownerupdate")
    public String updateBook(@ModelAttribute("book") @Valid Book book, @ModelAttribute("persons") Person person,
                             @PathVariable("id") int book_id) {
        int id = person.getId();
        bookDAO.updateOwner(id, book_id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/ownerdelete")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteOwner(id);

        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book ) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
        BindingResult bindingResult) {
    if (bindingResult.hasErrors())
        return "books/new";

    bookDAO.save(book);
    return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }


}

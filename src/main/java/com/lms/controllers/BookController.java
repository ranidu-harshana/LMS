package com.lms.controllers;

import com.lms.Helpers;
import com.lms.beans.Book;
import com.lms.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.lms.Helpers.*;

@Controller
public class BookController {
    HttpSession session;
    @Autowired
    BookDao bookDao;

    public BookController(HttpSession session) {
        this.session = session;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String index(Model model) {
        List<Book> booksList = bookDao.booksList();
        model.addAttribute("booksList", booksList);
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
        return "Books/all_books";
    }

    @RequestMapping(value = "/createbook", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
        //  check if user is logged in and is admin
        String x = checkRole(this.session);
        if (x != null) return x;

        return "Books/add_book";
    }

    @RequestMapping(value = "/viewbook/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model) {
        try {
            Book book = bookDao.findById(id);
            model.addAttribute("isAdmin", Helpers.checkAdmin(session));
            model.addAttribute("book", book);
            return "Books/view_book";
        } catch (Exception e) {
            return "redirect:/books";
        }
    }

    @RequestMapping(value = "/storebook", method = RequestMethod.POST)
    public String store(@ModelAttribute("book") Book book) {
        // check if user is logged in and is admin
        String x = checkRole(this.session);
        if (x != null) return x;

        setSessionMessage(session, bookDao.save(book), "Book saved successfully", "Book not saved successfully");
        return "redirect:/books";
    }

    @RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        // check if user is logged in and is admin
        String x = checkRole(this.session);
        if (x != null) return x;

        try {
            Book book = bookDao.findById(id);
            model.addAttribute("book", book);
            model.addAttribute("isAdmin", Helpers.checkAdmin(session));
            return "Books/edit_book";
        } catch (Exception e) {
            return "redirect:/books";
        }
    }

    @RequestMapping(value = "/updatebook", method = RequestMethod.POST)
    public String update(@ModelAttribute("book") Book book) {
        // check if user is logged in and is admin
        String x = checkRole(this.session);
        if (x != null) return x;

        setSessionMessage(session, bookDao.update(book), "Book updated successfully", "Book not updated successfully");
        return "redirect:/books";
    }

    @RequestMapping(value = "/deletebook", method = RequestMethod.POST)
    public String delete(HttpServletRequest request) {
        // check if user is logged in and is admin
        String x = checkRole(this.session);
        if (x != null) return x;

        setSessionMessage(session, bookDao.delete(Integer.parseInt(request.getParameter("bookId"))), "Book deleted successfully", "Book not deleted successfully");
        return "redirect:/books";
    }
}
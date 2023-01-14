package com.lms.controllers;

import com.lms.Helpers;
import com.lms.beans.Book;
import com.lms.beans.User;
import com.lms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.lms.Helpers.checkRole;
import static com.lms.Helpers.setSessionMessage;

@Controller
public class UserController {
    HttpSession session;
    @Autowired
    UserDao userDao;

    public UserController(HttpSession session) {
        this.session = session;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String index(Model model) {
        List<User> usersList = userDao.usersList();
        model.addAttribute("usersList", usersList);
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
        return "Users/all_users";
    }

    @RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
    public String adminDashboard() {
        return "Users/admin_dashboard";
    }

    @RequestMapping(value = "/loginuser", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        if (userDao.validateUser(email, Helpers.getMd5(password))) {
            session.setAttribute("email", email);
            session.setAttribute("role", userDao.findUser(email).getRole() == 1 ? "admin" : "normal");
            return "redirect:/admindashboard";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/logoutuser", method = RequestMethod.POST)
    public String logoutUser() {
        session.removeAttribute("email");
        session.removeAttribute("role");
        return "redirect:/";
    }

    @RequestMapping(value = "/registeruser", method = RequestMethod.GET)
    public String create() {
        //  check if user is logged in and is admin
        String x = checkRole(this.session);
        if (x != null) return x;

        return "Users/register_newuser";
    }

    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String store(@ModelAttribute("user") User user) {
        // check if user is logged in and is admin
        String x = checkRole(this.session);
        if (x != null) return x;

        setSessionMessage(session, userDao.save(user), "User registered successfully", "User not registered successfully");
        return "redirect:/";
    }
}

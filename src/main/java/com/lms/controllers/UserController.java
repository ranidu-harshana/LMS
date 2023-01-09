package com.lms.controllers;

import com.lms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    HttpSession session;
    @Autowired
    UserDao userDao;

    public UserController(HttpSession session) {
        this.session = session;
    }

    @RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
    public String adminDashboard() {
        return "Users/admin_dashboard";
    }

    @RequestMapping(value = "/loginuser", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        if (userDao.validateUser(email, password)) {
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
}

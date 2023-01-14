package com.lms.controllers;

import com.lms.Helpers;
import com.lms.beans.Book;
import com.lms.beans.ChangePassword;
import com.lms.beans.User;
import com.lms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        //  check if user is logged in and is admin
        String x = checkRole(this.session);
        if (x != null) return x;

        List<User> usersList = userDao.usersList();
        model.addAttribute("usersList", usersList);
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
        return "Users/all_users";
    }

    @RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
    public String adminDashboard(Model model) {
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
        return "Users/admin_dashboard";
    }

    @RequestMapping(value = "/loginuser", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        if (userDao.validateUser(email, Helpers.getMd5(password))) {
            session.setAttribute("userId", userDao.findUser(email).getId());
            session.setAttribute("role", userDao.findUser(email).getRole() == 1 ? "admin" : "normal");
            return "redirect:/admindashboard";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/logoutuser", method = RequestMethod.POST)
    public String logoutUser() {
        session.removeAttribute("userId");
        session.removeAttribute("role");
        return "redirect:/";
    }

    @RequestMapping(value = "/registeruser", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
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
        return "redirect:/users";
    }

    @RequestMapping(value = "/changepass", method = RequestMethod.GET)
    public String changePasswordForm(Model model) {
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
        return "Users/change_password";
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("changePassword") ChangePassword changePassword, HttpSession session, Model model) {
        changePassword.setId((Integer) session.getAttribute("userId"));
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
        setSessionMessage(session, userDao.changePassword(changePassword), "Password Changed successfully", "Password not changed successfully");
        return "Users/change_password";
    }
}

package com.lms.controllers;

import com.lms.Helpers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String getHome(HttpSession session, Model model) {
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
        return session.getAttribute("userId") == null ? "index" : "redirect:/admindashboard";
    }

    @RequestMapping(value = "/index")
    public String getHomeIndex(HttpSession session, Model model) {
        model.addAttribute("isAdmin", Helpers.checkAdmin(session));
        return session.getAttribute("userId") == null ? "index" : "redirect:/admindashboard";
    }
}

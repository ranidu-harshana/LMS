package com.lms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String getHome(HttpSession session) {
        return session.getAttribute("email") == null ? "index" : "redirect:/admindashboard";
    }

    @RequestMapping(value = "/index")
    public String getHomeIndex(HttpSession session) {
        return session.getAttribute("email") == null ? "index" : "redirect:/admindashboard";
    }
}

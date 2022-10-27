package com.test1.demo.controllers;

import com.test1.demo.repo.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController{

    @GetMapping("/about")
    public String aboutMain(Model model){
        model.addAttribute("about","Страница про нас");
        return "about-main";
    }
}

package com.test1.demo.controllers;

import com.test1.demo.models.UserModel;
import com.test1.demo.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UserModel());
        return "register-page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return "login-page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel, Model model){
        System.out.println("register request " + userModel);
        UserModel registeredUser = usersService.registerUser(userModel.getLogin(), userModel.getPassword(), userModel.getEmail());
        return registeredUser == null ? "error-page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model){
        System.out.println("login request " + userModel);
        UserModel authenticate = usersService.authenticate(userModel.getLogin(), userModel.getPassword());
        if(authenticate != null) {
            model.addAttribute("userLogin", authenticate.getLogin());
            return "personal-page";
        }
        else
            return "error-page";
    }

}

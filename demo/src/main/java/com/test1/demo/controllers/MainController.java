package com.test1.demo.controllers;

import com.test1.demo.Email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller// отвечает за переход (например перешл на главную или на вторую и тд)
public class MainController {

    @Autowired
    private EmailSenderService senderService;

    @GetMapping("/") // URL-адресс под названием ... ( / <- это означает главная страница)
    public String home(Model model) { // Model - обязательный параметр(создавать html файлы под названием их котроллеров-методов)
        model.addAttribute("title", "Главная страница"); // при помощи Model мы добавляем title
        return "home"; // тут открывает шаблон под названием - home(поэтим названием создаем html файлы)
    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void sender(){
//        senderService.sendEmail("dimokzhasulanov@gmail.com",
//                "Dimok", "Dimok");
//    }



}

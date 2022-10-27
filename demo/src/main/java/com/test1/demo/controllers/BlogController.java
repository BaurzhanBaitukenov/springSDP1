package com.test1.demo.controllers;

import com.test1.demo.models.Post;
import com.test1.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired // ссылается на переменную который мы вводим снизу
    private PostRepository postRepository;

    @GetMapping("/blog") // Get - просто переход
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll(); // перечесление данных в бд (<Post - указываем модельку с которой работаем>)
        model.addAttribute("posts", posts); // название массива "posts"
        return "blog-main"; // поэтим названием создаем html файлы
    }


    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add"; // поэтим названием создаем html файлы
    }


    @PostMapping("/blog/add") // Post - сами будем данные писать (+ получаем данные при помощи post)
    private String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){ // @ReqParam - поучение новых параметров(title - название параметра и тд)
        Post post = new Post(title,anons,full_text);
        postRepository.save(post); // сохраняем обьект
        return "redirect:/blog";
    }


    @GetMapping("/blog/{id}") // динамически будут подставлятся цифры
    public String blogDetails(@PathVariable(value = "id") long id, Model model){ // PathVar - добавляет автоматом цифры
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
            Optional<Post> post = postRepository.findById(id);
            ArrayList<Post> res = new ArrayList<>();
            post.ifPresent(res::add);
            model.addAttribute("post", res);
            return "blog-details"; // поэтим названием создаем html файл

    }


    @GetMapping("/blog/{id}/edit") // динамически будут подставлятся цифры
    public String blogEdit(@PathVariable(value = "id") long id, Model model){ // PathVar - добавляет автоматом цифры
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit"; // поэтим названием создаем html файл
    }


    @PostMapping("/blog/{id}/edit") // Post - сами будем данные писать (+ получаем данные при помощи post)
    private String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){ // @ReqParam - поучение новых параметров(title - название параметра и тд)
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);

        return "redirect:/blog";
    }


    @PostMapping("/blog/{id}/remove") // Post - сами будем данные писать (+ получаем данные при помощи post)
    private String blogPostRemove(@PathVariable(value = "id") long id, Model model){ // @ReqParam - поучение новых параметров(title - название параметра и тд)
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);

        return "redirect:/blog";
    }

}

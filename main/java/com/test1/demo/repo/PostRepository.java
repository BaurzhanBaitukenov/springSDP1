package com.test1.demo.repo;

import com.test1.demo.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> { // CrudRep - готовый интерфейс который выполняет круд операция(<Post - моделька с которой работаем>)
}

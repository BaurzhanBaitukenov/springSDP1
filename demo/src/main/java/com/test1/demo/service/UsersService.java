package com.test1.demo.service;


import com.test1.demo.models.UserModel;
import com.test1.demo.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserModel registerUser(String login, String password, String email){
        if(login != null && password != null){
            if(userRepository.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            return userRepository.save(userModel);
        } else {
            return null;
        }
    }

    public UserModel authenticate(String login, String password){
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }

}

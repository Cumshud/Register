package com.example.registerr.service;

import com.example.registerr.entity.Role;
import com.example.registerr.entity.Ucerr;
import com.example.registerr.reposiyory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Ucerr save(Ucerr ucerr) {
        Ucerr newUcerr =new Ucerr(ucerr.getFirstName(), ucerr.getLastName(), ucerr.getEmail(), ucerr.getPassword(),
        List.of(new Role("UCER")));
       return userRepository.save(newUcerr);
    }

    public Optional<Ucerr> login(String email, String password){
       Optional<Ucerr> ucer=userRepository.findByEmailAndPassword(email,password);
       return ucer;
    }
}

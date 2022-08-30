package com.example.registerr.service;

import com.example.registerr.entity.Ucerr;
import com.example.registerr.reposiyory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailChecking {
    @Autowired
    private UserRepository userRepository;

    public Optional<Ucerr> checkEmail(Ucerr ucerr){
      Optional<Ucerr> user1 =userRepository.findByEmail(ucerr.getEmail());
    return user1;
    }
}

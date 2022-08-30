package com.example.registerr.controller;

import com.example.registerr.entity.Ucerr;
import com.example.registerr.service.EmailChecking;
import com.example.registerr.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @Autowired
    private EmailChecking emailChecking;

    @GetMapping("/register")
    public String registerationPage(Model model){
        model.addAttribute("ucerr", new Ucerr());
        return "registration";
    }
    @PostMapping("/register")
    public String register(Ucerr ucerr){
       Optional<Ucerr> user1= emailChecking.checkEmail(ucerr);
    if(user1.isPresent()){
        return "redirect:register?eyni";
    }
        userService.save(ucerr);
        return "redirect:register?succes";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String loginToWebsite(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest httpServletRequest){
    Optional<Ucerr> user=userService.login(email, password);
    if(!user.isPresent()){
        model.addAttribute("error", "Istifadeci maili yaxud sifre yalnisdir!");
        return "login";
    }
    httpServletRequest.getSession().setAttribute("istifadeci", user.get());
    return "welcome";
    }
    @GetMapping("/welcome")
    public String welcome(HttpServletRequest httpServletRequest){
        Ucerr ucerr=(Ucerr) httpServletRequest.getSession().getAttribute("istifadeci");
        if(ucerr==null){
            return "login";
        }
        return "welcome";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest,Model model){
        httpServletRequest.getSession().invalidate();
        model.addAttribute("logout","Hesabdan cixis edildi");
        return "login";
    }

}

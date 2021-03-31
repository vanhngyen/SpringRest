package com.example.SpringRest.web;


import com.example.SpringRest.model.User;
import com.example.SpringRest.service.SecurityService;
import com.example.SpringRest.service.UserSevice;
import com.example.SpringRest.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController  {
    @Autowired
    private UserSevice userSevice;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){
        userValidator.validate(userForm,bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";

        }
        userSevice.save(userForm);
        securityService.autoLogin(userForm.getUsername(),userForm.getPasswordConfirm());
        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model , String error,String logout){
        if(error != null)
            model.addAttribute("error","You username and password is invalid");

        if(logout != null)
            model.addAttribute("message","you have been logged out successfully");

        return "login";
    }

    @GetMapping({"/","/welcome"})
    public String welcome(Model model){
        return "welcome";
    }

}

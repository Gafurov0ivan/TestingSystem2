package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itpark.dao.UserDao;
import ru.itpark.model.User;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserDao userDaoImpl;

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String showAddUserForm(Model model){
        User user = new User();
        user.setUserName("Ivan");
        user.setPassword("Ivan");
        user.setEmail("Ivan");
        model.addAttribute("login", user);
        return "login";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String saveOrUpdateUser(@ModelAttribute("login") User user, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "users/login";
        }
        userDaoImpl.save(user.getUserName(), user.getPassword());
        return "login";
    }

}

//    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
//    public String newUser(ModelMap model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        model.addAttribute("edit", false);
//        return "login";
//    }
//
//    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
//    public String saveUser(User user, BindingResult result, ModelMap model) {
////        if (result.hasErrors()) {
////            return "registration";
////        }
////        if (userDaoImpl.findByUserName(user.getUserName())!=null)
////        {
////            FieldError ssoError = new FieldError("user", user.getUserName(), "This userName is already occupied");
////            result.addError(ssoError);
////            return "registration";
////        }
//        userDaoImpl.save(user.getUserName(), user.getPassword());
////        model.addAttribute("success", "User " + user.getUserName() + " registered successfully");
//        return "userTests";

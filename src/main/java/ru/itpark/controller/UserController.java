package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.dao.UserDao;
import ru.itpark.model.User;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserDao userDaoImpl;

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String showAddUserForm(Model model){
//        User user = new User();
//        user.setUserName("Ivan");
//        user.setPassword("Ivan");
//        user.setEmail("Ivan");
//        model.addAttribute("login", user);
//        return "login";
//    }
//
//    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    public String saveOrUpdateUser(@ModelAttribute("login") User user, BindingResult result, Model model){
//        if (result.hasErrors()) {
//            return "users/login";
//        }
//        userDaoImpl.save(user.getUserName(), user.getPassword());
//        return "login";
//    }
//
//}

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("login", "command", new User());
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("command")User user,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "errorpage";
        }
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("email", user.getEmail());
        userDaoImpl.save(user.getUserName(), user.getPassword());
        return "userTests";
    }

//        if (result.hasErrors()) {
//            return "registration";
//        }
//        if (userDaoImpl.findByUserName(user.getUserName())!=null)
//        {
//            FieldError ssoError = new FieldError("user", user.getUserName(), "This userName is already occupied");
//            result.addError(ssoError);
//            return "registration";
//        }
//        userDaoImpl.save(user.getUserName(), user.getPassword());
////        model.addAttribute("success", "User " + user.getUserName() + " registered successfully");
//        return "userTests";
//    }
}

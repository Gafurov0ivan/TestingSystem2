package ru.itpark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itpark.entity.User;

import java.io.IOException;

/**
 * Created by maratgumarov on 08.11.16.
 */

@Controller
public class CreateTestController {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User(1L,"Marat");
        String s = "";
        try {
            s = mapper.writeValueAsString(user);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            User deserealizedUser = mapper.readValue(s,User.class);
            System.out.println(deserealizedUser.getId() + deserealizedUser.getUserName() + deserealizedUser.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

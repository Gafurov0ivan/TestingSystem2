package ru.itpark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import ru.itpark.model.User;

import java.io.IOException;

/**
 * Created by maratgumarov on 08.11.16.
 */

@Controller
public class CreateTestController {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
// after add User to system
// User user = new User(1L, "Marat");
        User user = new User();
        String s = "";
        try {
            s = mapper.writeValueAsString(user);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            User deserealizedUser = mapper.readValue(s,User.class);
            System.out.println(deserealizedUser.getId() + deserealizedUser.getUsername() + deserealizedUser.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

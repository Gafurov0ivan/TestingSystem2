package ru.itpark.service;

public interface AuthenticationService {

    String findLoggedInUsername();
    void autologin(String username, String password);
}

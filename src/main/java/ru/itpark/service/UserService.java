package ru.itpark.service;

import ru.itpark.model.User;

public interface UserService {

  void save(User user);
  User findByUsername(String username);
}

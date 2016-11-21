package ru.itpark.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.model.User;

public interface UserDao extends JpaRepository<User, Long> {

  User findByUsername(String username);
}

package ru.itpark.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
}

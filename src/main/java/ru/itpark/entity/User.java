package ru.itpark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */
@Entity
@Table(name = "sysUser")
public class User extends BaseEntity {

  @Column(name = "username")
  private String userName;

  @Column(name = "password")
  private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User() {
  }

  public User(Long id, String userName) {
    setId(id);

    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}

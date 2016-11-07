package ru.itpark.entity;

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
public class User implements Serializable {

  private Long id;
  private String userName;

  public User() {
  }

  public User(Long id, String userName) {
    this.id = id;
    this.userName = userName;
  }

  @Id
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}

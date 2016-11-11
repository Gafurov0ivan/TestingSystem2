package ru.itpark.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */
@Entity
@Table(name = "sysUser")
public class User extends BaseEntity {

  private String userName;
  private String password;
  private String fullName;
  private Collection<UserTest> completedTests;
  private Collection<Test> createdTests;

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

  public User(String userName, String password) {
    this.password = password;
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  public Collection<UserTest> getCompletedTests() {
    return completedTests;
  }

  public void setCompletedTests(Collection<UserTest> completedTests) {
    this.completedTests = completedTests;
  }

  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  public Collection<Test> getCreatedTests() {
    return createdTests;
  }

  public void setCreatedTests(Collection<Test> createdTests) {
    this.createdTests = createdTests;
  }


}

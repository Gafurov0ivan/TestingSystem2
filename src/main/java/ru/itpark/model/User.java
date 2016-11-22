package ru.itpark.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "person")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String username;
  private String password;
  private String email;
  @Transient
  private String passwordConfirm;
  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Collection<UserTest> completedTests;
  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  private Collection<Test> createdTests;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {return username;}

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {return password;}

  public void setPassword(String password) {this.password = password;}

  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {this.roles = roles;}

  public Collection<UserTest> getCompletedTests() {
    return completedTests;
  }

  public void setCompletedTests(Collection<UserTest> completedTests) {
    this.completedTests = completedTests;
  }


  public Collection<Test> getCreatedTests() {
    return createdTests;
  }

  public void setCreatedTests(Collection<Test> createdTests) {
    this.createdTests = createdTests;
  }
}

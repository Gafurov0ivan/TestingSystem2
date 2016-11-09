package ru.itpark.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

  private Long id;

  @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}

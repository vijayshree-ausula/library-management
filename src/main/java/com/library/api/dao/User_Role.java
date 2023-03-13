package com.library.api.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity 
public class User_Role {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  
  private Integer role_id;
  
  private Integer user_id;
  
  @OneToOne
  @JoinColumn(name = "id", referencedColumnName = "user_id")
  User user;
  
  @OneToOne
  @JoinColumn(name = "id", referencedColumnName = "role_id")
  Role role;
  

  public Integer getId() {
	  return id;
  }

  public void setId(Integer id) {
	  this.id = id;
  }

  public Integer getRole_id() {
	  return role_id;
  }

  public void setRoleId(Integer role_id) {
	  this.role_id = role_id;
  }

  public Integer getUser_id() {
	  return user_id;
  }

  public void setUser_id(Integer user_id) {
	  this.user_id = user_id;
  }

}

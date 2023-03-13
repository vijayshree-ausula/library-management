package com.library.api.dao;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "role")
public class Role {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  private String name;
  
//  @OneToOne 
//  @JoinColumn(name = "id", referencedColumnName = "role_id")
//  private User_Role user_role;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  
  public String getName() {
	return name;
  }
  
  public void setName(String name) {
	this.name = name;
  }

}

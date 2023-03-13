package com.library.api.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "user")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  private String uname;

  private String password;
  
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
          )
  private Set<Role> roles;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  
  public String getUname() {
	return uname;
  }	 

  public void setUname(String uname) {
	  this.uname = uname;
  }

  public String getPassword() {
	  return password;
  }

  public void setPassword(String password) {
	  this.password = password;
  }

  public Set<Role> getRoles() {
	return roles;
  }

  public void setRoles(Set<Role> roles) {
	  this.roles = roles;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      Set<Role> roles = getRoles();
      List<SimpleGrantedAuthority> authorities = new ArrayList<>();
       
      for (Role role : roles) {
          authorities.add(new SimpleGrantedAuthority(role.getName()));
      }
       
      return authorities;
  }
  
  @Override
  public boolean isAccountNonExpired() { return true; }
  
  @Override
  public boolean isAccountNonLocked() { return true; }
  
  @Override
  public boolean isCredentialsNonExpired() { return true; }
  
  @Override
  public boolean isEnabled() { return true; }

@Override
public String getUsername() {
	return getUname();
}

 
}

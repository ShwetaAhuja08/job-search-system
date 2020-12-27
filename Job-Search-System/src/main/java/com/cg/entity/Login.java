package com.cg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Login")
public class Login {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="login_id",nullable=false)
private Integer id;
@Column(name="username",nullable=false)
private String username;
@Column(name="password",nullable =false)
private String password;
@Column(name="role",nullable =false)
private String role;
// @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="login")
// private Employer employer;
public Login()
{
}
public String getUsername() {
return username;
}
public void setUsername(String username) {
this.username = username;
}
public String getPassword() {
return password;
}
public void setPassword(String password) {
this.password = password;
}
public String getRole() {
return role;
}
public void setRole(String role) {
this.role = role;
}

@Override
public String toString() {
return "Login [username=" + username + ", password=" + password + ", role=" + role + "]";
}
public Integer getId() {
return id;
}
public void setId(Integer id) {
this.id = id;
}
}
package com.hoang.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author hoangdd
 * @created 01/08/2020 - 6:21 AM
 **/
@Data
@Table(name = "USER")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String latName;
	
	@Column(name = "EMAIL")
	private String email;
}

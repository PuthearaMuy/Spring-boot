package com.springboot.theara.security;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String authentication;
}

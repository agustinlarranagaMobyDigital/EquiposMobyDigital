package com.equiposmoby.equiposmoby.Models;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    private String email;
    private String password;

}

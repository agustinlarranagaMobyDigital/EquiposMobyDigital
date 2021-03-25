package com.equiposmoby.equiposmoby.Models.Entity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "usuarios")
public class User implements Serializable {

    @Id
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public User(String email , String password){
        this.email = email;
        this.password = password;
    }

    public User() {

    }
}

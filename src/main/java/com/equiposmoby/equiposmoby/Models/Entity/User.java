package com.equiposmoby.equiposmoby.Models.Entity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
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

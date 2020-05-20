package org.example.RegistrationAndAuthentification.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table( name = "user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "login")
    })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @NotBlank
    @Size(max = 50)
    private String login;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotBlank
    @Size(max = 100)
    private String password;

    public User() {
    }

    public User(Long id_user, String login, String lastname,
                String firstname, String password) {
        this.id_user = id_user;
        this.login = login;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
    }

    public Long getId_user() {
        return id_user;
    }

    public String getLogin() {
        return login;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }
}

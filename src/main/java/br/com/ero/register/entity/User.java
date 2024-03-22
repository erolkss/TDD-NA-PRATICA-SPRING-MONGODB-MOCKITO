package br.com.ero.register.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User {

    public User(String name, String password, LocalDate dataOfBirth) {
        this.name = name;
        this.password = password;
        this.dataOfBirth = dataOfBirth;
    }

    private String id;
    private String name;
    private String password;
    private LocalDate dataOfBirth;
}

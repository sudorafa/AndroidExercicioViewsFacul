package com.example.orafa.androidexercicioviewsfacul.model;

import java.io.Serializable;

/**
 * Created by oRafa on 12/10/2017.
 */

public class User implements Serializable {

    private String name;
    private String email;
    private String nick;
    private String password;
    private Integer  idUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer  getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}

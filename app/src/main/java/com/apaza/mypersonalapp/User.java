package com.apaza.mypersonalapp;

import com.orm.dsl.Table;

/**
 * Created by Aldair on 11/10/2017.
 */
@Table
public class User {
    private Long id;
    private String ususario;
    private String password;
    private String nombres;

    public User() {
    }

    public User(String ususario, String password, String nombres) {
        this.ususario = ususario;
        this.password = password;
        this.nombres = nombres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsusario() {
        return ususario;
    }

    public void setUsusario(String ususario) {
        this.ususario = ususario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }


    @Override
    public String toString() {
        return "User{" +
                "ususario='" + ususario + '\'' +
                ", password='" + password + '\'' +
                ", nombres='" + nombres + '\'' +
                '}';
    }
}

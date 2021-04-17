package com.adb.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int userID;
    private String name;
    private String date;
    private Map<String, String> domicilio;
    private List<User> listadoDeClientes;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, String> getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Map<String, String> domicilio) {
        this.domicilio = domicilio;
    }

    public List<User> getListadoDeClientes() {
        return listadoDeClientes;
    }

    public void setListadoDeClientes(List<User> listadoDeClientes) {
        this.listadoDeClientes = listadoDeClientes;
    }
}

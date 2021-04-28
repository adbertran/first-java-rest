package com.adb.dtos;

import com.adb.domain.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJson {
    private int userID;
    private String name;
    private String date;
    private Map<String, String> domicilio;
    private List<UserJson> listadoDeClientes;

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

    public List<UserJson> getListadoDeClientes() {
        return listadoDeClientes;
    }

    public void setListadoDeClientes(List<UserJson> listadoDeClientes) {
        this.listadoDeClientes = listadoDeClientes;
    }


    public static UserJson createFrom(Users users) {
        UserJson userJson = new UserJson();
        userJson.userID = users.getUserID();
        userJson.name = users.getFirstName() + " " + users.getLastName();
        userJson.date = new SimpleDateFormat("yyyy-MM-dd").format(users.getDateCreated());

        return userJson;

    }
    public static UserJson createFrom(Users users, List<UserJson> listadoDeClientes) {
        UserJson userJson = UserJson.createFrom(users);
        userJson.listadoDeClientes = listadoDeClientes;

        return userJson;

    }

    public static List<UserJson> createFrom(List<Users> listadoDeClientes) {
        List<UserJson> list = new ArrayList<>();

        for(Users userDb: listadoDeClientes) {
            list.add(createFrom(userDb));
        }

        return list;

    }

}

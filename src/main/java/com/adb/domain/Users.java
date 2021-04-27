package com.adb.domain;

import com.adb.dtos.UserJson;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {
    private Integer userID;
    private String lastName;
    private String firstName;
    private transient Timestamp dateCreated;
    private transient Timestamp lastUpdated;

    @Id
    @Column(name = "user_id", nullable = false)
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "date_created", nullable = false, updatable = false)
    public Timestamp getDateCreated() {
        return dateCreated;

    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;

    }

    @Basic
    @Column(name = "last_updated", nullable = false)
    public Timestamp getLastUpdated() {
        return lastUpdated;

    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;

    }

    @PrePersist
    protected void onCreate() {
        this.lastUpdated = this.dateCreated = new Timestamp(new Date().getTime());

    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = new Timestamp(new Date().getTime());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Users orders = (Users) o;

        return Objects.equals(userID, orders.userID)
                && Objects.equals(lastName, orders.lastName)
                && Objects.equals(firstName, orders.firstName)
                && Objects.equals(dateCreated, orders.dateCreated)
                && Objects.equals(lastUpdated, orders.lastUpdated);

    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);

    }

    public static Users createFrom(UserJson userJson) {
        Users userDb = new Users();
        userDb.userID = userJson.getUserID();
        userDb.lastName = userJson.getName();
        userDb.firstName = userJson.getName();
        //The fields dateCreated and lastUpdated are managed by Hibernate via annotations.

        return userDb;

    }

}
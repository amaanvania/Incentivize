package com.incentive.managementsystem.Client;

import javax.persistence.*;
import java.util.Objects;


//class to represent one of our clients - Example: YuRide
@Entity
@Table(name = "CLIENTS")
public class Client {


    private @Id @GeneratedValue int id;

    @Column(unique=true)
    private String authCode;

    private String userName;


    private String password;

    public Client(){

    };

    public Client(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    //use this initially
    public Client(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(userName, client.userName) &&
                Objects.equals(password, client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password);
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}

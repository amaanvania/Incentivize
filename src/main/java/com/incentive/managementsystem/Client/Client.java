package com.incentive.managementsystem.Client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "CLIENTS")
public class Client {

    private @Id @GeneratedValue int id;

    private String userName;

    private String password;

    private String authCode;

    public Client(int id, String userName, String password, String authCode) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authCode = authCode;
    }

    public Client(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.authCode = "";
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

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(userName, client.userName) &&
                Objects.equals(password, client.password) &&
                Objects.equals(authCode, client.authCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, authCode);
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
}

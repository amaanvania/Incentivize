package com.ims.beans;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//class to represent a project
@Entity
@Table(name = "PROJECTS")
public class Project {

    //unique id of the project
    @Id
    @GeneratedValue
    int id;


    //which client does this project belong to?
    int clientId;


    //name of the project
    String name;

    public Project(){

    }
    public Project(int id, int clientId, String name) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.ims.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


//class to represent an incentive
@Entity
@Table(name = "INCENTIVES")
public class Incentive {

    private @Id
    @GeneratedValue
    int id;

    //which project does this incentive belong to?
    int projectID;


    private String name;

    private String category;

    public Incentive(){

    }
    public Incentive(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Incentive(String name, String category, int project_id) {
        this.name = name;
        this.category = category;
        this.projectID = project_id;
    }




    public int getProject_id() {
        return projectID;
    }

    public void setProject_id(int project_id) {
        this.projectID = project_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

package com.incentive.managementsystem.Condition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


//class to represent a condition
@Entity
@Table(name = "CONDITIONS")
public class Condition {


    private @Id
    @GeneratedValue
    int id;


    private int incentive_id;

    private String name;

    private String category;

    public Condition(){

    }
    public Condition(int id, int incentive_id, String name, String category) {
        this.id = id;
        this.incentive_id = incentive_id;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIncentive_id() {
        return incentive_id;
    }

    public void setIncentive_id(int incentive_id) {
        this.incentive_id = incentive_id;
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

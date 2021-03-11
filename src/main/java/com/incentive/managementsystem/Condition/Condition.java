package com.incentive.managementsystem.Condition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CONDITIONS")
public class Condition {


    private @Id
    @GeneratedValue
    int id;


    private int incentive_ID;

    private String name;

    private String category;


    public Condition(int incentive_ID, String name, String category) {
        this.incentive_ID = incentive_ID;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIncentive_ID() {
        return incentive_ID;
    }

    public void setIncentive_ID(int incentive_ID) {
        this.incentive_ID = incentive_ID;
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

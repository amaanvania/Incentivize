package com.incentive.managementsystem.Incentive;

import com.incentive.managementsystem.Condition.Condition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INCENTIVES")
public class Incentive {

    private @Id
    @GeneratedValue
    int id;

    private String name;

    private String category;

    public Incentive(){

    }
    public Incentive(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Incentive(String name, String category) {
        this.name = name;
        this.category = category;
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

package com.incentive.managementsystem.Threshold;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THRESHOLDS")
public class Threshold {

    private @Id
    @GeneratedValue
    int id;


    private int condition_id;

    private String value;

    private String parameterName;

    public Threshold(){

    }


    public Threshold(int id, int condition_id, String value, String parameterName) {
        this.id = id;
        this.condition_id = condition_id;
        this.value = value;
        this.parameterName = parameterName;
    }



    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCondition_id() {
        return condition_id;
    }

    public void setCondition_id(int condition_id) {
        this.condition_id = condition_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public boolean isBoolean(){
       boolean returnValue = false;

       if(getValue().equals("true") || getValue().equals("false")) returnValue = true;

       return returnValue;
    }

    public boolean isInteger(){
        try{
            int temp = Integer.parseInt(getValue());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

package com.incentive.managementsystem;

public class TestClass {

    public static void main(String[] args){

        String temp = "testtesttest";

        boolean b;
        try{
            boolean tem = Boolean.getBoolean(temp);
            b = true;
        }catch (Exception e){
            b = false;
        }

        System.out.println(b);
    }
}

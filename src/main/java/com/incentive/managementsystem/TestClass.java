package com.incentive.managementsystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class TestClass {

    public static void main(String[] args){


        String uniqueId = UUID.randomUUID().toString().replace("-", "");

        System.out.println(uniqueId);

    }
}

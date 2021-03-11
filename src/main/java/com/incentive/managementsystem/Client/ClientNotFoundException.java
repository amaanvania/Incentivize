package com.incentive.managementsystem.Client;

public class ClientNotFoundException extends RuntimeException{


    ClientNotFoundException(int id) {
        super("Could not find Client " + id);
    }
}

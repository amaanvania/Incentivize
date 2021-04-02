package com.incentive.managementsystem.Client;

public class ClientExistsException extends RuntimeException {

    ClientExistsException(String userName) {
        super("Username already exists: " + userName);
    }
}

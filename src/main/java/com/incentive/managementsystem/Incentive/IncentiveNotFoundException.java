package com.incentive.managementsystem.Incentive;

public class IncentiveNotFoundException extends RuntimeException {

    IncentiveNotFoundException(int id) {
        super("Could not find Incentive " + id);
    }
}

package com.incentive.managementsystem.Condition;

public class ConditionNotFoundException extends RuntimeException {

    ConditionNotFoundException(int id) {
        super("Could not find Condition " + id);
    }
}

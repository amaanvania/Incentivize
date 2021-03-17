package com.incentive.managementsystem.Threshold;

public class ThresholdNotFoundException extends RuntimeException {

    ThresholdNotFoundException(int id) {
        super("Could not find Threshold " + id);
    }
}

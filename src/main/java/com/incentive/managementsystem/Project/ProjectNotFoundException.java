package com.incentive.managementsystem.Project;

public class ProjectNotFoundException extends RuntimeException{


    ProjectNotFoundException(int id) {
        super("Could not find Project " + id);
    }
}

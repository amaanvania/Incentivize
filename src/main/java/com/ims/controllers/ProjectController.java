package com.ims.controllers;

import com.ims.beans.Project;
import com.ims.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;


    ProjectController(ProjectRepository cr){
        this.projectRepository = cr;
    }


    /**
     * Request mapping to return
     * all existing projects
     */
    @GetMapping("/projects")
    List<Project> all() {

        return projectRepository.findAll();
    }


    /**
     * Request mapping to return
     * a single project
     */
    @GetMapping("/project/{id}")
    Project one(@PathVariable int id) {
        return projectRepository.getOne(id);
    }


    /**
     * Request mapping to insert
     * a new project
     */
    @PostMapping("/projects")
    void newProject(@RequestBody Project project) {
        Project newProject = projectRepository.save(project);
    }
}

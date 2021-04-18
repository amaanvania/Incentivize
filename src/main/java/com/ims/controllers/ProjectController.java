package com.ims.controllers;

import com.ims.beans.Project;
import com.ims.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;


    ProjectController(ProjectRepository cr){
        this.projectRepository = cr;
    }

    @GetMapping("/projects")
    List<Project> all() {

        return projectRepository.findAll();
    }

    @GetMapping("/project/{id}")
    Project one(@PathVariable int id) {
        return projectRepository.getOne(id);
    }

    @PostMapping("/projects")
    void newProject(@RequestBody Project project) {
        Project newProject = projectRepository.save(project);
    }
}

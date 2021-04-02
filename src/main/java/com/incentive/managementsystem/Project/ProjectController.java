package com.incentive.managementsystem.Project;

import com.incentive.managementsystem.Project.Project;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final ProjectModelAssembler assembler;

    ProjectController(ProjectRepository cr, ProjectModelAssembler assembler){
        this.projectRepository = cr;
        this.assembler = assembler;
    }

    @GetMapping("/projects")
    CollectionModel<EntityModel<Project>> all() {

        List<EntityModel<Project>> orders = projectRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(ProjectController.class).all()).withSelfRel());
    }

    @GetMapping("/project/{id}")
    EntityModel<Project> one(@PathVariable int id) {

        Project order = projectRepository.findById(id) //
                .orElseThrow(() -> new ProjectNotFoundException(id));

        return assembler.toModel(order);
    }

    @PostMapping("/projects")
    ResponseEntity<EntityModel<Project>> newProject(@RequestBody Project project) {


        Project newProject = projectRepository.save(project);

        return ResponseEntity //
                .created(linkTo(methodOn(ProjectController.class).one(newProject.getId())).toUri()) //
                .body(assembler.toModel(newProject));
    }
}

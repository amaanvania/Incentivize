package com.incentive.managementsystem.Project;

import com.incentive.managementsystem.Project.Project;
import com.incentive.managementsystem.Project.ProjectController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class ProjectModelAssembler implements RepresentationModelAssembler<Project, EntityModel<Project>> {


    @Override
    public EntityModel<Project> toModel(Project client) {
        return EntityModel.of(client, //
                linkTo(methodOn(ProjectController.class).
                        one(client.getId())).withSelfRel(),
                linkTo(methodOn(ProjectController.class).all()).
                        withRel("Projects"));
    }
}
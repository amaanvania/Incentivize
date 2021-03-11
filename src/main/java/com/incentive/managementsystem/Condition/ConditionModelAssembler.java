package com.incentive.managementsystem.Condition;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ConditionModelAssembler implements RepresentationModelAssembler<Condition, EntityModel<Condition>> {


    @Override
    public EntityModel<Condition> toModel(Condition condition) {
        return EntityModel.of(condition, //
                linkTo(methodOn(ConditionController.class).
                        one(condition.getId())).withSelfRel(),
                linkTo(methodOn(ConditionController.class).all()).
                        withRel("Conditions"));
    }
}
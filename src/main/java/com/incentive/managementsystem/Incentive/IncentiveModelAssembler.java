package com.incentive.managementsystem.Incentive;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.incentive.managementsystem.Incentive.IncentiveController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class IncentiveModelAssembler implements RepresentationModelAssembler<Incentive, EntityModel<Incentive>> {


    @Override
    public EntityModel<Incentive> toModel(Incentive incentive) {
        return EntityModel.of(incentive, //
                linkTo(methodOn(IncentiveController.class).
                        one(incentive.getId())).withSelfRel(),
                linkTo(methodOn(IncentiveController.class).all()).
                        withRel("Incentives"));
    }
}
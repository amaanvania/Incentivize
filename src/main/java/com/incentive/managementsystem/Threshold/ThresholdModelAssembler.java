package com.incentive.managementsystem.Threshold;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ThresholdModelAssembler implements RepresentationModelAssembler<Threshold, EntityModel<Threshold>> {


    @Override
    public EntityModel<Threshold> toModel(Threshold threshold) {
        return EntityModel.of(threshold, //
                linkTo(methodOn(ThresholdController.class).
                        one(threshold.getId())).withSelfRel(),
                linkTo(methodOn(ThresholdController.class).all()).
                        withRel("Thresholds"));
    }
}
package com.incentive.managementsystem.Threshold;

import com.incentive.managementsystem.Threshold.ThresholdModelAssembler;
import com.incentive.managementsystem.Threshold.ThresholdNotFoundException;
import com.incentive.managementsystem.Threshold.ThresholdRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ThresholdController {
    private final ThresholdRepository thresholdRepository;
    private final ThresholdModelAssembler assembler;

    ThresholdController(ThresholdRepository cr, ThresholdModelAssembler assembler){
        this.thresholdRepository = cr;
        this.assembler = assembler;
    }

    @GetMapping("/thresholds")
    CollectionModel<EntityModel<Threshold>> all() {

        List<EntityModel<Threshold>> orders = thresholdRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(ThresholdController.class).all()).withSelfRel());
    }

    @GetMapping("/threshold/{id}")
    EntityModel<Threshold> one(@PathVariable int id) {

        Threshold order = thresholdRepository.findById(id) //
                .orElseThrow(() -> new ThresholdNotFoundException(id));

        return assembler.toModel(order);
    }

    @PostMapping("/thresholds")
    ResponseEntity<EntityModel<Threshold>> newThreshold(@RequestBody Threshold threshold) {


        Threshold newThreshold = thresholdRepository.save(threshold);

        return ResponseEntity //
                .created(linkTo(methodOn(ThresholdController.class).one(newThreshold.getId())).toUri()) //
                .body(assembler.toModel(newThreshold));
    }
}

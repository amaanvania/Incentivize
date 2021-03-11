package com.incentive.managementsystem.Incentive;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class IncentiveController {
    private final IncentiveRepository incentiveRepository;
    private final IncentiveModelAssembler assembler;

    IncentiveController(IncentiveRepository cr, IncentiveModelAssembler assembler){
        this.incentiveRepository = cr;
        this.assembler = assembler;
    }

    @GetMapping("/incentives")
    CollectionModel<EntityModel<Incentive>> all() {

        List<EntityModel<Incentive>> orders = incentiveRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(IncentiveController.class).all()).withSelfRel());
    }

    @GetMapping("/incentive/{id}")
    EntityModel<Incentive> one(@PathVariable int id) {

        Incentive order = incentiveRepository.findById(id) //
                .orElseThrow(() -> new IncentiveNotFoundException(id));

        return assembler.toModel(order);
    }

    @PostMapping("/incentives")
    ResponseEntity<EntityModel<Incentive>> newIncentive(@RequestBody Incentive incentive) {


        Incentive newIncentive = incentiveRepository.save(incentive);

        return ResponseEntity //
                .created(linkTo(methodOn(IncentiveController.class).one(newIncentive.getId())).toUri()) //
                .body(assembler.toModel(newIncentive));
    }
}

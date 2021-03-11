package com.incentive.managementsystem.Condition;

import com.incentive.managementsystem.Condition.ConditionModelAssembler;
import com.incentive.managementsystem.Condition.ConditionNotFoundException;
import com.incentive.managementsystem.Condition.ConditionRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ConditionController {
    private final ConditionRepository conditionRepository;
    private final ConditionModelAssembler assembler;

    ConditionController(ConditionRepository cr, ConditionModelAssembler assembler){
        this.conditionRepository = cr;
        this.assembler = assembler;
    }

    @GetMapping("/conditions")
    CollectionModel<EntityModel<Condition>> all() {

        List<EntityModel<Condition>> orders = conditionRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(ConditionController.class).all()).withSelfRel());
    }

    @GetMapping("/condition/{id}")
    EntityModel<Condition> one(@PathVariable int id) {

        Condition order = conditionRepository.findById(id) //
                .orElseThrow(() -> new ConditionNotFoundException(id));

        return assembler.toModel(order);
    }

    @PostMapping("/conditions")
    ResponseEntity<EntityModel<Condition>> newCondition(@RequestBody Condition condition) {


        Condition newCondition = conditionRepository.save(condition);

        return ResponseEntity //
                .created(linkTo(methodOn(ConditionController.class).one(newCondition.getId())).toUri()) //
                .body(assembler.toModel(newCondition));
    }
}

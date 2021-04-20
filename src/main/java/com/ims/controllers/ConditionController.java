package com.ims.controllers;

import com.ims.beans.Condition;
import com.ims.repository.ConditionRepository;
import com.ims.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConditionController {

    private final ConditionRepository conditionRepository;

    @Autowired
    private ConditionService conditionModel;

    public ConditionController(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }


    /**
     * Request mapping to return
     * all conditions
     */
    @GetMapping("/conditions")
    List<Condition> all() {
        return conditionRepository.findAll();
    }


    /**
     * Request mapping to return
     * one condition
     */
    @GetMapping("/condition/{id}")
    Condition one(@PathVariable int id) {
        return conditionRepository.getOne(id);
    }


    /**
     * Request mapping to insert
     * a condition
     */
    @PostMapping("/conditions")
    void newCondition(@RequestBody Condition condition) {
        Condition newCondition = conditionRepository.save(condition);
    }

    /**
     * Request mapping to remove
     * a single condition
     */
    @DeleteMapping("/condition/{id}")
    int remove(@PathVariable int id) {
        return conditionModel.removeCondition(id);
    }
}

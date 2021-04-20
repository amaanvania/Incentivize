package com.ims.controllers;

import com.ims.beans.Threshold;
import com.ims.repository.ThresholdRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThresholdController {
    private final ThresholdRepository thresholdRepository;

    ThresholdController(ThresholdRepository cr){
        this.thresholdRepository = cr;
    }


    /**
     * Request mapping to return
     * all existing thresholds
     */
    @GetMapping("/thresholds")
    List<Threshold> all() {
        return thresholdRepository.findAll();
    }


    /**
     * Request mapping to return
     * all existing projects
     */
    @GetMapping("/threshold/{id}")
    Threshold one(@PathVariable int id) {

        return thresholdRepository.getOne(id);
    }

    /**
     * Request mapping to insert
     * a new threshold
     */
    @PostMapping("/thresholds")
    void newThreshold(@RequestBody Threshold threshold) {


        Threshold newThreshold = thresholdRepository.save(threshold);

    }


    /**
     * Request mapping to delete an
     * existing threshold
     */
    @DeleteMapping("/threshold/{id}")
    int remove(@PathVariable int id) {
        thresholdRepository.deleteById(id);

        if(thresholdRepository.existsById(id)) return -1;
        else return 200;

    }
}

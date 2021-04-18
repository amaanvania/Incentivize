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

    @GetMapping("/thresholds")
    List<Threshold> all() {
        return thresholdRepository.findAll();
    }

    @GetMapping("/threshold/{id}")
    Threshold one(@PathVariable int id) {

        return thresholdRepository.getOne(id);
    }

    @PostMapping("/thresholds")
    void newThreshold(@RequestBody Threshold threshold) {


        Threshold newThreshold = thresholdRepository.save(threshold);

    }

    @DeleteMapping("/threshold/{id}")
    int remove(@PathVariable int id) {
        thresholdRepository.deleteById(id);

        if(thresholdRepository.existsById(id)) return -1;
        else return 200;

    }
}

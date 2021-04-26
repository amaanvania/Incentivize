package com.ims.controllers;

import com.ims.beans.Incentive;
import com.ims.repository.IncentiveRepository;
import com.ims.service.APIService;
import com.ims.service.IncentiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;


@RestController
public class IncentiveController {
    private final IncentiveRepository incentiveRepository;

    @Autowired
    private IncentiveService service;

    @Autowired
    private APIService apiService;


    IncentiveController(IncentiveRepository cr){
        this.incentiveRepository = cr;
    }


    /**
     * Request mapping to return
     * all incentives
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/incentives")
    List<Incentive> all() {
        return incentiveRepository.findAll();

    }

    /**
     * Request mapping to return
     * all incentives
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/incentives/client/{id}")
    List<Incentive> byClientID(@PathVariable int id) {
        return incentiveRepository.getIncentivesByclientID(id);

    }


    /**
     * Request mapping to return
     * one incentive
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/incentive/{id}")
    Incentive one(@PathVariable int id) {
        return incentiveRepository.getOne(id);
    }


    /**
     * Request mapping to update an incentive
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/incentive/update")
    void updateIncentive(@RequestBody Incentive i){

        if(incentiveRepository.existsById(i.getId()))
            incentiveRepository.save(i);

    }


    /**
     * Request mapping which generates API
     * call for an incentive
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/incentive/generateAPI/{incentive_id}/{client_id}")
    Object generateAPI(@PathVariable int incentive_id, @PathVariable int client_id) throws Exception {
        try{
            boolean b = service.validateAuthCodeByClientID(incentive_id,client_id);
            if(!b) return "Incentive does not exist";
        }catch (Exception e) {
            return "Incentive does not belong to Client or Client does not exist";
        }

        String authCode = service.getAuthCodeByClientID(client_id);
        //return apiService.generateAPIRequestMap(authCode,incentive_id);
        return apiService.generateAPIRequestString(authCode,incentive_id);
    }


    /**
     * Request mapping to check if an
     * incentive is fulfilled
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/incentive-fulfilled")
    String incentiveFulfilled(@RequestBody Map<String,String> allRequestParams) {

        if(allRequestParams.containsKey("id")) {
            String tempId = allRequestParams.get("id");
            int id = Integer.parseInt(tempId);
            String result = service.incentiveFulfilled(id, allRequestParams);
            return result;
        } else return "Missing id";
    }


    /**
     * Request mapping to insert
     * a new incentive
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/incentives")
    void newIncentive(@RequestBody Incentive incentive) {
        Incentive newIncentive = incentiveRepository.save(incentive);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/incentive/{id}")
    int remove(@PathVariable int id) {
        return service.removeIncentive(id);

    }

}

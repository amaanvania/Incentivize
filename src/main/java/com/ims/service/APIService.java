package com.ims.service;

import com.ims.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIService {

    @Autowired
    ThresholdRepository thresholdRepository;


    /**
     * Method which generates a sample API request
     * for an incentive
     * Used to generate API requests for clients
     */
    public String generateAPIRequest(String auth, int incentiveID){
        StringBuilder result = new StringBuilder();

        List<String> thresholds = thresholdRepository.getThresholdsByIncentive(incentiveID);
        result.append("localhost:8080/incentive-fulfilled");
        result.append("\n{");
        result.append("\n");
        result.append("\tid:" + incentiveID);
        result.append("\n");
        result.append("\tkey:" + auth);
        result.append("\n");
        for(int i = 0; i < thresholds.size(); i++){
            result.append("\t" + thresholds.get(i) + ": ?,");
            if(i < thresholds.size() - 1) result.append("\n");
        }
        result.append("\n}");
        return result.toString();
    }
}

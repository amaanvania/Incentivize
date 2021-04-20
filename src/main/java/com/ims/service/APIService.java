package com.ims.service;

import com.ims.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class APIService {

    @Autowired
    ThresholdRepository thresholdRepository;


    /**
     * Method which generates a sample API request
     * for an incentive as a String
     * Used to generate API requests for clients
     *
     * @param auth the clients auth code
     * @param incentiveID the incentive id to be generated
     */
    public String generateAPIRequestString(String auth, int incentiveID){
        StringBuilder result = new StringBuilder();
        List<String> thresholds = thresholdRepository.getThresholdsByIncentive(incentiveID);
        result.append("GET localhost:8080/incentive-fulfilled");
        result.append("\n{");
        result.append("\n");
        result.append("\tid:" + incentiveID);
        result.append(",\n");
        result.append("\tkey:" + auth);
        result.append(",\n");
       for(int i = 0; i < thresholds.size(); i++){
            result.append("\t" + thresholds.get(i) + ": ?");
            if(i < thresholds.size() - 1)
                result.append(",\n");
        }
        result.append("\n}");
        return result.toString();
    }

    /**
     * Method which generates a sample API request
     * for an incentive as a Map
     * Used to generate API requests for clients
     *
     * @param auth the clients auth code
     * @param incentiveID the incentive id to be generated
     */
    public Map<String,String> generateAPIRequestMap(String auth, int incentiveID){

        Map<String,String> res = new LinkedHashMap<>();
        res.put("Request type","GET");
        res.put("Request URL","localhost:8080/incentive-fulfilled");

        StringBuilder result = new StringBuilder();

        List<String> thresholds = thresholdRepository.getThresholdsByIncentive(incentiveID);
//        result.append("GET localhost:8080/incentive-fulfilled");
        result.append("\n{");
//        result.append("\n");
        result.append("\tid:" + incentiveID);
        result.append(",\n");
        result.append("\tkey:" + auth);
        result.append(",\n");
        for(int i = 0; i < thresholds.size(); i++){
            result.append("\t" + thresholds.get(i) + ": ?");
            if(i < thresholds.size() - 1)
                result.append(",\n");
        }
        result.append("\n}");

        res.put("Request Body", result.toString());
        return res;
    }
}

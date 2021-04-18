package com.ims.service;

import com.ims.beans.Threshold;
import com.ims.repository.ConditionRepository;
import com.ims.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConditionService {

    @Autowired
    private ThresholdRepository thresholdRepository;

    @Autowired
    private ConditionRepository conditionRepository;

    public int removeCondition(int condition){
        List<Threshold> thresholds = thresholdRepository.findAll();

        for(Threshold temp : thresholds){
            if(temp.getCondition_id() == condition){
                thresholdRepository.delete(temp);
            }
        }

        conditionRepository.deleteById(condition);

        return 200;
    }
}

package com.incentive.managementsystem.Condition;

import com.incentive.managementsystem.Incentive.IncentiveRepository;
import com.incentive.managementsystem.Threshold.Threshold;
import com.incentive.managementsystem.Threshold.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConditionModel {

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

package com.incentive.managementsystem.Incentive;

import com.incentive.managementsystem.Condition.Condition;
import com.incentive.managementsystem.Condition.ConditionRepository;
import com.incentive.managementsystem.Threshold.Threshold;
import com.incentive.managementsystem.Threshold.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Configuration
public class IncentiveModel {

    @Autowired
    private IncentiveRepository incentiveRepository;

    @Autowired
    private ThresholdRepository thresholdRepository;

    @Autowired
    private ConditionRepository conditionRepository;

    public boolean isIncentiveFulfilled(int id, Map<String, String> params){
        if(!incentiveRepository.existsById(id)) return false;

        for(String temp : params.keySet()){
            System.out.println(temp + "\t" + params.get(temp));
        }
        List<Condition> tempConditionList = conditionRepository.findAll();
        List<Threshold> tempList = thresholdRepository.findAll();

        HashSet<Integer> conditions = new HashSet<>();
        for(Condition c : tempConditionList){
            if(c.getIncentive_id() == id){
                conditions.add(c.getId());
            }
        }

        for(Threshold temp : tempList){
            if(conditions.contains(temp.getCondition_id())){
                if(!params.containsKey(temp.getParameterName())) { //we dont have this parameter
                    return false;
                }
                //otherwise we have condition so continue


                if(temp.isBoolean()){ //handle boolean logic
                    boolean thresholdValue = Boolean.parseBoolean(temp.getValue());
                    try{
                       String paramValue = params.get(temp.getParameterName());
                       if(paramValue == null) return false;
                       boolean isBoolean = paramValue.equals("true") || paramValue.equals("false");
                       if(!isBoolean) return false;
                       boolean tempBoolean = Boolean.parseBoolean(paramValue);
                       if(thresholdValue != tempBoolean) return false;

                    }catch (Exception e) {
                        return false;
                    }

                }else if(temp.isInteger()){ //handle integer logic

                    int thresholdValue = Integer.parseInt(temp.getValue());
                    try{
                        String paramValue = params.get(temp.getParameterName());
                        int tempInt = Integer.parseInt(paramValue);
                        if(thresholdValue > tempInt) return false;
                    }catch (Exception e){
                        return false;
                    }

                }else
                    return false; //not an integer or boolean

            }
        }
        return true;
    }
}

package com.incentive.managementsystem.Incentive;

import com.incentive.managementsystem.Client.ClientRepository;
import com.incentive.managementsystem.Condition.Condition;
import com.incentive.managementsystem.Condition.ConditionModel;
import com.incentive.managementsystem.Condition.ConditionRepository;
import com.incentive.managementsystem.Threshold.Threshold;
import com.incentive.managementsystem.Threshold.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
public class IncentiveService {

    @Autowired
    private IncentiveRepository incentiveRepository;

    @Autowired
    private ThresholdRepository thresholdRepository;

    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private ConditionModel conditionModel;

    @Autowired
    private ClientRepository clientRepository;


    public int incentiveFulfilled(int incentive_id, Map<String, String> params){
        int failureCode = -1;

        if(params.containsKey("key")){
            String auth = params.get("key");

            //basic auth code checking
            //expand on this so relevant to user
            if(clientRepository.getClientByAuthCode(auth).size() == 0) return failureCode;
        }else
            return failureCode;

        List<Condition> conditions = conditionRepository.getConditionsByIncentive(incentive_id);
        for(Condition c : conditions){
            List<Threshold> thresholds = thresholdRepository.getThresholdsByCondition(c.getId());
            for(Threshold temp : thresholds){
                if(!params.containsKey(temp.getParameterName())) { //we dont have this parameter
                    return failureCode;
                }
                //otherwise we have condition so continue


                if(temp.isBoolean()){ //handle boolean logic
                    boolean thresholdValue = Boolean.parseBoolean(temp.getValue());
                    try{
                        String paramValue = params.get(temp.getParameterName());
                        if(paramValue == null) return failureCode;
                        boolean isBoolean = paramValue.equals("true") || paramValue.equals("false");
                        if(!isBoolean) return failureCode;
                        boolean tempBoolean = Boolean.parseBoolean(paramValue);
                        if(thresholdValue != tempBoolean) return failureCode;

                    }catch (Exception e) {
                        return failureCode;
                    }

                }else if(temp.isDouble()){ //handle integer logic

                    int thresholdValue = Integer.parseInt(temp.getValue());
                    try{
                        String paramValue = params.get(temp.getParameterName());
                        int tempInt = Integer.parseInt(paramValue);
                        if(thresholdValue > tempInt) return failureCode;
                    }catch (Exception e){
                        return failureCode;
                    }

                }else
                    return failureCode;
            }
        }
        return 200; //success code
    }

    public boolean isIncentiveFulfilled(int id, Map<String, String> params){

        //first check if incentive exists
        if(!incentiveRepository.existsById(id)) return false;
        if(params == null) return false;
        if(params.containsKey("key")){
            String auth = params.get("key");

            //basic auth code checking
            //expand on this so relevant to user
            if(clientRepository.getClientByAuthCode(auth).size() == 0) return false;
        }else
            return false;

        //generate all conditions and thresholds
        List<Condition> tempConditionList = conditionRepository.findAll();
        List<Threshold> tempList = thresholdRepository.findAll();

        //store the relevant conditions in a hashset
        HashSet<Integer> conditions = new HashSet<>();
        for(Condition c : tempConditionList){
            if(c.getIncentive_id() == id){
                conditions.add(c.getId());
            }
        }

        //validate every single threshold
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

                }else if(temp.isDouble()){ //handle integer logic

                    double thresholdValue = Double.parseDouble(temp.getValue());
                    try{
                        String paramValue = params.get(temp.getParameterName());
                        double tempDouble = Double.parseDouble(paramValue);
                        if(thresholdValue > tempDouble) return false;
                    }catch (Exception e){
                        return false;
                    }

                }else
                    return false; //not an integer or boolean

            }
        }
        return true;
    }

    public int removeIncentive(int id){
        if(!incentiveRepository.existsById(id)) return -1;
        List<Condition> conditions = conditionRepository.findAll();

        for(Condition temp : conditions){
            if(temp.getIncentive_id() == id){
                conditionModel.removeCondition(temp.getId());
            }
        }

        incentiveRepository.deleteById(id);

        return 200;

    }

    public int insertIncentive(Incentive incentive){
        return 200;
    }
}

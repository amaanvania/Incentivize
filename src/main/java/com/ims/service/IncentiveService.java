package com.ims.service;

import com.ims.beans.Client;
import com.ims.beans.Condition;
import com.ims.beans.Incentive;
import com.ims.beans.Threshold;
import com.ims.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class IncentiveService {

    @Autowired
    private IncentiveRepository incentiveRepository;

    @Autowired
    private ThresholdRepository thresholdRepository;

    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private ConditionService conditionModel;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public String getAuthCodeByClientID(int client_id) throws Exception {
        Client c = clientRepository.getOne(client_id);
        if(c == null || c.equals(null)) throw new Exception("");

        return c.getAuthCode();
    }

    public boolean validateAuthCodeByClientID(int incentive_id, int client_id){

        Client c = clientRepository.getOne(client_id);
        if(c == null || c.equals(null)) return false;


        List<Client> clients = clientRepository.getClientByAuthCode(c.getAuthCode());
        if(clients.size() == 0) return false;

        Client curr = clients.get(0);

        List<Integer> incentives = incentiveRepository.getIncentivesByclientID(curr.getId());
        return incentives.contains(incentive_id);

    }

    public boolean validateAuthCode(String auth, int incentive_id){

        List<Client> clients = clientRepository.getClientByAuthCode(auth);
        if(clients.size() == 0) return false;

        Client curr = clients.get(0);

        List<Integer> incentives = incentiveRepository.getIncentivesByclientID(curr.getId());
        return incentives.contains(incentive_id);

    }

    public String incentiveFulfilled(int incentive_id, Map<String, String> params){

        List<Incentive> incentives = incentiveRepository.getIncentivesByID(incentive_id);
        if(incentives.size() == 0) return "Incentive does not exist";


        if(params.containsKey("key")){
            String auth = params.get("key");

            if(!validateAuthCode(auth,incentive_id)) return "Invalid Auth Code";


        }else
            return "Missing auth key";

        List<Condition> conditions = conditionRepository.getConditionsByIncentive(incentive_id);
        for(Condition c : conditions){
            List<Threshold> thresholds = thresholdRepository.getThresholdsByCondition(c.getId());
            for(Threshold temp : thresholds){
                if(!params.containsKey(temp.getParameterName())) { //we dont have this parameter
                    return "Missing parameter: " + temp.getParameterName();
                }
                //otherwise we have condition so continue


                if(temp.isBoolean()){ //handle boolean logic
                    boolean thresholdValue = Boolean.parseBoolean(temp.getValue());
                    try{
                        String paramValue = params.get(temp.getParameterName());
                        if(paramValue == null) return "Missing parameter";
                        boolean isBoolean = paramValue.toLowerCase().equals("true")
                                || paramValue.toLowerCase().equals("false");

                        if(!isBoolean) return "Parameter is not boolean";
                        boolean tempBoolean = Boolean.parseBoolean(paramValue);
                        if(thresholdValue != tempBoolean) return "Boolean values are not the same: " + temp.getParameterName();

                    }catch (Exception e) {
                        return temp.getParameterName() + " is not a Boolean";
                    }

                }else if(temp.isDouble()){ //handle integer logic

                    double thresholdValue = Double.parseDouble(temp.getValue());
                    try{
                        String paramValue = params.get(temp.getParameterName());
                        double tempInt = Double.parseDouble(paramValue);
                        if(thresholdValue > tempInt) return "Threshold value too low for " + temp.getParameterName();
                    }catch (Exception e){
                        return temp.getParameterName() + " is not a Double";
                    }

                }else
                    return "Invalid object type for " + temp.getParameterName();
            }
        }
        return "Success";
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

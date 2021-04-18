package com.ims.service;

import com.ims.beans.Client;
import com.ims.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {


    @Autowired
    private ClientRepository clientRepository;


    public Client registerNewClient(Client client) throws Exception {

        if(clientRepository.getClientByUserName(client.getUserName()).size() != 0){
           throw new Exception("Client exists");
        }else {
            //encrypt password
            Client temp = new Client(client.getUserName(), client.getPassword());
            temp.setAuthCode(generateAuthKey());
            return temp;
        }
    }

    public String generateAuthKey(){
        String auth = UUID.randomUUID().toString().replace("-", "");

        while(clientRepository.getClientByAuthCode(auth).size() != 0){
            auth = UUID.randomUUID().toString().replace("-", "");
        }

        return auth;
    }


}

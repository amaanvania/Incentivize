package com.incentive.managementsystem.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;


    public Client registerNewClient(Client client) throws ClientExistsException {

        if(clientRepository.getClientByUserName(client.getUserName()).size() != 0){
           throw new ClientExistsException(client.getUserName());
        }else {
            //encrypt password
            Client temp = new Client(client.getUserName(), passwordEncoder.encode(client.getPassword()));
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

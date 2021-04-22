package com.ims.controllers;

import com.ims.beans.Client;
import com.ims.repository.ClientRepository;
import com.ims.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ClientController {

    private final ClientRepository clientRepository;


    @Autowired
    private ClientService clientService;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Request Mapping for all clients
     */
    @GetMapping("/clients")
    List<Client> all() {
       return clientRepository.findAll();
    }

    /**
     * Request Mapping for one client
     */
    @GetMapping("/client/{id}")
    Client one(@PathVariable int id) {
       return clientRepository.getOne(id);
    }


    /**
     * Request Mapping for to handle logging in
     */
    @GetMapping("/client/login/{user}/{pass}")
    int loginClient(@PathVariable String user, @PathVariable String pass){
        return clientService.loginService(user,pass);
    }

    /**
     * Request mapping for auth code of
     * one client
     */
    @GetMapping("/client/auth/{id}")
    String oneAuth(@PathVariable int id) {
        return clientRepository.getOne(id).getAuthCode();
    }

    /**
     * Request mapping to insert
     * a new client
     */
    @PostMapping("/clients")
    void newClient(@RequestBody Client client) throws Exception {
        Client newClient = clientService.registerNewClient(client);
        clientRepository.save(newClient);
    }
}

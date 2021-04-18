package com.ims.controllers;

import com.ims.beans.Client;
import com.ims.repository.ClientRepository;
import com.ims.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final ClientRepository clientRepository;


    @Autowired
    private ClientService clientService;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    List<Client> all() {
       return clientRepository.findAll();
    }

    @GetMapping("/client/{id}")
    Client one(@PathVariable int id) {
       return clientRepository.getOne(id);
    }

    @PostMapping("/clients")
    void newClient(@RequestBody Client client) throws Exception {
        Client newClient = clientService.registerNewClient(client);
        clientRepository.save(newClient);
    }
}

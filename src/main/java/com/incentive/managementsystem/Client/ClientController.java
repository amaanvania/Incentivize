package com.incentive.managementsystem.Client;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ClientController {
    private final ClientRepository clientRepository;
    private final ClientModelAssembler assembler;

    ClientController(ClientRepository cr, ClientModelAssembler assembler){
        this.clientRepository = cr;
        this.assembler = assembler;
    }

    @GetMapping("/clients")
    CollectionModel<EntityModel<Client>> all() {

        List<EntityModel<Client>> orders = clientRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(ClientController.class).all()).withSelfRel());
    }

    @GetMapping("/client/{id}")
    EntityModel<Client> one(@PathVariable int id) {

        Client order = clientRepository.findById(id) //
                .orElseThrow(() -> new ClientNotFoundException(id));

        return assembler.toModel(order);
    }

    @PostMapping("/clients")
    ResponseEntity<EntityModel<Client>> newClient(@RequestBody Client client) {


        Client newClient = clientRepository.save(client);

        return ResponseEntity //
                .created(linkTo(methodOn(ClientController.class).one(newClient.getId())).toUri()) //
                .body(assembler.toModel(newClient));
    }
}

package org.tb.bdd.cucumber.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tb.bdd.cucumber.api.bom.ClientBom;
import org.tb.bdd.cucumber.api.bom.ResponseEntity;
import org.tb.bdd.cucumber.api.model.Client;
import org.tb.bdd.cucumber.api.service.ClientService;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<List<ClientBom>>> list() {
        return service.getList();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Client>> get(@PathVariable Long id) {
        return service.get(id);
    }
}

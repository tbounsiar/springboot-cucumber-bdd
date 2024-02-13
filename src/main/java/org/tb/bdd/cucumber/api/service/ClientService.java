package org.tb.bdd.cucumber.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.tb.bdd.cucumber.api.bom.ClientBom;
import org.tb.bdd.cucumber.api.bom.ResponseEntity;
import org.tb.bdd.cucumber.api.model.Client;
import org.tb.bdd.cucumber.api.repository.ClientRepository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log
public class ClientService {

    private final ClientRepository repository;

    public Mono<ResponseEntity<List<ClientBom>>> getList() {
        var clients = repository.getList();
        var content = clients.stream().map(
                client -> new ClientBom(
                        client.getId(),
                        client.getFirstName(),
                        client.getLastName(),
                        client.getEmail()
                )).collect(Collectors.toList());
        log.warning("GET Content Size: {}" + content.size());
        return Mono.just(new ResponseEntity<>(content));
    }

    public Mono<ResponseEntity<Client>> get(Long id) {
        log.warning("GET Client ID: {}" + id);
        var content = repository.get(id);
        return Mono.just(new ResponseEntity<>(content));
    }
}

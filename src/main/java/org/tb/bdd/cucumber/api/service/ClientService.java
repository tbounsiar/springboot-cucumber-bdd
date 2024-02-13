package org.tb.bdd.cucumber.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tb.bdd.cucumber.api.bom.ClientBom;
import org.tb.bdd.cucumber.api.bom.ResponseEntity;
import org.tb.bdd.cucumber.api.model.Client;
import org.tb.bdd.cucumber.api.repository.ClientRepository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public Mono<ResponseEntity<List<ClientBom>>> getList() {
        List<Client> clients = repository.getList();
        List<ClientBom> content = clients.stream().map(
                client -> new ClientBom(
                        client.getId(),
                        client.getFirstName(),
                        client.getLastName(),
                        client.getEmail()
                )).collect(Collectors.toList());
        return Mono.just(new ResponseEntity<>(content));
    }

    public Mono<ResponseEntity<Client>> get(Long id) {
        return Mono.just(new ResponseEntity<>(repository.get(id)));
    }
}

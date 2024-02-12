package org.tb.bdd.cucumber.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tb.bdd.cucumber.api.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
}

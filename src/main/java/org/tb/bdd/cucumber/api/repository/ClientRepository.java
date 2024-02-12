package org.tb.bdd.cucumber.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tb.bdd.cucumber.api.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("FROM Client")
    List<Client> getList();

    @Query("SELECT c FROM Client c JOIN FETCH c.address WHERE c.id=:id")
    Client get(@Param("id") Long id);

    Client save(Client client);

    @Query("DELETE FROM Client WHERE id=:id")
    void delete(@Param("id") Long id);
}

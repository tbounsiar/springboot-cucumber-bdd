package org.tb.bdd.cucumber.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tb.bdd.cucumber.api.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User")
    List<User> getList();

    @Query("FROM User WHERE id=:id")
    User get(Long id);

    User save(User user);

    @Query("DELETE FROM User WHERE id=:id")
    void delete(Long id);
}

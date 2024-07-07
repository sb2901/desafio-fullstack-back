package com.test.fullstack.test.repositories;

import com.test.fullstack.test.entities.Costumer;
import com.test.fullstack.test.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumerRepository extends CrudRepository<Costumer, Integer> {

    //Optional<Costumer> findById(Integer id);
}

package com.test.fullstack.test.repositories;

import com.test.fullstack.test.entities.CostumerContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CostumerContactRepository extends CrudRepository<CostumerContact, Integer> {
}

package com.test.fullstack.test.dto;

import com.test.fullstack.test.entities.Costumer;
import com.test.fullstack.test.entities.enums.ContactType;
import jakarta.persistence.*;

public record CostumerContactDto (
       Integer id,
       Integer type,
        String value,
       Integer idCostumer)
{
}

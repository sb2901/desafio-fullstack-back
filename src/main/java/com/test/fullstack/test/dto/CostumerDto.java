package com.test.fullstack.test.dto;

import com.test.fullstack.test.entities.CostumerContact;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;

public record CostumerDto(
        Integer id,
        String name,
        Date createdAt,
        List<CostumerContactDto> contact

) {}


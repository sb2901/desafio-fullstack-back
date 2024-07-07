package com.test.fullstack.test.entities;

import com.test.fullstack.test.entities.enums.ContactType;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "costumer_contact")
@Entity
public class CostumerContact {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,name = "nr_id")
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false,name = "nr_type")
    private ContactType type;

    @Column(nullable = false,name = "ds_value")
    private String value;

    @ManyToOne
    @JoinColumn(name="nr_costumer", nullable=false)
    private Costumer costumer;
}

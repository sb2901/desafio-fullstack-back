package com.test.fullstack.test.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "costumer")
@Entity
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,name = "nr_id")
    private Integer id;

    @Column(nullable = false,name = "ds_name")
    private String name;

    @CreationTimestamp
    @Column(updatable = false, name = "dt_created")
    private Date createdAt;

    @OneToMany(mappedBy="costumer", cascade=CascadeType.ALL)
    private Set<CostumerContact> contact;

}

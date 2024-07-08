package com.test.fullstack.test.dto;

import lombok.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CostumerDto {

    private Integer id;
    private String name;
    private Date createdAt;
    private Set<CostumerContactDto> contact;

}


package org.ncbe.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Employee {

    private Long id;
    private String name;
    private Department department;
    private Boolean isFullTime;
}

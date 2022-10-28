package org.ncbe.entities;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Department {

    private String deptName;
    private BigDecimal pay;
    private Boolean hasBonus;
}

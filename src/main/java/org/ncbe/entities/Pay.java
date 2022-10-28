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
public class Pay {

    private Long id;
    private Employee employee;
    private BigDecimal hoursWorked;
}

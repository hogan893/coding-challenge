package org.ncbe.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BasePayListDTO {

    private String employeeName;
    private BigDecimal basePay;
}

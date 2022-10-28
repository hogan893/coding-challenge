package org.ncbe.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class WeeklyPayReportDTO {

    private Map<String, BigDecimal> totalPayByDepartmentMap = new HashMap<>();
    private List<BasePayListDTO> basePayListDTOList = new ArrayList<>();
}

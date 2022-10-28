package org.ncbe.service;

import org.ncbe.dto.BasePayListDTO;
import org.ncbe.dto.WeeklyPayReportDTO;
import org.ncbe.entities.Pay;

import java.math.BigDecimal;
import java.util.List;


public class PayService {

    private final BigDecimal OVERTIME_MARK = new BigDecimal("40");
    private final BigDecimal BONUS = new BigDecimal("50");
    private final BigDecimal OVERTIME_RATE = new BigDecimal("1.5");

    public WeeklyPayReportDTO getPayReport(List<Pay> payList) {

        WeeklyPayReportDTO response = new WeeklyPayReportDTO();

        payList.stream().forEach(p -> {
            String deptName = p.getEmployee().getDepartment().getDeptName();
            BigDecimal basePay = p.getEmployee().getDepartment().getPay();
            BigDecimal pay = getTotalPay(p);
            if(!response.getTotalPayByDepartmentMap().containsKey(deptName)) {
                response.getTotalPayByDepartmentMap().put(deptName, pay);
            } else {
                response.getTotalPayByDepartmentMap().put(
                        deptName, response.getTotalPayByDepartmentMap().get(deptName).add(pay));
            }

            //Not using map due to possibility of two employees having same name
            response.getBasePayListDTOList().add(BasePayListDTO.builder()
                    .employeeName(p.getEmployee().getName())
                    .basePay(basePay)
                    .build());
        });

        return response;
    }

    private BigDecimal getTotalPay(Pay p) {
        BigDecimal totalPay = BigDecimal.ZERO;
        BigDecimal payRate = p.getEmployee().getDepartment().getPay();
        BigDecimal hoursWorked = p.getHoursWorked();
        Boolean isOvertimeEligible = p.getEmployee().getIsFullTime();
        Boolean hasBonus = p.getEmployee().getDepartment().getHasBonus();

        /*TODO: per requirements, only full-time employees are eligible for overtime
        Confirm that this is correct, and part-time employees shouldn't get overtime,
        even if they work more than 40 hours
         */
        if(isOvertimeEligible && hoursWorked.compareTo(OVERTIME_MARK) > 0) {
            BigDecimal overtimeHours = hoursWorked.subtract(OVERTIME_MARK);
            hoursWorked = OVERTIME_MARK;
            totalPay = totalPay.add(overtimeHours.multiply(payRate.multiply(OVERTIME_RATE)));
        }

        totalPay = totalPay.add(payRate.multiply(hoursWorked));
        if(hasBonus) {
            totalPay = totalPay.add(BONUS);
        }
        return totalPay.setScale(2);
    }
}

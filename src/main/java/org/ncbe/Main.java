package org.ncbe;

import org.ncbe.dto.BasePayListDTO;
import org.ncbe.dto.WeeklyPayReportDTO;
import org.ncbe.entities.Employee;
import org.ncbe.entities.Pay;
import org.ncbe.enums.DepartmentEnum;
import org.ncbe.factories.DepartmentFactory;
import org.ncbe.service.PayService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        PayService payService = new PayService();
        print(payService.getPayReport(testList1));
    }

    private static void print(WeeklyPayReportDTO payReportDTO) {
        System.out.printf("%-20s %-10s %n", "Department", "Total Pay");
        System.out.println("-------------------------------");
        payReportDTO.getTotalPayByDepartmentMap().entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .forEach(entry -> {
                    System.out.printf("%-20s %,10.2f %n", entry.getKey(), entry.getValue().floatValue());
                });
        System.out.println("-------------------------------");
        System.out.println();

        System.out.printf("%-20s %-10s %n", "Employee", "Hourly Rate");
        System.out.println("---------------------------------");
        payReportDTO.getBasePayListDTOList().stream()
                .sorted(Comparator.comparing(BasePayListDTO::getEmployeeName))
                .forEach(e -> {
                    System.out.printf("%-20s %,10.2f %n", e.getEmployeeName(), e.getBasePay().floatValue());
                });
        System.out.println("---------------------------------");
    }

    private static final List<Pay> testList1 = Arrays.asList(
            Pay.builder()
                    .id(101L)
                    .employee(Employee.builder()
                            .id(1L)
                            .department(DepartmentFactory.getDepartment(DepartmentEnum.INTERN))
                            .isFullTime(true)
                            .name("John Smith")
                            .build())
                    .hoursWorked(new BigDecimal("40"))
                    .build(),
            Pay.builder()
                    .id(102L)
                    .employee(Employee.builder()
                            .id(2L)
                            .department(DepartmentFactory.getDepartment(DepartmentEnum.MANAGER))
                            .isFullTime(true)
                            .name("William Wilson")
                            .build())
                    .hoursWorked(new BigDecimal("41"))
                    .build(),
            Pay.builder()
                    .id(103L)
                    .employee(Employee.builder()
                            .id(3L)
                            .department(DepartmentFactory.getDepartment(DepartmentEnum.INTERN))
                            .isFullTime(false)
                            .name("Reanu Keeves")
                            .build())
                    .hoursWorked(new BigDecimal("41"))
                    .build(),
            Pay.builder()
                    .id(104L)
                    .employee(Employee.builder()
                            .id(4L)
                            .department(DepartmentFactory.getDepartment(DepartmentEnum.EXECUTIVE))
                            .isFullTime(false)
                            .name("Aaron A Aaronson")
                            .build())
                    .hoursWorked(new BigDecimal("40"))
                    .build()
    );
}

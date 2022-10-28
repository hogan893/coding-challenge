package org.ncbe.service;

import org.junit.jupiter.api.Test;
import org.ncbe.dto.WeeklyPayReportDTO;
import org.ncbe.entities.Employee;
import org.ncbe.entities.Pay;
import org.ncbe.enums.DepartmentEnum;
import org.ncbe.factories.DepartmentFactory;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayServiceTest {
    private PayService payService = new PayService();

    @Test
    public void getPayReportTest_payByDepartment_noOvertime_noBonus() {
        Pay pay = getPay("John Smith", DepartmentEnum.INTERN, false, "41");

        WeeklyPayReportDTO response = payService.getPayReport(Arrays.asList(pay));
        assertEquals(1, response.getTotalPayByDepartmentMap().size());
        assertEquals("410.00", response.getTotalPayByDepartmentMap().get("Intern").toString());
    }

    @Test
    public void getPayReportTest_payByDepartment_overtime_noBonus() {
        Pay pay = getPay("John Smith", DepartmentEnum.INTERN, true, "41");

        WeeklyPayReportDTO response = payService.getPayReport(Arrays.asList(pay));
        assertEquals(1, response.getTotalPayByDepartmentMap().size());
        assertEquals("415.00", response.getTotalPayByDepartmentMap().get("Intern").toString());
    }

    @Test
    public void getPayReportTest_payByDepartment_noOvertime_bonus() {
        Pay pay = getPay("John Smith", DepartmentEnum.EXECUTIVE, false, "41");

        WeeklyPayReportDTO response = payService.getPayReport(Arrays.asList(pay));
        assertEquals(1, response.getTotalPayByDepartmentMap().size());
        assertEquals("2100.00", response.getTotalPayByDepartmentMap().get("Executive").toString());
    }

    @Test
    public void getPayReportTest_payByDepartment_overtime_bonus() {
        Pay pay = getPay("John Smith", DepartmentEnum.EXECUTIVE, true, "41");

        WeeklyPayReportDTO response = payService.getPayReport(Arrays.asList(pay));
        assertEquals(1, response.getTotalPayByDepartmentMap().size());
        assertEquals("2125.00", response.getTotalPayByDepartmentMap().get("Executive").toString());
    }

    @Test
    public void getPayReportTest_payByDepartment_multiple() {
        Pay pay1 = getPay("John Smith", DepartmentEnum.INTERN, false, "10");
        Pay pay2 = getPay("Jane Smith", DepartmentEnum.EXECUTIVE, true, "11");
        Pay pay3 = getPay("Joe Smith", DepartmentEnum.SUPERVISOR, false, "12");
        Pay pay4 = getPay("Josh Smith", DepartmentEnum.INTERN, true, "13");
        Pay pay5 = getPay("Jennifer Smith", DepartmentEnum.FLOOR_WORKER, false, "14");
        Pay pay6 = getPay("Jeff Smith", DepartmentEnum.EXECUTIVE, true, "15");
        Pay pay7 = getPay("Jacob Smith", DepartmentEnum.INTERN, false, "16");
        Pay pay8 = getPay("Jo Smith", DepartmentEnum.FLOOR_WORKER, true, "17");
        Pay pay9 = getPay("William Wilson", DepartmentEnum.MANAGER, false, "18");
        Pay pay10 = getPay("Marcus O'Reilly", DepartmentEnum.MANAGER, true, "19");
        Pay pay11 = getPay("Ames Walters", DepartmentEnum.FLOOR_WORKER, false, "20");
        Pay pay12 = getPay("Brian Mason", DepartmentEnum.FLOOR_WORKER, true, "21");
        Pay pay13 = getPay("Robert Plant", DepartmentEnum.SUPERVISOR, false, "22");

        WeeklyPayReportDTO response = payService.getPayReport(Arrays.asList(pay1, pay2, pay3, pay4, pay5, pay6, pay7,
                pay8, pay9, pay10, pay11, pay12, pay13));
        assertEquals(5, response.getTotalPayByDepartmentMap().size());
        assertEquals("390.00", response.getTotalPayByDepartmentMap().get("Intern").toString());
        assertEquals("1440.00", response.getTotalPayByDepartmentMap().get("Floor Worker").toString());
        assertEquals("850.00", response.getTotalPayByDepartmentMap().get("Supervisor").toString());
        assertEquals("1395.00", response.getTotalPayByDepartmentMap().get("Manager").toString());
        assertEquals("1400.00", response.getTotalPayByDepartmentMap().get("Executive").toString());
    }

    @Test
    public void getPayReportTest_basePay_multiple() {
        Pay pay1 = getPay("John Smith", DepartmentEnum.INTERN, false, "10");
        Pay pay2 = getPay("Jane Smith", DepartmentEnum.EXECUTIVE, true, "11");
        Pay pay3 = getPay("Joe Smith", DepartmentEnum.SUPERVISOR, false, "12");
        Pay pay4 = getPay("Josh Smith", DepartmentEnum.MANAGER, true, "13");
        Pay pay5 = getPay("Jennifer Smith", DepartmentEnum.FLOOR_WORKER, false, "14");

        WeeklyPayReportDTO response = payService.getPayReport(Arrays.asList(pay1, pay2, pay3, pay4, pay5));
        assertEquals(5, response.getBasePayListDTOList().size());
        assertEquals("John Smith", response.getBasePayListDTOList().get(0).getEmployeeName());
        assertEquals("10", response.getBasePayListDTOList().get(0).getBasePay().toString());
        assertEquals("Jane Smith", response.getBasePayListDTOList().get(1).getEmployeeName());
        assertEquals("50", response.getBasePayListDTOList().get(1).getBasePay().toString());
        assertEquals("Joe Smith", response.getBasePayListDTOList().get(2).getEmployeeName());
        assertEquals("25", response.getBasePayListDTOList().get(2).getBasePay().toString());
        assertEquals("Josh Smith", response.getBasePayListDTOList().get(3).getEmployeeName());
        assertEquals("35", response.getBasePayListDTOList().get(3).getBasePay().toString());
        assertEquals("Jennifer Smith", response.getBasePayListDTOList().get(4).getEmployeeName());
        assertEquals("20", response.getBasePayListDTOList().get(4).getBasePay().toString());
    }

    private Pay getPay(String customerName, DepartmentEnum departmentEnum, boolean isFullTime, String hoursWorked) {
        return Pay.builder()
                .employee(Employee.builder()
                        .name(customerName)
                        .department(DepartmentFactory.getDepartment(departmentEnum))
                        .isFullTime(isFullTime)
                        .build())
                .hoursWorked(new BigDecimal(hoursWorked))
                .build();
    }
}

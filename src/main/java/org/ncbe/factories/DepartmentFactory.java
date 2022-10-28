package org.ncbe.factories;

import org.ncbe.entities.Department;
import org.ncbe.enums.DepartmentEnum;

import java.math.BigDecimal;

public class DepartmentFactory {
    private static Department intern = Department.builder()
            .deptName("Intern")
            .pay(new BigDecimal("10"))
            .hasBonus(false)
            .build();

    private static Department floorWorker = Department.builder()
            .deptName("Floor Worker")
            .pay(new BigDecimal("20"))
            .hasBonus(false)
            .build();

    private static Department supervisor = Department.builder()
            .deptName("Supervisor")
            .pay(new BigDecimal("25"))
            .hasBonus(false)
            .build();

    private static Department manager = Department.builder()
            .deptName("Manager")
            .pay(new BigDecimal("35"))
            .hasBonus(true)
            .build();

    private static Department executive = Department.builder()
            .deptName("Executive")
            .pay(new BigDecimal("50"))
            .hasBonus(true)
            .build();

    public static Department getDepartment(DepartmentEnum departmentEnum) {
        Department response = null;
        switch (departmentEnum) {
            case INTERN:
                response = intern;
                break;
            case FLOOR_WORKER:
                response = floorWorker;
                break;
            case SUPERVISOR:
                response = supervisor;
                break;
            case MANAGER:
                response = manager;
                break;
            case EXECUTIVE:
                response = executive;
                break;
        }
        return response;
    }
}

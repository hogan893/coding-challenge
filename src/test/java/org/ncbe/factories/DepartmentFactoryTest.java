package org.ncbe.factories;

import org.junit.jupiter.api.Test;
import org.ncbe.entities.Department;
import org.ncbe.enums.DepartmentEnum;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentFactoryTest {

    @Test
    public void getDepartmentTest_intern() {
        Department department = DepartmentFactory.getDepartment(DepartmentEnum.INTERN);
        assertEquals("Intern", department.getDeptName());
        assertEquals("10.0", department.getPay().toString());
        assertFalse(department.getHasBonus());
    }

    @Test
    public void getDepartmentTest_floorWorker() {
        Department department = DepartmentFactory.getDepartment(DepartmentEnum.FLOOR_WORKER);
        assertEquals("Floor Worker", department.getDeptName());
        assertEquals("20.0", department.getPay().toString());
        assertFalse(department.getHasBonus());
    }

    @Test
    public void getDepartmentTest_supervisor() {
        Department department = DepartmentFactory.getDepartment(DepartmentEnum.SUPERVISOR);
        assertEquals("Supervisor", department.getDeptName());
        assertEquals("25.0", department.getPay().toString());
        assertFalse(department.getHasBonus());
    }

    @Test
    public void getDepartmentTest_manager() {
        Department department = DepartmentFactory.getDepartment(DepartmentEnum.MANAGER);
        assertEquals("Manager", department.getDeptName());
        assertEquals("35.0", department.getPay().toString());
        assertTrue(department.getHasBonus());
    }

    @Test
    public void getDepartmentTest_executive() {
        Department department = DepartmentFactory.getDepartment(DepartmentEnum.EXECUTIVE);
        assertEquals("Executive", department.getDeptName());
        assertEquals("50.0", department.getPay().toString());
        assertTrue(department.getHasBonus());
    }
}

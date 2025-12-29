package app.controller;

import app.controller.Admin.ListEmployeeByRoleController;
import app.controller.Admin.RegisterEmployeeController;
import app.domain.model.Employee.CenterCoordinator;
import app.domain.model.Employee.Employee;
import app.domain.model.Employee.Nurse;
import app.domain.model.Employee.Receptionist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class ListEmployeeByRoleControllerTest {

    @BeforeEach
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = App.class.getDeclaredField("singleton");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void getListEmployeeByRole1() {

        RegisterEmployeeController rec = new RegisterEmployeeController();
        Employee vacx = new Receptionist("j","j","916597487","jeteve@msn.com","13765405");
        Employee emp = new Receptionist("jonas","rua penalves","912222222","1181478@isep.ipp.pt","10101010");
        rec.newEmployee("j","j","916597487","jeteve@msn.com","13765405",0);
        rec.registerEmployee();

        ListEmployeeByRoleController l = new ListEmployeeByRoleController();

        List<Employee> empx = l.getListEmployeeByRole(0);


        List<Employee> empList = new ArrayList<>() ;
        empList.add(emp);
        empList.add(vacx);

        System.out.println();
        for (Employee e : empx)
            System.out.println(e);

        for(Employee e : empList)
            System.out.println(e);

        assertTrue(empList.size() == empx.size() && empList.containsAll(empx) && empx.containsAll(empList));

    }

    @Test
    void getListEmployeeByRole2() {

        RegisterEmployeeController rec = new RegisterEmployeeController();
        Employee vacx = new CenterCoordinator("j","j","915533456","amichalo@aol.com","55666605");

        rec.newEmployee("j","j","915533456","amichalo@aol.com","55666605",1);
        rec.registerEmployee();

        ListEmployeeByRoleController l = new ListEmployeeByRoleController();

        List<Employee> empx = l.getListEmployeeByRole(1);


        List<Employee> emp = new ArrayList<>() ;
        emp.add(vacx);


        assertTrue(empx.contains(vacx));

    }

    @Test
    void getListEmployeeByRole3() {

        RegisterEmployeeController rec = new RegisterEmployeeController();
        Employee vacx = new Nurse("j","j","910233456","3566330@isep.ipp.pt","36666600");
        Employee e = new Nurse("gui","rua penalves","913333333","1191038@isep.ipp.pt","13111110");
        rec.newEmployee("j","j","910233456","3566330@isep.ipp.pt","36666600",2);
        rec.registerEmployee();

        ListEmployeeByRoleController l = new ListEmployeeByRoleController();

        List<Employee> empxy = l.getListEmployeeByRole(2);

        List<Employee> emp = new ArrayList<>() ;
        emp.add(e);
        emp.add(vacx);

        assertTrue(emp.size() == empxy.size() && emp.containsAll(empxy) && empxy.containsAll(emp));

    }

    @Test
    void getRoles() {
        ListEmployeeByRoleController rec = new ListEmployeeByRoleController();
        List<String> roles = new ArrayList<>();
        roles.add("RECEPTIONIST");
        roles.add("CENTER COORDINATOR");
        roles.add("NURSE");

        List<String> result = roles;
        List<String> expResult = rec.getRoles();

        assertLinesMatch(expResult,result);
    }
}
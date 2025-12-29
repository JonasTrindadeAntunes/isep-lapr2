package app.controller;


import app.controller.Admin.RegisterEmployeeController;
import app.domain.model.Employee.Employee;
import app.domain.model.Employee.Receptionist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterEmployeeControllerTest {

    @BeforeEach
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = App.class.getDeclaredField("singleton");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void testGetRoles() {
        RegisterEmployeeController rec = new RegisterEmployeeController();

        List<String> listOfRolesx = new ArrayList<>();
        listOfRolesx.add("RECEPTIONIST");
        listOfRolesx.add("CENTER COORDINATOR");
        listOfRolesx.add("NURSE");


        assertLinesMatch(rec.getRoles(),listOfRolesx);

    }

    @Test
    void testNewEmployee() {

        RegisterEmployeeController rec = new RegisterEmployeeController();

        boolean expResult = true;
        boolean result = rec.newEmployee("j","j","912222227","kdawson@yahoo.com","13000405",2);

        assertEquals(expResult,result);
    }


    @Test
    void testRegisterEmployee() {

        RegisterEmployeeController rec = new RegisterEmployeeController();
        rec.newEmployee("j","j","910923654","jetxve@msn.com","13003405",0);


        boolean expResult = true;
        boolean result = rec.registerEmployee();


        assertEquals(expResult,result);


    }

    @Test
    void testRegisterEmployee1() {

        RegisterEmployeeController rec = new RegisterEmployeeController();
        rec.newEmployee("j","j","916597387","mahbub@verizon.net","13265405",2);


        boolean expResult = true;
        boolean result = rec.registerEmployee();


        assertEquals(expResult,result);


    }

    @Test
    void testRegisterEmployee2() {

        RegisterEmployeeController rec = new RegisterEmployeeController();
        rec.newEmployee("j","j","911597387","specprog@live.com","11265405",2);


        boolean expResult = true;
        boolean result = rec.registerEmployee();


        assertEquals(expResult,result);


    }

    @Test
    void testGetEmployeeString() {

        RegisterEmployeeController rec = new RegisterEmployeeController();

        Employee vacx = new Receptionist("j","j","916597487","bastian@msn.com","13765405");

        rec.newEmployee("j","j","916597487","bastian@msn.com","13765405",1);
        rec.registerEmployee();
        rec.getEmployeeString();

        String expResult = vacx.toString();
        String result = rec.getEmployeeString();

        assertEquals(expResult,result);

    }

    @Test
    public void ensureNullIsNotAllowed() throws RuntimeException{

        RegisterEmployeeController rec = new RegisterEmployeeController();
        rec.newEmployee("j","j","916597487","carroll@yahoo.ca","13765405",4);


    }






}
package domain.model;

import app.controller.App;
import app.domain.model.Employee.Employee;
import app.domain.model.Platform.RegisterEmployee;
import app.domain.model.Platform.RegisterUsers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterEmployeeTest {

    @Test
    void newEmployee() {
        RegisterUsers userRegister = App.getInstance().getCompany().getUserRoleStore();
        RegisterEmployee re = new RegisterEmployee(userRegister);
        Employee emp = re.newEmployee("j","j","916597487","boser@yahoo.com","13765405",10);

        assertEquals(null,emp);
    }

    @Test
    void validateEmployee() {

        RegisterUsers userRegister = App.getInstance().getCompany().getUserRoleStore();
        RegisterEmployee re = new RegisterEmployee(userRegister);
        Employee emp = re.newEmployee("j","j","916597487","njpayne@yahoo.ca","13765405",1);
        re.registerEmployee(emp);
        Employee emp1 = re.newEmployee("j","j","916527487","njpayne@yahoo.ca","13762405",1);


        assertEquals(false,re.validateEmployee(emp1));
    }

    @Test
    void validateEmployee1() {

        RegisterUsers userRegister = App.getInstance().getCompany().getUserRoleStore();
        RegisterEmployee re = new RegisterEmployee(userRegister);
        Employee emp = re.newEmployee("j","j","916597480","baveja@icloud.com","13777700",1);
        re.registerEmployee(emp);
        Employee emp1 = re.newEmployee("j","j","916577400","harryh@gmail.com","13777700",1);



        assertEquals(false,re.validateEmployee(emp1));
    }

    @Test
    void validateEmployee2() {

        RegisterUsers userRegister = App.getInstance().getCompany().getUserRoleStore();
        RegisterEmployee re = new RegisterEmployee(userRegister);
        Employee emp = re.newEmployee("j","j","919876543","flavell@sbcglobal.net","13999900",1);
        re.registerEmployee(emp);
        Employee emp1 = re.newEmployee("j","j","919876543","marioph@sbcglobal.net","13999900",1);



        assertEquals(false,re.validateEmployee(emp1));
    }

    @Test
    void getEmployeeByEmailTest() {
        RegisterUsers userRegister = App.getInstance().getCompany().getUserRoleStore();
        RegisterEmployee re = new RegisterEmployee(userRegister);
        Employee emp = re.newEmployee("Rui","rua do Alberto","919876333","akoblin@yahoo.com","13599900",1);
        System.out.println(re.registerEmployee(emp));re.registerEmployee(emp);
        assertEquals(emp,re.getEmployeeByEmail("akoblin@yahoo.com"));
    }
    @Test
    void getEmployeeByEmailTestNull() {
        RegisterUsers userRegister = App.getInstance().getCompany().getUserRoleStore();
        RegisterEmployee re = new RegisterEmployee(userRegister);
        assertEquals(null, re.getEmployeeByEmail("8765679@isep.ipp.pt"));
    }
}
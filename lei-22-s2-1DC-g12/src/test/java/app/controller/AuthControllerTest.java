package app.controller;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    @Test
    void doLogin() {
        AuthController authController = new AuthController();
        assertEquals(true,authController.doLogin("admin@lei.sem2.pt" , "123456"));
    }

    @Test
    void doLogin1() {
        AuthController authController = new AuthController();
        assertEquals(false,authController.doLogin("" , "123456"));
    }

    @Test
    void getUserRoles() {

        AuthController authController = new AuthController();
        authController.doLogin("admin@lei.sem2.pt" , "123456");
        List<UserRoleDTO> lurd = authController.getUserRoles();

        List<String> roles = new ArrayList<>();
        roles.add("ADMINISTRATOR");

        assertTrue(lurd.get(0).getDescription().contains(roles.get(0)));

    }

    


}
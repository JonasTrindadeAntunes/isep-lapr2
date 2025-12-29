package app.controller;

import app.controller.Admin.LoadUsersFileController;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadUsersFileControllerTest {


    @Test
    void checkLogin() {
        LoadUsersFileController lufc= new LoadUsersFileController();
        App.getInstance().doLogin("admin@lei.sem2.pt","123456");


        assertTrue(lufc.checkLogin());
    }

    @Test
    void getListOfFiles() {
        LoadUsersFileController lufc= new LoadUsersFileController();

        List<String> listOfFilesname = new ArrayList<>();
        listOfFilesname.add("performanceDataFromGaoFuNationalCenterDoPortoVaccinationCenter.csv");
        listOfFilesname.add("SNSUserDataFromGaoFuNationalCenterDoPortoVaccinationCenter.csv");

        List<String> expected = lufc.getListOfFiles("src/main/resources");
        assertTrue(listOfFilesname.size() == expected.size() && listOfFilesname.containsAll(expected) && expected.containsAll(listOfFilesname));

    }


    @Test
    void loadUsersFromCSVUsersFileWithComma() {

        LoadUsersFileController lufc= new LoadUsersFileController();


        boolean expResult = true;
        boolean result = lufc.loadUsersFromCSV("SNSUserDataFromGaoFuNationalCenterDoPortoVaccinationCenter.csv");

        assertEquals(expResult,result);
    }

    @Test
    void loadUsersFromCSVUsersFileWithHeaderAndSemicolon() {

        LoadUsersFileController lufc= new LoadUsersFileController();

        boolean expResult = true;
        boolean result = lufc.loadUsersFromCSV("SNSUserDataFromGaoFuNationalCenterDoPortoVaccinationCenter.csv");

        assertEquals(expResult,result);
    }

    @Test
    void loadUsersFromCSV() {

        LoadUsersFileController lufc= new LoadUsersFileController();

        boolean expResult = false;
        boolean result = lufc.loadUsersFromCSV("X");

        assertEquals(expResult,result);

    }

    @Test
    void loadUsersFromCSV1() {

        LoadUsersFileController lufc= new LoadUsersFileController();

        boolean expResult = false;
        boolean result = lufc.loadUsersFromCSV(null);

        assertEquals(expResult,result);

    }


    @Test
    void showData() {
        LoadUsersFileController lufc= new LoadUsersFileController();
        boolean expResult = true;
        boolean result = lufc.showData();

        assertEquals(expResult,result);
    }

    @Test
    void printSystemUsers() {

        LoadUsersFileController lufc= new LoadUsersFileController();

        List<UserDTO> ex = App.getInstance().getCompany().getUserRoleStore().getAuthFacade().getUsers();
        List<UserDTO> exs = lufc.printSystemUsers();
        List<String> expResult = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for(UserDTO user : ex)
        {
             expResult.add(user.getName());
             expResult.add(user.getId());
             expResult.add(user.getRoles().get(0).getDescription());
        }

        for(UserDTO user : exs)
        {
            result.add(user.getName());
            result.add(user.getId());
            result.add(user.getRoles().get(0).getDescription());
        }



        assertEquals(expResult,result);
    }


}
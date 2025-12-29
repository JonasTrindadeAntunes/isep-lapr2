package domain.model;

import app.domain.model.Company;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void getDesignation() {
        Company comp = new Company("comp","1");
        assertEquals(comp.getDesignation(),"comp");
    }

    @Test
    void ensureDesignationNotNull() {


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Company("","1");
        });

        String expectedMessage = "Designation cannot be blank.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}
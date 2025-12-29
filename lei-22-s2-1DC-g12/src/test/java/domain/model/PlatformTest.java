package domain.model;

import app.domain.model.Platform.Platform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    @Test
    void ensureDescriptionNotNull() {


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Platform("","");
        });

        String expectedMessage = "Argument cant be null";
        String actualMessage = exception.getMessage();

        System.out.println(""+exception.getMessage());

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
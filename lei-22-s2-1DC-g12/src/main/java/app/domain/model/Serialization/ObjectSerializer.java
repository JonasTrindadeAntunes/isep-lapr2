package app.domain.model.Serialization;

import app.controller.App;
import app.domain.model.Company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The type Object serializer.
 */
public class ObjectSerializer {

    /**
     * The App.
     */
    private App app;

    /**
     * Instantiates a new Object serializer.
     */
    public ObjectSerializer(){
    }

    /**
     * Serialize .
     *
     * @return the boolean
     */
    public boolean serialize(){
        this.app = App.getInstance();
        Company company = app.getCompany();
        try {
            FileOutputStream fileOut = new FileOutputStream("company.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(company);
            outStream.close();
            fileOut.close();
        } catch(IOException i) {
            i.printStackTrace();
        }
        return true;
    }
}

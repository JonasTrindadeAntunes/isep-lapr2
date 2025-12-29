package app.domain.model.Serialization;

import app.controller.App;
import app.domain.model.Company;

import java.io.*;

/**
 * The type Object deserializer.
 */
public class ObjectDeserializer {

    private App app;

    /**
     * Instantiates a new Object deserializer.
     */
    public ObjectDeserializer(){

    }
    public ObjectDeserializer(App app){
        this.app = app;
    }

    /**
     * Deserialize boolean.
     *
     * @return the boolean
     */
    public boolean deserialize(){
        if(app == null){
            app = App.getInstance();
        }
        Company company = null;
        try {
            FileInputStream fileIn =new FileInputStream("company.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            company = (Company) in.readObject();
            app.setCompany(company);
            in.close();
            fileIn.close();
        } catch(FileNotFoundException e){
            System.out.println(e);
            return false;
        } catch(IOException i) {
            i.printStackTrace();
            return false;
        } catch(ClassNotFoundException c) {
            System.out.println("Company class not found.");
            c.printStackTrace();
            return false;
        }
        return false;
    }



}

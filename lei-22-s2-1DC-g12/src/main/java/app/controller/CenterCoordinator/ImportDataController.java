package app.controller.CenterCoordinator;

import app.controller.App;
import app.domain.model.Platform.Platform;
import app.domain.shared.Constants;

import java.io.File;
import java.util.List;

/**
 * The type Import data controller.
 */
public class ImportDataController  {

    /**
     * The App.
     */
    private App m_oApp;

    /**
     * The Platform.
     */
    private Platform m_oPlatform;


    /**
     * Instantiates a new Import data controller.
     */
    public ImportDataController(){
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();

    }

    /**
     * Check login .
     *
     * @return the boolean
     */
    public boolean checkLogin(){
        return m_oApp.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_CENTER_COORDINATOR);
    }

    /**
     * Get list of files.
     *
     * @param path the path
     * @return the list
     */
    public List<String> getListOfFiles(String path){
        try
        {
            return this.m_oPlatform.getImportPerformanceFromCSV().getListOfFiles(path);
    }catch (RuntimeException ex)
        {
            System.out.println(ex.getMessage());}
        return null;
    }

    /**
     * Import performance from csv.
     *
     * @param fileName the file name
     * @return the boolean
     */
    public boolean importPerformanceFromCSV(String fileName){
        try{
            return this.m_oPlatform.getImportPerformanceFromCSV().importPerformanceFromCSV(fileName);
        }
        catch (RuntimeException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Import performance from csv.
     *
     * @param fileName the file name
     * @return the boolean
     */
    public boolean importPerformanceFromCSV(File fileName){
        try{
            return this.m_oPlatform.getImportPerformanceFromCSV().importPerformanceFromCSV(fileName);
        }
        catch (RuntimeException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }






}

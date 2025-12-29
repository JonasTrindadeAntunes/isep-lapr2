package app.controller.Admin;

import app.controller.App;
import app.domain.model.Platform.Platform;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.util.List;

/**
 * The type Load users file controller.
 */
public class LoadUsersFileController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Platform
     */
    private Platform m_oPlatform;


    /**
     * Instantiates a new Load users file controller.
     */
    public LoadUsersFileController(){
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();

    }

    /**
     * Check login of the Administrator
     *
     * @return the boolean
     */
    public boolean checkLogin(){
        return m_oApp.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_ADMIN);
    }


    /**
     * Gets list of files.
     *
     * @param path the path
     * @return the list of files
     */
    public List<String> getListOfFiles(String path)
    {
        try
        {
            return this.m_oPlatform.getLoadUsersFromCSV().getListOfFiles(path);

        }catch(RuntimeException ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Load users from csv .
     *
     * @param fileName the file name
     * @return the boolean
     */
    public boolean loadUsersFromCSV(String fileName){
        try
        {
            return this.m_oPlatform.getLoadUsersFromCSV().loadUsersFromCSV(fileName);
        }
        catch(RuntimeException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Register list of users in system .
     *
     * @return the boolean
     */
    public boolean registerListStringUsersInSystem()
    {
        return this.m_oPlatform.getLoadUsersFromCSV().registerListStringUsersInSystem();
    }

    /**
     * Show SNSUsers data that was stored in file.
     *
     * @return the boolean
     */
    public boolean showData()
    {
        return this.m_oPlatform.getLoadUsersFromCSV().showData();
    }

    /**
     * Print system users .
     *
     * @return the List<UserDTO>
     */
    public List<UserDTO> printSystemUsers()
    {
       return this.m_oPlatform.getLoadUsersFromCSV().printSystemUsers();
    }


}

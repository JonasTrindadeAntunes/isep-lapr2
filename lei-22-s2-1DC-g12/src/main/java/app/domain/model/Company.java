package app.domain.model;

import app.domain.model.Platform.Platform;
import app.domain.model.Platform.RegisterUsers;
import app.domain.shared.MessageBundle;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * The type Company.
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company implements Serializable {

    private String designation;
    private RegisterUsers registerUsers;
    private Platform m_oPlatform;
    /**
     * Instantiates a new Company.
     *
     * @param designation the designation
     */
    public Company(String designation,String recoveryTime)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException(MessageBundle.getString("designationcannotbeblank"));

        this.designation = designation;
        this.m_oPlatform = new Platform(MessageBundle.getString("registersplatform"),recoveryTime);
        this.registerUsers = new RegisterUsers(new AuthFacade());

    }

    /**
     * Gets designation.
     *
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }


    /**
     * Gets platform.
     *
     * @return the platform
     */
    public Platform getPlatform(){return m_oPlatform;}

    /**
     * Gets user role store.
     *
     * @return the user role store
     */
    public RegisterUsers getUserRoleStore() {
        return registerUsers;
    }

}

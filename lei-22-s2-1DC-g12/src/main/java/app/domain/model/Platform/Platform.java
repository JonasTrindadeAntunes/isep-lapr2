package app.domain.model.Platform;


import app.domain.model.*;
import app.domain.model.VaccinationCenter.RegisterVaccinationCenter;
import app.domain.model.Vaccine.RegisterVaccineType;
import app.domain.shared.MessageBundle;

import java.io.Serializable;

/**
 * The type Platform.
 */
public class Platform implements Serializable {


    private final SMSNotifier smsNotifier;
    private final EmailNotifier emailNotifier;
    private final ExportVaccinationStatistics exportVaccinationStatistics;
    private String description;

    private RegisterEmployee m_oRegisterEmployee;
    private final RegisterVaccinationCenter m_oRegisterVaccinationCenter;
    private final RegisterVaccineType m_oRegisterVaccineType;
    private RegisterSNSUser m_oRegisterSNSUser;
    private LoadUsersFromCSV m_oLoadUsersFromCSV;
    private ImportPerformanceFromCSV m_oImportPerformanceFromCSV;


    /**
     * Instantiates a new Platform.
     *
     * @param description the description
     */
    public Platform(String description,String recoveryTime) {
        if((description == null) || (description.isEmpty()))
            throw new IllegalArgumentException(MessageBundle.getString("argumentcantbenull"));

        this.description = description;



        this.m_oRegisterVaccinationCenter = new RegisterVaccinationCenter(recoveryTime);
        this.m_oRegisterVaccineType = new RegisterVaccineType();

        this.smsNotifier = new SMSNotifier();
        this.emailNotifier = new EmailNotifier();
        this.exportVaccinationStatistics = new ExportVaccinationStatistics();

    }

    /**
     * Gets export vaccination statistics.
     *
     * @return the export vaccination statistics
     */
    public ExportVaccinationStatistics getExportVaccinationStatistics()
    {
        return this.exportVaccinationStatistics;
    }


    /**
     * Gets sms notifier.
     *
     * @return the sms notifier
     */
    public SMSNotifier getSmsNotifier() {
        return smsNotifier;
    }

    /**
     * Gets email notifier.
     *
     * @return the email notifier
     */
    public EmailNotifier getEmailNotifier() {return emailNotifier;}

    /**
     * Get register employee.
     *
     * @return the register employee
     */
    public RegisterEmployee getRegisterEmployee(){return this.m_oRegisterEmployee;}

    /**
     * Get register vaccination center.
     *
     * @return the register vaccination center
     */
    public RegisterVaccinationCenter getRegisterVaccinationCenter(){return this.m_oRegisterVaccinationCenter;}

    /**
     * Gets register vaccine type.
     *
     * @return the register vaccine type
     */
    public RegisterVaccineType getRegisterVaccineType() {return this.m_oRegisterVaccineType;}


    /**
     * Gets register sns user.
     *
     * @return the register sns user
     */
    public RegisterSNSUser getRegisterSNSUser() {return this.m_oRegisterSNSUser;}


    /**
     * Get class where we load files of type CSV
     *
     * @return the load from csv
     */
    public LoadUsersFromCSV getLoadUsersFromCSV(){return this.m_oLoadUsersFromCSV;}


    /**
     * Get import performance from csv .
     *
     * @return the import performance from csv
     */
    public ImportPerformanceFromCSV getImportPerformanceFromCSV(){return this.m_oImportPerformanceFromCSV;}



    public void initializeRegisters(RegisterUsers registerUsers) {
        this.m_oRegisterSNSUser = new RegisterSNSUser(registerUsers);
        this.m_oRegisterEmployee = new RegisterEmployee(registerUsers);
        this.m_oLoadUsersFromCSV = new LoadUsersFromCSV(registerUsers,m_oRegisterSNSUser);
        this.m_oImportPerformanceFromCSV = new ImportPerformanceFromCSV(registerUsers,m_oRegisterSNSUser,m_oRegisterVaccineType,m_oRegisterVaccinationCenter);

    }


}

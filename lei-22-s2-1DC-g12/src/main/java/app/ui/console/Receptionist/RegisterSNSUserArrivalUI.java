package app.ui.console.Receptionist;
import app.controller.Receptionist.RegisterSNSUserArrivalController;
import app.domain.model.VaccineSchedule;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import java.util.Calendar;


/**
 * The type Register sns user arrival ui.
 */
public class RegisterSNSUserArrivalUI implements Runnable {

    private final RegisterSNSUserArrivalController m_controller;


    /**
     * Instantiates a new Register sns user arrival ui.
     */
    public RegisterSNSUserArrivalUI() {
        this.m_controller = new RegisterSNSUserArrivalController();
    }

    public void run() {

        System.out.println(MessageBundle.getString("registersnsuserarrival"));

        if (!m_controller.checkLogin()) {
            System.out.println(MessageBundle.getString("unauthorizeduser"));
            return;
        }

            if (m_controller.getWorkingVaccinationCenter() == null) {
                System.out.println(MessageBundle.getString("backtoreceptionistmenu"));
            } else {

                String snsUserNumber = Utils.readLineFromConsole(MessageBundle.getString("insertsnsusernumber"));
                while (!StringUtils.isNumeric(snsUserNumber)) {
                    System.out.println(MessageBundle.getString("snsusernumbermustbenumeric"));
                    snsUserNumber = Utils.readLineFromConsole(MessageBundle.getString("insertsnsusernumber"));
                }


                if (!m_controller.checkIfExistsSNSUser(snsUserNumber)) {
                    System.out.println(MessageBundle.getString("snsusernotfound"));
                } else {
                    VaccineSchedule vs = m_controller.validateUserArrival(m_controller.getWorkingVaccinationCenter(), snsUserNumber);
                    if (vs!=null) {
                        if (Utils.confirm(MessageBundle.getString("scheduleexistsendtowaitingroom")+"(S/N)"))
                        {
                            m_controller.registerUserArrival(m_controller.getWorkingVaccinationCenter(), vs, Calendar.getInstance().getTime());
                            System.out.println(MessageBundle.getString("registrationsucessful"));
                        }

                    }
                    else
                    {
                        System.out.println(MessageBundle.getString("userarrivalnonsuccessful"));
                        run();
                    }

                }

            }
        }
    }

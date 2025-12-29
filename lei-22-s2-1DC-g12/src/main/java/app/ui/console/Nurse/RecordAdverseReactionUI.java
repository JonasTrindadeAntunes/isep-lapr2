package app.ui.console.Nurse;

import app.controller.Nurse.RecordAdverseReactionController;
import app.domain.model.SNSUser;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;
import org.apache.commons.lang3.StringUtils;

public class RecordAdverseReactionUI implements Runnable{

    private RecordAdverseReactionController m_controller;

    public RecordAdverseReactionUI(){this.m_controller = new RecordAdverseReactionController();}

    public void run()
    {

        System.out.println("Record adverse reactions of the SNS user");

        if(!m_controller.checkLogin()) {
            System.out.println(MessageBundle.getString("unauthorizeduser"));
            return;
        }

        if (m_controller.getWorkingVaccinationCenter() == null) {
            System.out.println(MessageBundle.getString("backtonursemenu"));
        } else {

            String snsUserNumber = Utils.readLineFromConsole(MessageBundle.getString("insertsnsusernumber"));
            while (!StringUtils.isNumeric(snsUserNumber)) {
                System.out.println(MessageBundle.getString("snsusernumbermustbenumeric"));
                snsUserNumber = Utils.readLineFromConsole(MessageBundle.getString("insertsnsusernumber"));
            }


            if (!m_controller.checkIfExistsSNSUser(snsUserNumber)) {
                System.out.println(MessageBundle.getString("snsusernotfound"));
            } else {
                SNSUser user = m_controller.getSNSUser(snsUserNumber);

                int vaccineAdministration = Utils.showAndSelectIndex(m_controller.getVaccineAdministrations(user),"Select to which vaccine administration you want to record an adverse reaction");

                String adverseReaction = Utils.readLineFromConsole("Type a short description about the adverse reaction (with at most 300 characters)");
                m_controller.recordAdverseReaction(user,adverseReaction,vaccineAdministration);
                }
            }

            if (Utils.confirm(MessageBundle.getString("confirmdata") +"(S/N)"))
            {
                System.out.println(MessageBundle.getString("operationsucessful"));
            }
            else
                run();

        }

    }


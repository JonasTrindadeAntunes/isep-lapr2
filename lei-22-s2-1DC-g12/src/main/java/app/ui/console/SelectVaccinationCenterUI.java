package app.ui.console;


import app.controller.SelectVaccinationCenterController;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;


/**
 * The type Select vaccination center ui.
 */
public class SelectVaccinationCenterUI implements Runnable{

    private final SelectVaccinationCenterController m_controller;

    /**
     * Instantiates a new Select vaccination center ui.
     */
    public SelectVaccinationCenterUI()
    {
        this.m_controller = new SelectVaccinationCenterController();
    }

    public void run() {

        if (this.m_controller.getListVaccinationCenter() == null) {
            System.out.println(MessageBundle.getString("backtoadminmenu"));
        } else {
            if (this.m_controller.getListVaccinationCenter().isEmpty()) {
                System.out.println(MessageBundle.getString("errornovaccinationcenters"));
                return;
            }

            Object vaccinationCenter = Utils.showAndSelectOne(m_controller.getListVaccinationCenter(), MessageBundle.getString("selectvaccinationcenter"));
            m_controller.setWorkingVaccinationCenter((VaccinationCenter) vaccinationCenter);
        }

    }


}

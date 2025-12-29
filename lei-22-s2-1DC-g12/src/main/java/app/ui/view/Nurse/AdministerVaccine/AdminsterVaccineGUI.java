package app.ui.view.Nurse.AdministerVaccine;

import app.controller.Nurse.AdministerVaccineController;
import app.domain.model.SNSUser;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.VaccineAdministration;
import app.ui.view.Nurse.NurseOptionsMenu;
import javafx.scene.control.Alert;

public class AdminsterVaccineGUI{

    NurseOptionsMenu nurseOptionsMenu;

    AdministerVaccineController controller;

    public AdminsterVaccineGUI(NurseOptionsMenu nurseOptionsMenu){
        this.nurseOptionsMenu = nurseOptionsMenu;
        controller = new AdministerVaccineController();
    }
    public void start() {
        try {
            AdministerVaccine_1 administerVaccine_1 = (AdministerVaccine_1) this.nurseOptionsMenu.getNurseGUI().getMainApp().replaceSceneContent("/fxml/AdministerVaccine_1.fxml");
            administerVaccine_1.setAdminsterVaccineGUI(this);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getAvaliableVaccines(SNSUser user){
        if(user == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No user was selected!");
            a.show();
            return;
        }
        try {
            AdministerVaccine_2 administerVaccine_2 = (AdministerVaccine_2) this.nurseOptionsMenu.getNurseGUI().getMainApp().replaceSceneContent("/fxml/AdministerVaccine_2.fxml");
            administerVaccine_2.setAdminsterVaccineGUI(this);
            administerVaccine_2.setUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getDosageAndAdverReactions(SNSUser user, Vaccine vaccine){
        if(vaccine == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No vaccine was selected!");
            a.show();
            return;
        }
        try {
            AdministerVaccine_3 administerVaccine_3 = (AdministerVaccine_3) this.nurseOptionsMenu.getNurseGUI().getMainApp().replaceSceneContent("/fxml/AdministerVaccine_3.fxml");
            administerVaccine_3.setAdminsterVaccineGUI(this);
            administerVaccine_3.setFields(vaccine,user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void confirmVaccineAdministration(SNSUser user, Vaccine vaccine, String lotNumber){
        if(lotNumber == null||lotNumber.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No lot number was inserted!");
            a.show();
            return;
        }
        try {

            AdministerVaccine_4 administerVaccine_4 = (AdministerVaccine_4) this.nurseOptionsMenu.getNurseGUI().getMainApp().replaceSceneContent("/fxml/AdministerVaccine_4.fxml");
            administerVaccine_4.setAdminsterVaccineGUI(this);
            administerVaccine_4.presetVaccineAdministration(this.controller.createVaccineAdministration(user, vaccine, lotNumber));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void confirmVaccineAdministration(VaccineAdministration vaccineAdministration) {
        controller.registerVaccineAdministration(vaccineAdministration);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Vaccine administration sucessfully registered!");
        a.show();
        this.nurseOptionsMenu.getNurseGUI().runNurseUI();
    }

    public void cancel(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("Vaccine administration sucessfully canceled!");
        a.show();
        this.nurseOptionsMenu.getNurseGUI().runNurseUI();
    }
}

package app.ui.view.Nurse.RegisterAdverseReaction;

import app.controller.Nurse.RecordAdverseReactionController;
import app.domain.model.SNSUser;
import app.domain.model.VaccineAdministration;
import app.ui.view.Nurse.NurseOptionsMenu;
import javafx.scene.control.Alert;

public class RegisterAdverseReactionGUI {


    RecordAdverseReactionController controller;

    NurseOptionsMenu nurseOptionsMenu;

    int vaccineAdministrationIndex;
    private SNSUser user;

    public RegisterAdverseReactionGUI(NurseOptionsMenu nurseOptionsMenu){
        this.nurseOptionsMenu = nurseOptionsMenu;
        controller = new RecordAdverseReactionController();

    }

    public void start() {
        try {
            RegisterAdverseReaction_1 registerAdverseReaction_1 = (RegisterAdverseReaction_1) this.nurseOptionsMenu.getNurseGUI().getMainApp().replaceSceneContent("/fxml/RegisterAdverseReaction_1.fxml");
            registerAdverseReaction_1.setRegisterAdverseReactionGUI(this);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    public void getSNSUser(String text) {
        if(!controller.checkIfExistsSNSUser(text)){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("SNS user not found!");
            a.show();
            return;
        }
        try {
            this.user = controller.getSNSUser(text);
            if(controller.getVaccineAdministrations(user).isEmpty()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("No vaccine administrations for this user!");
                a.show();
                backToNurseMenu();
            }
            RegisterAdverseReaction_2 registerAdverseReaction_2 = (RegisterAdverseReaction_2) this.nurseOptionsMenu.getNurseGUI().getMainApp().replaceSceneContent("/fxml/RegisterAdverseReaction_2.fxml");
            registerAdverseReaction_2.setRegisterAdverseReactionGUI(this);
            registerAdverseReaction_2.getSNSUserVaccineAdministrations(user);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void getAdverseReactionDescription(int vaccineAdministration){
        if(vaccineAdministration<0){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No vaccine administration selected!");
            a.show();
            return;
        }
        try {
            vaccineAdministrationIndex = vaccineAdministration;
            RegisterAdverseReaction_3 registerAdverseReaction_3 = (RegisterAdverseReaction_3) this.nurseOptionsMenu.getNurseGUI().getMainApp().replaceSceneContent("/fxml/RegisterAdverseReaction_3.fxml");
            registerAdverseReaction_3.setRegisterAdverseReactionGUI(this);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    public void registerAdverseReaction(String text) {
        if(text == null || text.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No description was inserted.!");
            a.show();
            return;
        }
        if(this.controller.recordAdverseReaction(user,text,vaccineAdministrationIndex)) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Sucessfully registered adverse reaction!");
            a.show();
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error creating adverse reaction!");
            a.show();
        }
        backToNurseMenu();
    }

    private void backToNurseMenu() {
        this.nurseOptionsMenu.getNurseGUI().runNurseUI();
    }


}

package app.ui.view.RoleUI;

import app.ui.view.Base;
import app.ui.view.CenterCoordinator.CenterCoordinatorOptionsMenu;
import app.ui.view.ScreenGUI;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class CenterCoordinatorGUI implements ScreenGUI {
    private Base mainApp;
    private CenterCoordinatorOptionsMenu optionsMenu;

    @FXML
    private MenuItem logoutMenuItem;


    public CenterCoordinatorGUI(Base mainApp){
        this.mainApp = mainApp;
    }

    public Base getMainApp() {
        return this.mainApp;
    }


    @Override
    public void run() {
        runCenterCoordinatorGUI();

    }

    public void runCenterCoordinatorGUI(){
        try {
            CenterCoordinatorOptionsMenu centerCoordinatorOptionsMenu = (CenterCoordinatorOptionsMenu) this.mainApp.
                    replaceSceneContent("/fxml/CenterCoordinatorUI.fxml");
            centerCoordinatorOptionsMenu.setCenterCoordinatorGUI(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package app.ui.view;

import javafx.fxml.Initializable;

public class MenuItemUI {
    String description;
    ScreenGUI screenGUI;

    public MenuItemUI(String description, ScreenGUI screenGUI) {
        this.description = description;
        this.screenGUI = screenGUI;
    }

    public String getDescription() {
        return description;
    }

    public ScreenGUI getScreenGUI() {
        return screenGUI;
    }
}

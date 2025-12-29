package app.ui.view;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.controller.App;
import app.domain.model.Serialization.ObjectDeserializer;
import app.domain.model.Serialization.ObjectSerializer;
import app.ui.console.MainMenuUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Base extends Application {
    private Stage stage;
    private App app;
    private final double MINIMUM_WINDOW_WIDTH = 350.0;
    private final double MINIMUM_WINDOW_HEIGHT = 200.0;
    private final double SCENE_WIDTH = 350.0;
    private final double SCENE_HEIGHT = 200.0;


    @Override
    public void start(Stage stage) throws Exception {
        app = App.getInstance();
        app.setBase(this);
        this.stage = stage;
        stage.setTitle("Vaccination APP");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        toMainScene();
        this.stage.show();
    }

    public Stage getStage() {
        return this.stage;
    }

    public void toMainScene() {
        try {
            MainUI mainUI = (MainUI) replaceSceneContent("/fxml/Main.fxml");
            mainUI.setMainApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Base.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Base.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add("/styles/Styles.css");
        this.stage.setScene(scene);
        this.stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public App getApp() {
        return app;
    }

    @Override
    public void stop(){
        ObjectSerializer objectSerializer = new ObjectSerializer();
        objectSerializer.serialize();
        System.exit(0);
    }
}

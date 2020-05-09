package pl.javastart.mp3player.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPaneController {

    @FXML
    private MenuItem fileMenuItem;

    @FXML
    private MenuItem dirMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    public void initialize(){
        aboutMenuItem.setOnAction(event -> {
            try {
                AnchorPane aboutPane = FXMLLoader.load(getClass().getResource("/fxml/aboutPane.fxml"));
                Scene scene = new Scene(aboutPane);
                Stage stage = new Stage();
                stage.setTitle("Mp3Player - about");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        closeMenuItem.setOnAction(event -> Platform.exit());
    }

    public MenuItem getFileMenuItem() {
        return fileMenuItem;
    }

    public MenuItem getDirMenuItem() {
        return dirMenuItem;
    }

    public MenuItem getCloseMenuItem() {
        return closeMenuItem;
    }

    public MenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }
}

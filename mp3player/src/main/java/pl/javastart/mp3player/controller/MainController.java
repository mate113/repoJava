package pl.javastart.mp3player.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.javastart.mp3player.mp3.Mp3Parser;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3Player;

import java.io.File;

public class MainController {
    @FXML
    private ContentPaneController contentPaneController;
    @FXML
    private ControlPaneController controlPaneController;
    @FXML
    private MenuPaneController menuPaneController;

    private Mp3Player player;

    public void initialize() {
        createPlayer();
        configureButtons();
        configureTableClick();
        configureMenu();
    }

    private void createPlayer() {
        ObservableList<Mp3Song> items = contentPaneController.getContentTable().getItems();
        player = new Mp3Player(items);
    }

    private void configureTableClick() {
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                int index = contentTable.getSelectionModel().getSelectedIndex();
                playSelectedSong(index);
            }
        });
    }

    private void playSelectedSong(int index) {
        player.loadSong(index);
        configureProgressBar();
        configureVolume();
        controlPaneController.getPlayButton().setSelected(true);
    }

    private void configureVolume() {
        Slider volumeSlider = controlPaneController.getVolumeSlider();
        volumeSlider.valueProperty().unbind();
        volumeSlider.setMax(1.0);
        volumeSlider.valueProperty().bindBidirectional(player.getMediaPlayer().volumeProperty());
    }

    private void configureProgressBar() {
        Slider progressSlider = controlPaneController.getProgressSlider();
        player.getMediaPlayer().setOnReady(() -> progressSlider.setMax(player.getSongLength()));
        player.getMediaPlayer().currentTimeProperty().addListener((arg, oldVolue, newValue) ->
                progressSlider.setValue(newValue.toSeconds()));
        progressSlider.valueProperty().addListener((arg, oldValue, newValue) -> {
            if (progressSlider.isValueChanging())
                player.getMediaPlayer().seek(Duration.seconds(newValue.doubleValue()));
        });

    }

    private void configureButtons(){
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        ToggleButton playButton = controlPaneController.getPlayButton();
        Button nextButton = controlPaneController.getNextButton();
        Button previousButton = controlPaneController.getPreviousButton();

        playButton.setOnAction(actionEvent ->{
            if (playButton.isSelected())
                player.play();
            else
                player.stop();
        });
        previousButton.setOnAction(actionEvent -> {
            contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex() - 1);
            playSelectedSong(contentTable.getSelectionModel().getSelectedIndex());
        });
        nextButton.setOnAction(actionEvent -> {
            contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex() + 1);
            playSelectedSong(contentTable.getSelectionModel().getSelectedIndex());
        });
    }

    private void configureMenu() {
        MenuItem fileMenuItem = menuPaneController.getFileMenuItem();
        MenuItem dirMenuItem = menuPaneController.getDirMenuItem();

        fileMenuItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3", "*.mp3"));
            File file = fileChooser.showOpenDialog(new Stage());
            try{
                contentPaneController.getContentTable().getItems().add(Mp3Parser.createMp3Song(file));
                showMessage("Wczytano pilk " + file.getName());
            } catch (Exception e) {
                showMessage("Wystąpił błąd podczas odczytu pilku " + file.getName());
            }
        });

        dirMenuItem.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(new Stage());
            try {
                contentPaneController.getContentTable().getItems().addAll(Mp3Parser.createMp3List(file));
                showMessage("Wczytano folder " + file.getName());
            } catch (Exception e) {
                showMessage("Wystąpił błąd podczas odczytu folderu");
            }
        });
    }

    private void showMessage(String message){
        controlPaneController.getMessageTextField().setText(message);
    }
}

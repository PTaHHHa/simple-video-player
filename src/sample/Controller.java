package sample;
import  java.net.URL;
import java.util.ResourceBundle;
import java.io.*;
import com.sun.glass.ui.Pixels;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.naming.Binding;


public class Controller {

    @FXML
    private Button MuteButton;

    @FXML
    private Button PlayButton;

    @FXML
    private Button StopButton;

    @FXML
    private Button PauseButton;

    @FXML
    private Slider VolumeSlider;

    @FXML
    private Slider TimeSlider;

    @FXML
    private Button OpenFile;

    @FXML
    private MediaView MediaView;

    @FXML

    private MediaPlayer mediaPlayer;
    private String filepath;
    @FXML
    private void OpenFile(ActionEvent event){
        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select file *.mp4","*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file=fileChooser.showOpenDialog(null);
        filepath =file.toURI().toString();
        if (filepath != null) {
            Media media=new Media(filepath);
            mediaPlayer=new MediaPlayer(media);
            MediaView.setMediaPlayer(mediaPlayer);

            DoubleProperty width=MediaView.fitWidthProperty();
            DoubleProperty height=MediaView.fitHeightProperty();
            width.bind(Bindings.selectDouble(MediaView.sceneProperty(),"width"));
            height.bind(Bindings.selectDouble(MediaView.sceneProperty(),"height"));

            VolumeSlider.setValue(mediaPlayer.getVolume()*100);
            VolumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(VolumeSlider.getValue()/100);
                }
            });

            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration newValue) {
                    TimeSlider.setValue(newValue.toSeconds());
                }
            });
            mediaPlayer.play();
        }

    }
    @FXML
    private void PlayButton(ActionEvent event){
        mediaPlayer.play();
    }

    @FXML
    private void PauseButton(ActionEvent event){
        mediaPlayer.pause();
    }

    @FXML
    private void StopButton(ActionEvent event){
        mediaPlayer.stop();
    }

    @FXML
    private void MuteButton(ActionEvent event){
        if (!mediaPlayer.isMute()) {
            mediaPlayer.setMute(true);
        }
        else {
            mediaPlayer.setMute(false);
        }

    }
}

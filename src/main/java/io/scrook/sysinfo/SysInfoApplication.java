package io.scrook.sysinfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SysInfoApplication extends Application {

  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
    primaryStage.setTitle("SystemInformation | Allgemein");
    primaryStage.setScene(new Scene(root, 800, 500));
    primaryStage.show();

  }

}

package io.scrook.sysinfo.controller;

import io.scrook.sysinfo.system.DefaultSystemInfoProvider;
import io.scrook.sysinfo.system.SystemInfoProvider;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.EventObject;
import java.util.ResourceBundle;

import static java.net.InetAddress.getLocalHost;


public class SysInfoController implements Initializable {

  @FXML
  private Label freeMemory;

  @FXML
  private Label usedMemory;

  @FXML
  private Label totalMemory;

  @FXML
  private Label sysName;

  @FXML
  private Label operatingSys;

  @FXML
  private Label kernelVersion;

  @FXML
  private Label javaVersion;

  @FXML
  private Label screenSize;

  private SystemInfoProvider systemInfoProvider = new DefaultSystemInfoProvider();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    if (freeMemory != null && usedMemory != null && totalMemory != null) {
      freeMemory.setText(String.valueOf(systemInfoProvider.getFreeMemory(SystemInfoProvider.SizeUnit.GB)));
      usedMemory.setText(String.valueOf(systemInfoProvider.getUsedMemory(SystemInfoProvider.SizeUnit.GB)));
      totalMemory.setText(String.valueOf(systemInfoProvider.getTotalMemory(SystemInfoProvider.SizeUnit.GB)));


      try {
        sysName.setText(getLocalHost().getHostName().toUpperCase());
      } catch (UnknownHostException e) {
        e.printStackTrace();
      }
      operatingSys.setText(System.getProperty("os.name").toUpperCase());
      kernelVersion.setText(System.getProperty("os.version").toUpperCase());
      javaVersion.setText(System.getProperty("java.version").toUpperCase());

      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
      screenSize.setText(screen.getWidth() + " x " + screen.getHeight());
    }
  }

  public void onReloadClick(MouseEvent mouseEvent) throws Exception {
    changeScene("SystemInformationen | Main", "/fxml/main.fxml", mouseEvent);
  }

  public void onMainClick(MouseEvent mouseEvent) throws Exception {
    changeScene("SystemInformationen | Main", "/fxml/main.fxml", mouseEvent);
  }

  public void onHardwareClick(MouseEvent mouseEvent) throws Exception {
    changeScene("SystemInformationen | Hardware", "/fxml/hardware.fxml", mouseEvent);
  }

  public void onNetworkClick(MouseEvent mouseEvent) throws Exception {
    changeScene("SystemInformationen | Network", "/fxml/network.fxml", mouseEvent);
  }

  private void changeScene(String title, String fxmlPath, EventObject eventObject) throws IOException {
    Parent main = FXMLLoader.load(getClass().getResource(fxmlPath));
    Scene scene = new Scene(main, 800, 500);
    Stage window = (Stage)((Node)eventObject.getSource()).getScene().getWindow();
    window.setScene(scene);
    window.setTitle(title);
    window.show();
  }

  public void onQuitClick() {
    Platform.exit();
  }

}

package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import static org.example.db.DBHelper.obtenerPedidosMesaActual;

public class PrimaryController implements Initializable {
    public static String nombreMesa;
    @FXML
    private Pane mesa_1;
    @FXML
    private Pane mesa_2;
    @FXML
    private Pane mesa_3;
    @FXML
    private Pane mesa_4;
    @FXML
    private Pane mesa_5;
    @FXML
    private Pane mesa_6;
    @FXML
    private Pane mesa_7;
    @FXML
    private Text textoMensaje;


    @FXML
    private void seleccionarMesa1() throws IOException {
        nombreMesa = "mesa_1";
    }

    @FXML
    private void seleccionarMesa2() throws IOException {
        nombreMesa = "mesa_2";
    }

    @FXML
    private void seleccionarMesa3() throws IOException {
        nombreMesa = "mesa_3";
    }

    @FXML
    private void seleccionarMesa4() throws IOException {
        nombreMesa = "mesa_4";
    }

    @FXML
    private void seleccionarMesa5() throws IOException {
        nombreMesa = "mesa_5";
    }

    @FXML
    private void seleccionarMesa6() throws IOException {
        nombreMesa = "mesa_6";
    }

    @FXML
    private void seleccionarMesa7() throws IOException {
        nombreMesa = "mesa_7";
    }

    @FXML
    private void btnCerrarAplicacion() {
        try {
            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void btnAbrirMesa() {
        if (nombreMesa != null) {
            try {
                App.setRoot("secondary");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            textoMensaje.setText("¡Selecciona una mesa para abrir!");
        }

    }

    @FXML
    public void btnAbrirHistoricos() {
        try {
            App.setRoot("historico");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pane[] listaPane = {mesa_1, mesa_2, mesa_3, mesa_4, mesa_5, mesa_6, mesa_7};
        cargarVistaMesasOcupadas(listaPane);

    }

    private void cargarVistaMesasOcupadas(Pane[] listaPane) {
        for (int i = 1; i < 8; i++) {
            if (!obtenerPedidosMesaActual(i).isEmpty()) {
                listaPane[i - 1].setStyle("-fx-background-color: red;");
            } else {
                listaPane[i - 1].setStyle("-fx-background-color: green;");
            }
        }
    }
}

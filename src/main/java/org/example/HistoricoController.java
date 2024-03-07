package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.models.Mesa;
import org.example.models.Pedido;
import org.example.models.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static org.example.PrimaryController.nombreMesa;
import static org.example.db.DBHelper.*;

public class HistoricoController implements Initializable {


@FXML
private TableView tableViewMesas;

    @FXML
    private void volverMenuPrincipal() throws IOException {
        App.setRoot("primary");
    }


    @FXML
    private void verHistoricoMesa(){

    }

    private void cargarTableViewMesas() {


        //obtener una lista con todas las mesas
        LinkedList<Mesa> mesas = obtenerDatosMesaActual();

        tableViewMesas.getColumns().clear();


        TableColumn<Producto, Integer> colidMesa = new TableColumn<>("MESA ID");
        TableColumn<Producto, String> colNombreMesa = new TableColumn<>("NOMBRE MESA");
        TableColumn<Producto, Double> colPrecioTotal = new TableColumn<>("TOTAL MESA");


        colidMesa.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreMesa.setCellValueFactory(new PropertyValueFactory<>("nombreMesa"));
        colPrecioTotal.setCellValueFactory(new PropertyValueFactory<>("totalGastado"));

        tableViewMesas.getColumns().addAll(colidMesa, colNombreMesa, colPrecioTotal);

        ObservableList<Mesa> mesasObservableList = FXCollections.observableArrayList(mesas);
        tableViewMesas.setItems(mesasObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTableViewMesas();
    }
}

package org.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.example.models.Mesa;
import org.example.models.Pedido;
import org.example.models.Producto;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static org.example.PrimaryController.nombreMesa;
import static org.example.db.DBHelper.*;

public class HistoricoController implements Initializable {
public static int idMesaHistorico;

@FXML
private TableView tableViewMesas;
@FXML
private Button generarHistoricoID;

    @FXML
    private void volverMenuPrincipal() throws IOException {
        App.setRoot("primary");
    }


    @FXML
    private void verHistoricoMesa() throws IOException {

        App.setRoot("historicomesa");
    }

    private void cargarTableViewMesas() {
        LinkedList<Mesa> mesas = obtenerDatosMesaActual();

        tableViewMesas.getColumns().clear();

        TableColumn<Mesa, Integer> colidMesa = new TableColumn<>("MESA ID");
        TableColumn<Mesa, String> colNombreMesa = new TableColumn<>("NOMBRE MESA");
        TableColumn<Mesa, Double> colPrecioTotal = new TableColumn<>("TOTAL MESA");

        colidMesa.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreMesa.setCellValueFactory(new PropertyValueFactory<>("nombreMesa"));
        colPrecioTotal.setCellValueFactory(new PropertyValueFactory<>("totalGastado"));

        tableViewMesas.getColumns().addAll(colidMesa, colNombreMesa, colPrecioTotal);
        ObservableList<Mesa> mesasObservableList = FXCollections.observableArrayList(mesas);
        tableViewMesas.setItems(mesasObservableList);


        tableViewMesas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Mesa selectedMesa = (Mesa) newSelection;

                idMesaHistorico = selectedMesa.getId();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTableViewMesas();
        generarHistoricoID.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{

                    InputStream reportFile = getClass().getResourceAsStream("/org/example/mostrar_reporte_mesas.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);
                    String url = "jdbc:mysql://localhost:3306/bar_interface";
                    String username = "root";
                    String password = "";
                    Connection connection = DriverManager.getConnection(url, username, password);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

                    JasperViewer.viewReport(jasperPrint, false);

                    String pdfFilePath = "informe_mesas.pdf";
                    JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);
                }catch (Exception e){
                e.printStackTrace();
                }
            }
        });
    }
}

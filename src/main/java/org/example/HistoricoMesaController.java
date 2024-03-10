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

import static org.example.HistoricoController.idMesaHistorico;
import static org.example.PrimaryController.nombreMesa;
import static org.example.db.DBHelper.*;

public class HistoricoMesaController  implements Initializable {
    private int idMesa;

    @FXML
    private TableView tableViewMesas;
    @FXML
    private void volverMenuHistorico() throws IOException {
        App.setRoot("historico");
    }


    private void cargarTableViewProductos() {

        String mesaActual = nombreMesa;
        Mesa mesa = obtenerDatosMesaActual(mesaActual);

        LinkedList<Pedido> pedidos = obtenerPedidosMesa(idMesa);
        LinkedList<Producto> productos = obtenerProductosMesaActual(pedidos);

        tableViewMesas.getColumns().clear();


        TableColumn<Producto, String> colNombreProducto = new TableColumn<>("NOMBRE PRODUCTO");
        TableColumn<Producto, String> colCategoriaProducto = new TableColumn<>("CATEGORIA PRODUCTO");
        TableColumn<Producto, Double> colPrecioProducto = new TableColumn<>("PRECIO PRODUCTO");


        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colCategoriaProducto.setCellValueFactory(new PropertyValueFactory<>("categoriaProducto"));
        colPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));

        tableViewMesas.getColumns().addAll(colNombreProducto, colCategoriaProducto, colPrecioProducto);

        ObservableList<Producto> productosObservableList = FXCollections.observableArrayList(productos);
        tableViewMesas.setItems(productosObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idMesa = idMesaHistorico;
        cargarTableViewProductos();
    }

}

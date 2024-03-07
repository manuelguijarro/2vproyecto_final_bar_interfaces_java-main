package org.example;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.*;
import org.example.models.Mesa;
import org.example.models.Pedido;
import org.example.models.Producto;

import static org.example.PrimaryController.nombreMesa;
import static org.example.db.DBHelper.*;

public class SecondaryController implements Initializable {

@FXML
private Text textoTotalMesa;


@FXML
private TableView tableViewProductos;

    private void aniadirNuevoPedido(String productoNombre) {
        String mesaActual = nombreMesa;
        Producto producto = obtenerDatosProducto(productoNombre);
        Mesa mesa = obtenerDatosMesaActual(mesaActual);

        Pedido pedido = new Pedido(mesa.getId(), producto.getId());
        if (aniadirPedidoActualDB(pedido)) {

            LinkedList<Pedido> pedidos = obtenerPedidosMesaActual(mesa.getId());

            LinkedList<Producto> productos = obtenerProductosMesaActual(pedidos);
            Double totalMesaActual = 0.0;
            for (Producto p : productos) {
                totalMesaActual += p.getPrecioProducto();
            }
            mesa.setTotalGastado(totalMesaActual);
            Double nuevoPrecioActualMesa = actualizarTotalMesaActual(mesa);
            System.out.println("Actualizada");
            textoTotalMesa.setText("TOTAL: " + nuevoPrecioActualMesa + "€");

            cargarTableViewProductos();
        }
    }

    @FXML
    private void btnRefrescoCocaCola() {
        aniadirNuevoPedido("Coca-Cola");
    }

    @FXML
    private void btnRefrescoNestea() {
        aniadirNuevoPedido("Nestea");
    }

    @FXML
    private void btnRefrescoFantaNaranja() {
        aniadirNuevoPedido("Fanta Naranja");
    }
    @FXML
    private void btnRefrescoFantaLimon() {
        aniadirNuevoPedido("Fanta Limon");
    }

    @FXML
    private void btnRefrescoPepsi() {
        aniadirNuevoPedido("Pepsi");
    }

    @FXML
    private void volverMenuPrincipal() throws IOException {
        App.setRoot("primary");
    }




    @FXML
    private void cobrarMesa() throws JRException, SQLException {
        String mesaActual = nombreMesa;
        Mesa mesa = obtenerDatosMesaActual(mesaActual);

        crearMesaHistoricoDB(mesa.getNombreMesa(),mesa.getTotalGastado());

        reiniciarMesaActual();
    }

    @FXML
    private void reiniciarMesaActual(){
        String mesaActual = nombreMesa;
        String idMesaActual = mesaActual.split("_")[1];

        if (borrarPedidosMesaActual(Integer.parseInt(idMesaActual))){

            actualizarPrecioTotalMesaActual(Integer.parseInt(idMesaActual));
            cargarTableViewProductos();
        }
    }
    private void cargarTableViewProductos() {
        String mesaActual = nombreMesa;
        Mesa mesa = obtenerDatosMesaActual(mesaActual);

        LinkedList<Pedido> pedidos = obtenerPedidosMesaActual(mesa.getId());
        LinkedList<Producto> productos = obtenerProductosMesaActual(pedidos);
        textoTotalMesa.setText("TOTAL: " + mesa.getTotalGastado() + "€");

        tableViewProductos.getColumns().clear();


        TableColumn<Producto, String> colNombreProducto = new TableColumn<>("NOMBRE PRODUCTO");
        TableColumn<Producto, String> colCategoriaProducto = new TableColumn<>("CATEGORIA PRODUCTO");
        TableColumn<Producto, Double> colPrecioProducto = new TableColumn<>("PRECIO PRODUCTO");


        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colCategoriaProducto.setCellValueFactory(new PropertyValueFactory<>("categoriaProducto"));
        colPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));

        tableViewProductos.getColumns().addAll(colNombreProducto, colCategoriaProducto, colPrecioProducto);

        ObservableList<Producto> productosObservableList = FXCollections.observableArrayList(productos);
        tableViewProductos.setItems(productosObservableList);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTableViewProductos();
    }
}
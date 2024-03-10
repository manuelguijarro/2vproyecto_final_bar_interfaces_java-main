package org.example.db;

import org.example.models.Mesa;
import org.example.models.Pedido;
import org.example.models.Producto;

import java.sql.*;
import java.util.LinkedList;

public class DBHelper {
    public static Producto obtenerDatosProducto(String nombreProducto) {

        Producto producto = new Producto();
        try {
            Connection conexion = obtenerConexionDB();

            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Productos WHERE nombre_producto='" + nombreProducto + "';");
            while (resultSet.next()) {
                // Aquí puedes procesar cada fila de resultados
                int idProducto = resultSet.getInt("id_producto");
                String categoriaProducto = resultSet.getString("categoria_producto");
                Double precioProducto = resultSet.getDouble("precio_producto");
                producto.setId(idProducto);
                producto.setNombreProducto(nombreProducto);
                producto.setCategoriaProducto(categoriaProducto);
                producto.setPrecioProducto(precioProducto);
            }
            System.out.println(producto);

            conexion.close();
            return producto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Mesa obtenerDatosMesaActual(String nombreMesaActual) {

        Mesa mesa = new Mesa();
        try {
            Connection conexion = obtenerConexionDB();

            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Mesas_Actuales WHERE nombre_mesa_actual='" + nombreMesaActual + "';");
            while (resultSet.next()) {

                int idMesaActual = resultSet.getInt("id_mesa_actual");
                Double totalGastadoMesa = resultSet.getDouble("total_gastado");
                mesa.setId(idMesaActual);
                mesa.setNombreMesa(nombreMesaActual);
                mesa.setTotalGastado(totalGastadoMesa);
            }
            System.out.println(mesa);

            conexion.close();
            return mesa;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static LinkedList<Mesa> obtenerDatosMesaActual() {

        LinkedList<Mesa>mesas = new LinkedList<>();
        try {
            Connection conexion = obtenerConexionDB();

            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Mesas;");
            while (resultSet.next()) {

                int idMesaActual = resultSet.getInt("id_mesa");
                String nombreMesaActual = resultSet.getString("nombre_mesa");
                Double totalGastadoMesa = resultSet.getDouble("total_gastado");
                mesas.add(new Mesa(idMesaActual,nombreMesaActual,totalGastadoMesa));
            }


            conexion.close();
            return mesas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean aniadirPedidoActualDB(Pedido pedidoActual) {

        try {
            Connection conexion = obtenerConexionDB();

            String sql = "INSERT INTO Pedidos_Actuales (id_mesa_actual, id_producto) VALUES (?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setInt(1, pedidoActual.getIdMesaActual());
            preparedStatement.setInt(2, pedidoActual.getIdProducto());

            int filasInsertadas = preparedStatement.executeUpdate();

            conexion.close();

            return filasInsertadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean crearPedidoHistoricoDB(LinkedList<Producto>productos, int mesaId) {
        int filasInsertadas=0;
        for (Producto producto:productos) {
            try {
                Connection conexion = obtenerConexionDB();

                String sql = "INSERT INTO Pedido (id_mesa, id_producto) VALUES (?, ?)";
                PreparedStatement preparedStatement = conexion.prepareStatement(sql);

                preparedStatement.setInt(1, mesaId);
                preparedStatement.setInt(2, producto.getId());

                 filasInsertadas = preparedStatement.executeUpdate();

                conexion.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return filasInsertadas > 0;
    }

    public static LinkedList<Pedido> obtenerPedidosMesaActual(int mesaActualID) {

        LinkedList<Pedido> pedidos = new LinkedList<>();
        try {
            Connection conexion = obtenerConexionDB();

            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Pedidos_Actuales WHERE id_mesa_actual='" + mesaActualID + "';");
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                int idProducto = resultSet.getInt("id_producto");

                pedido.setIdProducto(idProducto);
                pedido.setIdMesaActual(mesaActualID);
                pedidos.add(pedido);
            }


            conexion.close();
            return pedidos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static LinkedList<Pedido> obtenerPedidosMesa(int mesaID) {

        LinkedList<Pedido> pedidos = new LinkedList<>();
        try {
            Connection conexion = obtenerConexionDB();

            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM Pedido WHERE id_mesa = ?");
            statement.setInt(1, mesaID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                int idPedido = resultSet.getInt("id_pedido");
                int idProducto = resultSet.getInt("id_producto");
                // Agregar más columnas según la estructura de tu tabla Pedido

                pedido.setIdMesaActual(mesaID);
                pedido.setIdProducto(idProducto);
                // Agregar más setters según la estructura de tu clase Pedido
                pedidos.add(pedido);
            }

            conexion.close();
            return pedidos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean borrarPedidosMesaActual(int idMesaActual) {

        try {
            Connection conexion = obtenerConexionDB();

            // Creamos la sentencia SQL para borrar los pedidos de la mesa actual
            String sql = "DELETE FROM Pedidos_Actuales WHERE id_mesa_actual=?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, idMesaActual);

            // Ejecutamos la sentencia SQL y obtenemos el número de filas afectadas
            int filasBorradas = preparedStatement.executeUpdate();

            conexion.close();

            // Si al menos una fila fue borrada, retornamos verdadero
            return filasBorradas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si ocurre una excepción o no se borra ninguna fila, retornamos falso
        return false;
    }
    public static boolean borrarProductoEspecifico(int idMesaActual, int idProducto) {

        try {
            Connection conexion = obtenerConexionDB();

            // Creamos la sentencia SQL para borrar el producto específico
            String sql = "DELETE FROM Pedidos_Actuales WHERE id_mesa_actual=? AND id_producto=? LIMIT 1";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, idMesaActual);
            preparedStatement.setInt(2, idProducto);

            // Ejecutamos la sentencia SQL y obtenemos el número de filas afectadas
            int filasBorradas = preparedStatement.executeUpdate();

            conexion.close();

            // Si se borró una fila, retornamos verdadero
            return filasBorradas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si ocurre una excepción o no se borra ninguna fila, retornamos falso
        return false;
    }
    public static boolean actualizarPrecioTotalMesaActual(int idMesa) {

        try {
            Connection conexion = obtenerConexionDB();

            // Crear la sentencia SQL para actualizar el precio total de la mesa
            String sql = "UPDATE Mesas_Actuales SET total_gastado=? WHERE id_mesa_actual=?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setDouble(1, 0.0);
            preparedStatement.setInt(2, idMesa);

            // Ejecutar la sentencia SQL y obtener el número de filas afectadas
            int filasActualizadas = preparedStatement.executeUpdate();

            conexion.close();

            // Si al menos una fila fue actualizada, retornamos verdadero
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si ocurre una excepción o no se actualiza ninguna fila, retornamos falso
        return false;
    }
    private static Connection obtenerConexionDB(){
        String url = "jdbc:mysql://localhost:3306/bar_interface";
        String usuario = "root";
        String contrasena = "";
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            return conexion;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static LinkedList<Producto> obtenerProductosMesaActual(LinkedList<Pedido> pedidos) {

        LinkedList<Producto> productos = new LinkedList<>();
        for (Pedido pedido : pedidos) {
            try {
                Connection conexion = obtenerConexionDB();

                Statement statement = conexion.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Productos WHERE id_producto='" + pedido.getIdProducto() + "';");
                while (resultSet.next()) {
                    Producto producto = new Producto();
                    // Aquí puedes procesar cada fila de resultados
                    int idProducto = resultSet.getInt("id_producto");
                    String nombreProducto = resultSet.getString("nombre_producto");
                    String categoriaProducto = resultSet.getString("categoria_producto");
                    Double precioProducto = resultSet.getDouble("precio_producto");
                    producto.setId(idProducto);
                    producto.setNombreProducto(nombreProducto);
                    producto.setCategoriaProducto(categoriaProducto);
                    producto.setPrecioProducto(precioProducto);
                    productos.add(producto);
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productos;
    }

    public static double actualizarTotalMesaActual(Mesa mesa) {

        try {
            Connection conexion = obtenerConexionDB();

            // Buscamos el total gastado actualmente en la mesa con el ID proporcionado
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT total_gastado FROM Mesas_Actuales WHERE id_mesa_actual='" + mesa.getId() + "';");


            // Sumamos el total gastado actual con el total del nuevo pedido
            double nuevoTotalGastado = mesa.getTotalGastado();

            // Actualizamos el total gastado en la mesa actual
            String sql = "UPDATE Mesas_Actuales SET total_gastado=? WHERE id_mesa_actual=?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setDouble(1, nuevoTotalGastado);
            preparedStatement.setInt(2, mesa.getId());
            int filasActualizadas = preparedStatement.executeUpdate();

            conexion.close();

            // Si al menos una fila fue actualizada correctamente, retornamos verdadero
            return nuevoTotalGastado;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0.0;
    }

    public static Mesa crearMesaHistoricoDB(String nombreMesa, double totalGastado) {

        Mesa mesa = null;
        try {
            Connection conexion = obtenerConexionDB();

            // Crear la sentencia SQL para insertar una nueva mesa
            String sqlInsert = "INSERT INTO Mesas (nombre_mesa, total_gastado) VALUES (?, ?)";
            PreparedStatement preparedStatementInsert = conexion.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            preparedStatementInsert.setString(1, nombreMesa);
            preparedStatementInsert.setDouble(2, totalGastado);

            // Ejecutar la sentencia SQL de inserción
            preparedStatementInsert.executeUpdate();

            // Obtener el ID de la mesa recién insertada
            ResultSet resultSet = preparedStatementInsert.getGeneratedKeys();
            int idMesa = -1;
            if (resultSet.next()) {
                idMesa = resultSet.getInt(1);
            }

            // Crear la sentencia SQL para obtener la última mesa insertada
            String sqlSelect = "SELECT * FROM Mesas WHERE id_mesa = ?";
            PreparedStatement preparedStatementSelect = conexion.prepareStatement(sqlSelect);
            preparedStatementSelect.setInt(1, idMesa);

            // Ejecutar la sentencia SQL de selección
            ResultSet resultSetSelect = preparedStatementSelect.executeQuery();

            // Verificar si hay resultados y crear el objeto Mesa correspondiente
            if (resultSetSelect.next()) {
                String nombreMesaInsertada = resultSetSelect.getString("nombre_mesa");
                double totalGastadoInsertado = resultSetSelect.getDouble("total_gastado");
                mesa = new Mesa(idMesa, nombreMesaInsertada, totalGastadoInsertado);
            }

            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesa;
    }
}

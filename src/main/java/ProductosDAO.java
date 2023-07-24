import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosDAO {

    public static final Logger logger = LogManager.getLogger(ProductosDAO.class);

    public void registrarProductos(ArrayList<Producto> productos) throws ProductoException {
        JDBCManager jdbcManager = new JDBCManager();

        try (Connection connection = jdbcManager.recuperarConexion()){
            for (Producto producto : productos) {
                String sqlInsert = "INSERT INTO productospi (id, nombre, descripcion, precio, fechaAlta) " +
                        "VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
                insertStatement.setInt(1, producto.getId());
                insertStatement.setString(2, producto.getNombre());
                insertStatement.setString(3, producto.getDescripcion());
                insertStatement.setDouble(4, producto.getPrecio());
                insertStatement.setDate(5, new java.sql.Date(producto.getFechaDeAlta().getTime()));
                insertStatement.execute();
            }
            System.out.println("Productos registrados en la base de datos con exito");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            throw new ProductoException("Ha ocurrido un error al cargar los productos en la base de datos", e);
        }
    }

    public ArrayList<Producto> recuperarProductos() throws ProductoException {
        JDBCManager jdbcManager = new JDBCManager();
        Producto producto = null;
        ArrayList<Producto> productosRecuperados = new ArrayList<>();

        try (Connection connection = jdbcManager.recuperarConexion()){
            String sqlSelect = "SELECT id, nombre, descripcion, precio, fechaAlta FROM productospi";
            PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = selectStatement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setFechaDeAlta(resultSet.getDate("fechaAlta"));
                productosRecuperados.add(producto);
            }
            System.out.println("Productos recuperados de la base de datos con exito");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            throw new ProductoException("Ha ocurrido un problema al recuperar informacion desde la base de datos", e);
        }
        return productosRecuperados;
    }
}

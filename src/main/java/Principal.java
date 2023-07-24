import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Principal {
    public static final Logger logger = LogManager.getLogger(Principal.class);
    public static void main(String[] args) {
        CSVManager csvManager = new CSVManager();
        ProductosDAO productosDAO = new ProductosDAO();

        // Leer los datos desde el archivo CSV
        ArrayList<Producto> nuevosProductos = null;
        try {
            nuevosProductos = csvManager.obtenerProductosDeCSV("C:/Users/Eric/Documents/Productos.csv");
        } catch (ProductoException e) {
            logger.error(e);
            e.printStackTrace();
        }

        // Cargar los productos a la Base de Datos
        try {
            productosDAO.registrarProductos(nuevosProductos);
        } catch (ProductoException e) {
            logger.error(e);
            e.printStackTrace();
        }

        // Obtener los datos desde la Base de Datos
        ArrayList<Producto> productosRecuperados = null;
        try {
            productosRecuperados = productosDAO.recuperarProductos();
        } catch (ProductoException e) {
            logger.error(e);
            e.printStackTrace();
        }

        // Grabar los datos en un nuevo archivo CSV
        try {
            csvManager.generarArchivoCSV(productosRecuperados);
        } catch (ProductoException e) {
            logger.error(e);
            e.printStackTrace();
        }

    }
}

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CSVManager {
    public ArrayList<Producto> obtenerProductosDeCSV (String direccionDelArchivo) throws ProductoException {
        Producto producto;
        ArrayList<Producto> productos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Integer contador = 1;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(direccionDelArchivo))){
            String linea = bufferedReader.readLine();
            while (linea != null) {
                if (contador > 1 && !linea.isBlank()){
                    producto = new Producto();
                    String[] registro = linea.split(",");
                    Integer id = Integer.parseInt(registro[0]);
                    producto.setId(id);
                    producto.setNombre(registro[1]);
                    producto.setDescripcion(registro[2]);
                    Double precio = Double.parseDouble(registro[3]);
                    producto.setPrecio(precio);
                    Date fechaDeAlta = dateFormat.parse(registro[4]);
                    producto.setFechaDeAlta(fechaDeAlta);
                    productos.add(producto);
                }
                linea = bufferedReader.readLine();
                contador++;
            }
            System.out.println("Productos recuperados del archivo CSV con exito");
        } catch (IOException | ParseException e) {
            throw new ProductoException("Ha ocurrido un error al recuperar los datos del archivo especificado", e);
        }
        return productos;
    }

    public void generarArchivoCSV (ArrayList<Producto> productos) throws ProductoException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        File file = new File("C:/Users/Eric/Documents/ProductosGenerado.csv");
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write("ID,NOMBRE,DESCRIPCION,PRECIO,FECHAALTA\n");
            for (Producto producto : productos){
                fileWriter.write(producto.getId() + "," + producto.getNombre() + "," + producto.getDescripcion() +
                         "," + producto.getPrecio() + "," + dateFormat.format(producto.getFechaDeAlta()) + "\n");
            }
            System.out.println("Archivo CSV creado y grabado correctamente");
        } catch (IOException e){
            throw new ProductoException("Ha ocurrido un error al crear y grabar el archivo CSV", e);
        }
    }

}

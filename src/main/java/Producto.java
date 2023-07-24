import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Producto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Date fechaDeAlta;

    private ArrayList<Producto> productos = new ArrayList<Producto>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Date fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        if (!Objects.equals(id, producto.id)) return false;
        if (!Objects.equals(nombre, producto.nombre)) return false;
        if (!Objects.equals(descripcion, producto.descripcion))
            return false;
        if (!Objects.equals(precio, producto.precio)) return false;
        return Objects.equals(fechaDeAlta, producto.fechaDeAlta);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (fechaDeAlta != null ? fechaDeAlta.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Producto {" +
                "id = " + id +
                ", nombre = " + nombre +
                ", descripcion = " + descripcion +
                ", precio = " + precio +
                ", fechaDeAlta = " + fechaDeAlta +
                "}'\n";
    }
}

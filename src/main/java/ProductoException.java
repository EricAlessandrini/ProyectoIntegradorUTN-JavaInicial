public class ProductoException extends Exception{
    public ProductoException (String msg) {
        super(msg);
    }
    public ProductoException (String msg, Throwable e) {
        super(msg, e);
    }
}

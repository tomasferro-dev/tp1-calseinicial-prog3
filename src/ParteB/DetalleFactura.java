package ParteB;

public class DetalleFactura {
    private double cantidad;
    private double subtotal;

    private Factura factura;
    private Articulo articulo;


    public DetalleFactura(Articulo articulo,double cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }
    private double calcularSubtotal() {
        return this.cantidad * articulo.getPrecio();
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;

    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
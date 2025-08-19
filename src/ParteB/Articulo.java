package ParteB;

import java.util.ArrayList;
import java.util.List;

public class Articulo {
    private int codigo;
    private String denominacion;
    private double precio;
    private String unidadMedida;

    private List<DetalleFactura> listaDetallesFactura =  new ArrayList<>();

    public Articulo(int codigo, String denominacion, double precio, String unidadMedida) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.precio = precio;
        this.unidadMedida = unidadMedida;
    }

    public void agregarDetalleFactura(DetalleFactura detalleFactura){
        if (this.listaDetallesFactura == null)this.listaDetallesFactura = new ArrayList<>();
        this.listaDetallesFactura.add(detalleFactura);

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public List<DetalleFactura> getListaDetallesFactura() {
        return listaDetallesFactura;
    }

    public void setListaDetallesFactura(List<DetalleFactura> listaDetallesFactura) {
        this.listaDetallesFactura = listaDetallesFactura;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "codigo=" + codigo +
                ", denominacion='" + denominacion + '\'' +
                ", precio=" + precio +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", listaDetallesFactura=" + listaDetallesFactura +
                '}';
    }
}

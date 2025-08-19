package ParteB;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int numero;
    private String razonSocial;
    private long cuit;

    private List<Factura> ListaFacturas = new ArrayList<>();

    public Cliente(int numero, String razonSocial, long cuit) {
        this.numero = numero;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
    }

    public void agregarFactura (Factura factura){
        if (this.ListaFacturas == null)this.ListaFacturas = new ArrayList<>();
        this.ListaFacturas.add(factura);
        factura.setCliente(this);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public List<Factura> getListaFacturas() {
        return ListaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        ListaFacturas = listaFacturas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "numero=" + numero +
                ", razonSocial='" + razonSocial + '\'' +
                ", cuit=" + cuit +
                ", ListaFacturas=" + ListaFacturas +
                '}';
    }
}

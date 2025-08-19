package ParteB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private String letra;
    private int numero;
    private double recargo;
    private String tipoPago;
    private double totalItems;
    private double totalFinal;
    private LocalDate fecha;

    private Cliente cliente;
    private List<DetalleFactura> listaDetalleFacturas = new ArrayList<>();

    public Factura() {
    }

    public Factura(String letra, int numero, double recargo, String tipoPago, double totalItems, double totalFinal, LocalDate fecha) {
        this.letra = letra;
        this.numero = numero;
        this.recargo = recargo;
        this.tipoPago = tipoPago;
        this.totalItems = totalItems;
        this.totalFinal = totalFinal;
        this.fecha = fecha;
    }

    public Factura(int numero,String tipoPago, LocalDate fecha, Cliente cliente) {
        this.numero = numero;
        this.tipoPago = tipoPago;
        this.fecha = fecha;
        this.cliente = cliente;
       //this.totalItems = totalItems;
    }

    public void agregarDetalleFactura(DetalleFactura detalleFactura){
        if (this.listaDetalleFacturas == null) this.listaDetalleFacturas = new ArrayList<>();
        this.listaDetalleFacturas.add(detalleFactura);
        detalleFactura.setFactura(this);
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public double getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(double totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleFactura> getListaDetalleFacturas() {
        return listaDetalleFacturas;
    }

    public void setListaDetalleFacturas(List<DetalleFactura> listaDetalleFacturas) {
        this.listaDetalleFacturas = listaDetalleFacturas;
    }

    public void calcularTotales() {
        totalItems = listaDetalleFacturas.stream()
                .mapToDouble(DetalleFactura::getSubtotal)
                .sum();

        if (tipoPago.equals("C")) recargo = 0;
        else if (tipoPago.equals("TD")) recargo = totalItems * 0.05;
        else if (tipoPago.equals("TC")) recargo = totalItems * 0.10;

        totalFinal = totalItems + recargo;
    }

    public void mostrarFactura() {
        System.out.println("Cliente: " + cliente.getRazonSocial());
        System.out.println("Fecha: " + fecha);
        System.out.println("Número: " + numero);
        System.out.println("Tipo Pago: " + tipoPago);
        System.out.println("-------------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-10s %-10s\n",
                "Cod", "Denominación", "Precio", "Cantidad", "Subtotal");

        for (DetalleFactura d : listaDetalleFacturas) {
            System.out.printf("%-5d %-15s %-10.2f %-10.2f %-10.2f\n",
                    d.getArticulo().getCodigo(),
                    d.getArticulo().getDenominacion(),
                    d.getArticulo().getPrecio(),
                    d.getCantidad(),
                    d.getSubtotal());
        }

        System.out.println("-------------------------------------------------");
        System.out.println("Total Ítems: " + totalItems);
        System.out.println("Recargo: " + recargo);
        System.out.println("Total Final: " + totalFinal);
    }


    @Override
    public String toString() {
        return "Factura{" +
                "letra='" + letra + '\'' +
                ", numero=" + numero +
                ", recargo=" + recargo +
                ", tipoPago='" + tipoPago + '\'' +
                ", totalItems=" + totalItems +
                ", totalFinal=" + totalFinal +
                ", fecha=" + fecha +
                ", cliente=" + cliente;
    }
}

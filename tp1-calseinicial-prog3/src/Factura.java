import java.util.Arrays;

public class Factura {
    private String fecha;
    private long nroFactura;
    private String razonSocial; //(Cliente)
    private long cuitCliente;// (Cliente)
    private String tipoPago;
    private double montoTotalItems;
    private double recargo;
    private double montoFinal;
    String[][] itemsFactura;
    int contadorItems = 0;

    public Factura() {

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(long nroFactura) {
        this.nroFactura = nroFactura;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(long cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public double getMontoTotalItems() {
        return montoTotalItems;
    }

    public void setMontoTotalItems(double montoTotalItems) {
        this.montoTotalItems = montoTotalItems;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public void addItems (String item) {
        if (contadorItems < itemsFactura.length) {
            itemsFactura[contadorItems][0] = item;
            contadorItems++;
        }
    }

    public void removeItem (String item) {
        boolean eliminado = false;
        for (int i = 0; i<contadorItems; i++) {
            if (itemsFactura[i][0].equals(item)) {
                itemsFactura[i][0] = itemsFactura[i+1][0];
                eliminado = true;
                for (int j = i+1; j<contadorItems; j++ ) {
                    if (itemsFactura[j+1][0] != null) {
                        itemsFactura[j][0] = itemsFactura[j+1][0];
                    }
                }
                contadorItems--;
            }
        }
    }

    public void mostrarItems () {
        for (int i = 0; i < contadorItems; i++) {
            System.out.println("Producto: " + itemsFactura[i][0]);
        }
    }

    public String[][] getItemsFactura() {
        return itemsFactura;
    }

    public int getContadorItems() {
        return contadorItems;
    }

    public void setItemsFactura(int cantidadItems) {
        this.itemsFactura = new String[cantidadItems][5];
    }

    public void setContadorItems(int contadorItems) {
        this.contadorItems = contadorItems;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "fecha='" + fecha + '\'' +
                ", nroFactura=" + nroFactura +
                ", razonSocial='" + razonSocial + '\'' +
                ", cuitCliente=" + cuitCliente +
                ", tipoPago='" + tipoPago + '\'' +
                ", montoTotalItems=" + montoTotalItems +
                ", recargo=" + recargo +
                ", montoFinal=" + montoFinal +
                ", itemsFactura=" + Arrays.toString(itemsFactura) +
                ", contadorItems=" + contadorItems +
                '}';
    }
}

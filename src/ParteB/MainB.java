package ParteB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainB {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Array original (del punto A)
        String [][] articulosArray = {
                {"100","Azucar", "20", "U"},
                {"101","Leche" , "30" , "U"},
                {"102","Aceite", "50" , "U"},
                {"103","Sal", "45" , "U"},
                {"104","Fideos", "15" , "U"},
                {"105","Arroz", "28" , "U"},
                {"106","Galletas", "26" , "U"},
                {"107","Carne Molida", "220", "Kg"},
                {"108","Shampoo", "42" , "U"},
                {"109","Queso Mantecoso", "178" , "Kg"},
                {"110","Jamon Cocido", "320" , "Kg"},
                {"111","Naranjas", "80" , "Kg"}
        };

        // Convertimos el array a objetos de tipo Articulo
        List<Articulo> listaArticulos = listarArticulos(articulosArray);

        // Mostramos los artículos disponibles
        mostrarLista(listaArticulos);

        // Creamos cliente
        Cliente cliente = crearCliente(sc);

        // Creamos factura
        Factura factura = crearFactura(sc, cliente);

        // Cantidad de líneas de detalle a cargar
        int cantLineas = (int) validarCantidadArticulos(sc);

        // Cargar artículos en la factura
        for (int i = 0; i < cantLineas; i++) {
            System.out.println("Ingrese código de artículo: ");
            int codigo = Integer.parseInt(sc.nextLine());

            Articulo elegido = null;
            for (Articulo a : listaArticulos) {
                if (a.getCodigo() == codigo) {
                    elegido = a;
                    break;
                }
            }

            if (elegido == null) {
                System.out.println("Código inválido, intente de nuevo.");
                i--;
                continue;
            }

            System.out.println("Ingrese cantidad (" + elegido.getUnidadMedida() + "): ");
            double cantidad = (elegido.getUnidadMedida().equals("U"))
                    ? Integer.parseInt(sc.nextLine())   // si es por unidad  entero
                    : Double.parseDouble(sc.nextLine()); // si es por kilo  double

            DetalleFactura detalle = new DetalleFactura(elegido, cantidad);
            factura.agregarDetalleFactura(detalle);
        }

        // Calculamos totales y mostramos la factura
        factura.calcularTotales();
        factura.mostrarFactura();
    }

    //metodos

    public static List<Articulo> listarArticulos(String[][] articulosArray){
        List<Articulo> articulos = new ArrayList<>();

        for (String[] fila : articulosArray){
            int codigo = Integer.parseInt(fila[0]);
            String nombre = fila[1];
            double precio = Double.parseDouble(fila[2]);
            String unidad = fila[3];

            Articulo articulo = new Articulo(codigo, nombre, precio, unidad);
            articulos.add(articulo);
        }

        return articulos;
    }

    public static void mostrarLista(List<Articulo> listaArticulos){
        System.out.println("\n---------- LISTA DE ARTÍCULOS ----------");
        System.out.printf("%-10s %-20s %-10s %-15s%n",
                "Código", "Denominación", "Precio", "Unidad");

        for (Articulo a : listaArticulos) {
            System.out.printf("%-10d %-20s %-10.2f %-15s%n",
                    a.getCodigo(), a.getDenominacion(), a.getPrecio(), a.getUnidadMedida());
        }
        System.out.println("----------------------------------------");
    }

    public static Cliente crearCliente(Scanner sc){
        System.out.println("Ingrese número de cliente: ");
        int numero = Integer.parseInt(sc.nextLine());
        System.out.println("Ingrese razón social: ");
        String razonSocial = sc.nextLine();
        System.out.println("Ingrese CUIT: ");
        long cuit = Long.parseLong(sc.nextLine());

        return new Cliente(numero, razonSocial, cuit);
    }

    public static String validarTipoPago(Scanner sc){
        String tipoPagoIngresado;
        boolean valido = false;

        do {
            System.out.println("Ingrese tipo de pago: ");
            System.out.println("C  : CONTADO");
            System.out.println("TC : TARJETA DE CRÉDITO");
            System.out.println("TD : TARJETA DE DÉBITO");
            tipoPagoIngresado = sc.nextLine().toUpperCase();

            switch (tipoPagoIngresado){
                case "C":
                case "TC":
                case "TD":
                    valido = true;
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (!valido);

        return tipoPagoIngresado;
    }

    public static Factura crearFactura(Scanner sc, Cliente cliente){
        System.out.println("Creando factura...");
        System.out.println("Ingrese fecha: ");
        System.out.print("Día: ");
        int dia = Integer.parseInt(sc.nextLine());
        System.out.print("Mes: ");
        int mes = Integer.parseInt(sc.nextLine());
        System.out.print("Año: ");
        int anio = Integer.parseInt(sc.nextLine());
        LocalDate fechaFactura = LocalDate.of(anio, mes, dia);

        System.out.println("Ingrese nro de factura: ");
        int numeroFactura = Integer.parseInt(sc.nextLine());

        String tipoPagoFactura = validarTipoPago(sc);

        return new Factura(numeroFactura, tipoPagoFactura, fechaFactura, cliente);
    }

    public static double validarCantidadArticulos(Scanner sc){
        double cant;
        do {
            System.out.println("Ingrese la cantidad de artículos a facturar: ");
            cant = Double.parseDouble(sc.nextLine());
            if (cant <= 0){
                System.out.println("La cantidad debe ser mayor a cero.");
            }
        } while (cant <= 0);

        return cant;
    }
}
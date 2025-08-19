import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Factura factura1 = new Factura();
        CalculoFactura calculoFactura = new CalculoFactura();

        while (true) {
            System.out.println("Creando factura...");
            System.out.println("Ingrese fecha de la factura: ");
            factura1.setFecha(sc.nextLine());
            System.out.println("Ingrese el numero de la factura: ");
            factura1.setNroFactura(sc.nextLong());
            sc.nextLine();
            System.out.println("Ingrese la razon social del cliente: ");
            factura1.setRazonSocial(sc.nextLine());
            System.out.println("Ingrese el CUIT del cliente: ");
            factura1.setCuitCliente(sc.nextLong());
            System.out.println("Ingrese el tipo de pago (C/TC/TD)");
            String tipoDePago;
            sc.nextLine();
            while (true) {
                tipoDePago = sc.nextLine();
                if (
                        !tipoDePago.equalsIgnoreCase("c") &&
                                !tipoDePago.equalsIgnoreCase("tc") &&
                                !tipoDePago.equalsIgnoreCase("td")) {
                    System.out.println("Por favor, ingrese un tipo de pago aceptable (C/TC/TD: ");
                } else {
                    System.out.println("Ha seleccionado tipo de pago: " + tipoDePago);
                    factura1.setTipoPago(tipoDePago);
                    break;
                }
            }
            System.out.println("Ingrese la cantidad total de items: ");
            int cantidadItems;
            while (true) {
                cantidadItems = sc.nextInt();
                if (cantidadItems <= 0) {
                    System.out.println("La cantidad no puede ser 0 o menos. Ingrese otra vez: ");
                } else {
                    System.out.println("Cantidad de items: " + cantidadItems);
                    factura1.setItemsFactura(cantidadItems);
                    break;
                }
            }
            System.out.println("-----------------Articulos a facturar------------------------ ");

            while (factura1.getContadorItems() < cantidadItems) {
                System.out.println("Ingrese el código del artículo: ");
                String codigoArticulo = sc.nextLine();

                String[][] catalogo = calculoFactura.articulos;
                boolean encontrado = false;

                for (int i = 0; i < catalogo.length; i++) {
                    if (catalogo[i][0].equals(codigoArticulo)) {
                        encontrado = true;

                        String nombre = catalogo[i][1];
                        double precio = Double.parseDouble(catalogo[i][2]);
                        String unidad = catalogo[i][3];

                        System.out.println("Artículo encontrado: " + nombre + " - $" + precio + " por " + unidad);

                        double cantidad = 0;
                        boolean cantidadValida = false;

                        while (!cantidadValida) {
                            System.out.println("Ingrese la cantidad a facturar (" + unidad + "): ");
                            String inputCantidad = sc.nextLine();

                            if (unidad.equalsIgnoreCase("U")) {
                                if (inputCantidad.contains(".")) {
                                    System.out.println("Debe ingresar un número entero, no puede contener punto.");
                                } else {
                                    try {
                                        cantidad = Integer.parseInt(inputCantidad);
                                        if (cantidad <= 0) {
                                            System.out.println("La cantidad debe ser mayor a 0.");
                                        } else {
                                            cantidadValida = true;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Entrada inválida. Intente de nuevo.");
                                    }
                                }
                            } else if (unidad.equalsIgnoreCase("Kg")) {

                                try {
                                    cantidad = Double.parseDouble(inputCantidad);
                                    if (cantidad <= 0) {
                                        System.out.println("La cantidad debe ser mayor a 0.");
                                    } else {
                                        cantidadValida = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida. Intente de nuevo.");
                                }
                            }
                        }

                        double subtotal = cantidad * precio;

                        String[] itemFactura = new String[6];
                        itemFactura[0] = codigoArticulo;
                        itemFactura[1] = nombre;
                        itemFactura[2] = String.valueOf(precio);
                        itemFactura[3] = unidad;
                        itemFactura[4] = String.valueOf(cantidad);
                        itemFactura[5] = String.valueOf(subtotal);

                        factura1.getItemsFactura()[factura1.getContadorItems()] = itemFactura;
                        factura1.setContadorItems(factura1.getContadorItems() + 1);

                        System.out.println("Artículo agregado: " + nombre +
                                " | Cantidad: " + cantidad + " " + unidad +
                                " | Subtotal: $" + subtotal);

                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("El codigo no existe. Intente nuevamente.");
                }
            }

            System.out.println("-------------------------------------------------------------");
            System.out.println("Factura cargada con exito.");


            double montoTotalItems = 0;
            for (int i = 0; i < factura1.getContadorItems(); i++) {
                String[] item = factura1.getItemsFactura()[i];
                double subtotal = Double.parseDouble(item[5]);
                montoTotalItems += subtotal;
            }
            factura1.setMontoTotalItems(montoTotalItems);


            double recargo = 0;
            if (factura1.getTipoPago().equalsIgnoreCase("td")) {
                recargo = montoTotalItems * 0.05;
            } else if (factura1.getTipoPago().equalsIgnoreCase("tc")) {
                recargo = montoTotalItems * 0.10;
            }
            factura1.setRecargo(recargo);


            double montoFinal = montoTotalItems + recargo;
            factura1.setMontoFinal(montoFinal);


            System.out.println("El ticket a pagar es:");
            System.out.println("Cliente: " + factura1.getRazonSocial());
            System.out.println("Fecha: " + factura1.getFecha());
            System.out.println("Numero: " + factura1.getNroFactura());
            System.out.println("Tipo de Pago: " + factura1.getTipoPago().toUpperCase());
            System.out.println();
            System.out.println("Codigo   Denominacion       Precio   Cantidad   Subtotal");
            for (int i = 0; i < factura1.getContadorItems(); i++) {
                String[] item = factura1.getItemsFactura()[i];
                System.out.printf("%-8s %-18s %-8s %-9s %-10s%n",
                        item[0], item[1], item[2], item[4], item[5]);
            }
            System.out.println("-------------------------------------------------------------");
            System.out.println("Total Items: " + montoTotalItems);
            System.out.println("Recargo: " + recargo);
            System.out.println("Total Final: " + montoFinal);

            System.out.println("\n¿Desea cargar otra factura? (S/N): ");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("N")) {
                System.out.println("Programa finalizado.");
                break;
            }

        }


    }

}
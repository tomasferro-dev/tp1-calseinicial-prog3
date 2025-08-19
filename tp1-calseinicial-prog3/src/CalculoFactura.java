public class CalculoFactura {
    String [][] articulos = {
        {"100","Azucar", "20", "U"} ,
        {"101","Leche" , "30" , "U"} ,
        {"102","Aceite", "50" , "U"} ,
        {"103","Sal", "45" , "U"} ,
        {"104"," Fideos", "15" , "U"} ,
        {"105"," Arroz", "28" , "U"} ,
        {"106","Galletas", "26" , "U"} ,
        {"107","Carne Molida", "220" ,"Kg"} ,
        {"108","Shampoo", "42" , "U"} ,
        {"109","Queso Mantecoso", "178" , "Kg"} ,
        {"110","Jamon Cocido", "320" , "Kg"} ,
        {"111","Naranjas", "80" , "Kg"}
    };

    public CalculoFactura() {
    }

    public void getArticulo (String codigo) {
        for (int i = 0; i<articulos.length; i++) {
            if (articulos[i][0].equals(codigo)) {

            }
        }
    }

    public boolean verSiExisteArticulo(String codigo) {
        boolean encontrado = false;
        for (int i = 0; i<articulos.length; i++) {
            if (articulos[i][0].equals(codigo)) {
                encontrado = true;
            }
        }
        return encontrado;
    }

}

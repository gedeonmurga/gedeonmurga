package pe.edu.upeu;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Producto> prot=new ArrayList<>();

        prot.add(new Producto("P001", "televisor",4000,20));
        prot.add(new Producto("P002","parlantes",2000));
        var px=new Producto("P003","celular",3500);
        var cantidad=54.5;
        prot.add(px);


        for (Producto p: prot) {
            System.out.println(p.nombre+"\t"+p.precio+"\t"+p.stock+"\t"+p.igv);

        }
        System.out.println(px.getClass());
        System.out.println(cantidad instanceof double);

    }

}
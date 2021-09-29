package net.xeill.elpuig;

import java.io.File;
import java.util.Scanner;

public class CreaDir {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Introdueix la ruta on vols crear el directori: ");
        String fpath = in.nextLine();

        System.out.println("Introdueix el nom del directori que vols crear: ");
        String fname = in.nextLine();

        File d = new File(fpath+File.separator+fname);
        d.mkdirs();

        if (d.exists()) System.out.println("S0ha creat el directori.");
        else System.out.println("No s'ha pogut crear el diretori.");

    }
}

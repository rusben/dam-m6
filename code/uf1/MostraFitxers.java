package net.xeill.elpuig;

import java.io.File;

public class MostraFitxers {

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            System.out.print(i+" ");
            System.out.println(args[i]);

            File f = new File(args[i]);
            File[] files = f.listFiles();

            for (int j = 0; j < files.length; j++) {
                System.out.print(files[j].isDirectory() ? "d" : "-");
                System.out.print(files[j].canRead() ? "r" : "-");
                System.out.print(files[j].canWrite() ? "w" : "-");
                System.out.print(files[j].canExecute() ? "x" : "-");
                System.out.print(" "+files[j].getName());
                System.out.println();
            }

        }
    }


}

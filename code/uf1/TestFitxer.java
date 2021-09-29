package net.xeill.elpuig;

import java.io.File;

public class TestFitxer {

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {

            File f = new File(args[i]);

            if (f.exists()) {
                System.out.println(f.getAbsolutePath());
                System.out.print(f.isDirectory() ? "d" : "-");
                System.out.print(f.canRead() ? "r" : "-");
                System.out.print(f.canWrite() ? "w" : "-");
                System.out.println(f.canExecute() ? "x" : "-");
                System.out.println(f.isHidden() ? "hidden" : "");
            } else {
                System.out.println("File Not Found!");
            }
        }

    }
}

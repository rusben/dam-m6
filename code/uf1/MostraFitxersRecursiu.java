import java.io.File;

public class MostraFitxersRecursiu {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {

            System.out.print(i + ": ");
            System.out.println(args[i]);

            mostraFitxers(new File(args[i]), 0);
        }
    }

    public static void mostraFitxers(File file, int depth) {

        File[] files = file.listFiles();
        for (File f : files) {
            showInfo(f, depth);

            if (f.isDirectory()) {
                depth++;
                System.out.println(f.getPath());
                mostraFitxers(f, depth);
                depth--;
            }
        }
    }

    public static void showInfo(File file, int depth) {
        for (int i = 0; i < depth ; i++) {
            System.out.print("\t");
        }

        System.out.print(file.isDirectory() ? "d" : "-");
        System.out.print(file.canRead() ? "r" : "-");
        System.out.print(file.canWrite() ? "w" : "-");
        System.out.print(file.canExecute() ? "x" : "-");
        System.out.println(" " + file.getName());
    }
}
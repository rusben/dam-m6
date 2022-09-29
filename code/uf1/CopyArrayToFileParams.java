import java.io.*;

public class CopyArrayToFileParams {

    public static void main(String[] args) {

        String[] words = new String[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            words[i] = args[i];
        }

        File f = new File(args[args.length - 1]);

        saveWordsIntoFile(words, f);
        showWords(f);
    }

    private static void showWords(File f) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveWordsIntoFile(String[] words, File f) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (int i = 0; i < words.length; i++) {
                bw.write(words[i]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

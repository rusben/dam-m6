import java.io.*;

public class ComptaVocalsAlphabet {

    public static void main(String[] args) {

        // args[0] --> conté el fitxer
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
            String line;
            // String alphabet = "abcdefghijklmnopqrstuvwxyz";
            String alphabet = "aeiou";
            // bw.write("a, e, i, o, u");
            bw.write(alphabetToHeader(alphabet));
            bw.newLine();
            while ((line = br.readLine()) != null) {
              //  bw.write(lineToOcurrencesLetter(line));
                bw.write(lineToOcurrencesAlphabet(line, alphabet));
                bw.newLine();
            }
            br.close();
            bw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String alphabetToHeader(String alphabet) {
        String[] x = alphabet.split("");
        return String.join(", ", x);
    }

    public static String lineToOcurrencesLetter(String line) {

        return letterToOcurrences(line, 'a') + ", " +
               letterToOcurrences(line, 'e') + ", " +
               letterToOcurrences(line, 'i') + ", " +
               letterToOcurrences(line, 'o') + ", " +
               letterToOcurrences(line, 'u');
    }

    public static String lineToOcurrencesAlphabet(String line, String alphabet) {
        return alphabetToOcurrences(line, alphabet);
    }

    // Recibe una línea y una letra y dice cuantas veces aparece
    // la letra en la línea
    public static int letterToOcurrences(String line, char letter) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (letter == line.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    // Recibe una línea y una serie de letras y devuelve el número de
    // ocurrencias de cada caracter del String alphabet
    public static String alphabetToOcurrences(String line, String alphabet) {

        String result = "";

        for (int i = 0; i < alphabet.length(); i++) {
            int count = 0;
            for (int j = 0; j < line.length(); j++) {

                if (alphabet.charAt(i) == line.charAt(j)) {
                    count++;
                }
            }

            // Pone una coma menos cuando es la última línea
            result += count + ((i == alphabet.length() - 1) ? "" : ", ");
        }
        return result;
    }
}

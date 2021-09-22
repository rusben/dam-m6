import java.util.Scanner;

public class RepeatStrings {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int i = reader.nextInt();
		for (int j = 0; j < i; j++)
			System.out.println("I love Java.");
		reader.close();
	}
}

import java.util.Scanner;

public class PrintNumber {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
    int num = 0;
    int sum = 0;

    System.out.println("Please enter a number to show its digits");
    num = reader.nextInt();

    System.out.println("Below you can see the digits and the sum of the digits");

    while (num > 0) {
      System.out.println("==>" + num % 10);
      sum += num % 10;
      num = num / 10;
    }

    System.out.println("The sum is: "+ sum);
	}
}

import java.util.Scanner;

public class SuperCalculator {
	public static void main(String[] args) {
    int input, a, b;
    input = a = b = 0;
    Scanner in = new Scanner(System.in);

    System.out.println("The super calculator");
    System.out.println("enter the corrsponding number:");
    System.out.println("1. Add | 2. Substract | 3. Divide | 4. Multiply");

    input = in.nextInt();

    a = in.nextInt();
    b = in.nextInt();

    switch (input) {
      case 1: System.out.println(a + " + " + b + " = " + add(a, b));
        break;
      case 2: System.out.println(a + " - " + b + " = " + substract(a, b));
        break;
      case 3: System.out.println(a + " / " + b + " = " + divide(a, b));
        break;
      case 4: System.out.println(a + " * " + b + " = " + multiply(a, b));
        break;
      default: System.out.println("Your input is invalid.");
    }
  }

  static int add(int lhs, int rhs) { return lhs + rhs; }
  static int substract(int lhs, int rhs) { return lhs - rhs; }
  static int divide(int lhs, int rhs) { return lhs / rhs; }
  static int multiply(int lhs, int rhs) { return lhs * rhs; }
}

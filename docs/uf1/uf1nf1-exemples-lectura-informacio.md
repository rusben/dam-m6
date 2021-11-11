# Programes en Java per a llegir informació introduïda per l'usuari

## Exemple 1
Hi ha diverses maneres d'obtenir informació de l'usuari. Aquí tenim un programa que mitjançant la classe `Scanner` de Java assolirà aquesta tasca. Aquesta classe `Scanner` pertany a `java.util`, per tant la primera línia del programa és `import java.util.Scanner;` que permet a l'usuari llegir valors de diversos tipus a Java.

```java
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
```

Les declaracions dels `import` han d'estar a les primeres línies del programa. Per accedir als mètodes de la classe `Scanner`, creeu un nou objecte d'escàner amb el nom `in`. Ara podem fem servir el seu mètode `next`. El mètode `next` obté la cadena de text que un usuari introdueix al teclat. Depenent de la vostra necessitat, utilitzeu aquest mètode: `reader.nextInt();` per llegir només els números, no el retorn de línia ni res que hi hagi després del número.


## Exemple 2
Fem servir `in.nextLine()` per llegir el text sencer que ha escrit l'usuari.
```java
import java.util.Scanner;

public class GetInputFromUser {
	public static void main(String[] args) {
    int a;
    float b;
    String s;
    Scanner in = new Scanner(System.in);

    System.out.println("Enter a string");
    s = in.nextLine();
    System.out.println("You entered string "+s);
    System.out.println("Enter a integer");
    a = in.nextInt();
    System.out.println("You entered integer "+a);
    System.out.println("Enter a float");
    b = in.nextFloat();
    System.out.println("You entered float"+b);
    in.close();
  }
}

```

## Exemple 3
Aquí el programa demana a l'usuari que introdueixi un número enter amb `in.nextInt()`. Després, el programa imprimeix els dígits del número i la suma dels dígits.

```java
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
```

## Exemple 4
Implementació de la classe `SuperCalculator`.

```java
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
```

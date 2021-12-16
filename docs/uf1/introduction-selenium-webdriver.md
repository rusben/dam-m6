# Introducción a Selenium Webdriver

## Configuración del Webdriver

### Creación de un proyecto maven


1. Creamos un nuevo proyecto maven y añadimos las siguientes dependencias en nuestro fichero `pom.xml`

```xml
<dependencies>
       <dependency>
           <groupId>org.seleniumhq.selenium</groupId>
           <artifactId>selenium-remote-driver</artifactId>
           <version>4.0.0</version>
       </dependency>
       <dependency>
           <groupId>org.seleniumhq.selenium</groupId>
           <artifactId>selenium-java</artifactId>
           <version>4.0.0</version>
       </dependency>
   </dependencies>
```

2. Bajamos el driver específico para nuestro navegador. En este ejemplo utilizaremos el driver de Firefox, para ello bajaremos la última versión del driver, este driver llamado `geckodriver` es un fichero binario compilado para la plataforma en la que se va a ejecutar, en mi caso `Linux`, deberá ser accesible en nuestro proyecto Java y tener propiedades de ejecución.

* https://github.com/mozilla/geckodriver/releases/latest

3. La clase principal de nuestro proyecto, `Main.java` configura la ruta hasta el driver en la propiedad de sistema `webdriver.gecko.driver`

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Main {

  public static void main(String[] args) {
    System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
    FirefoxOptions options = new FirefoxOptions();
    WebDriver driver = new FirefoxDriver(options);

    driver.get("https://elpuig.xeill.net");

    String title = driver.getTitle();
    System.out.println(title);

    driver.close();
  }
}
```

### Explicando el código
Importación de paquetes

Para comenzar, debe importar los siguientes dos paquetes:
* `org.openqa.selenium.WebDriver`: contiene la clase `WebDriver` necesaria para crear una instancia de un nuevo navegador cargado con un controlador específico.
* `org.openqa.selenium.firefox.FirefoxDriver`: contiene la clase `FirefoxDriver` necesaria para crear una instancia de un controlador específico de `Firefox` en el navegador instanciado por la clase WebDriver

Si necesitas acciones más complicadas, como acceder a otra clase, tomar capturas de pantalla del navegador o manipular archivos externos, necesitarás importar más paquetes.

Una clase `FirefoxDriver` sin parámetros significa que nuestro programa `Java` iniciará el perfil predeterminado de `Firefox`. El perfil predeterminado de `Firefox` es similar a iniciar `Firefox` en modo seguro (no se cargan extensiones).

### Creación de instancias de objetos y variables

Normalmente, así es como se crea una instancia de un objeto de controlador.

```java
FirefoxOptions options = new FirefoxOptions();
WebDriver driver = new FirefoxDriver(options);
```

### Iniciar una sesión de navegador

El método `get()` de `WebDriver` se utiliza para iniciar una nueva sesión de navegador y la dirige a la URL que se especifique como parámetro.

```java
driver.get("https://elpuig.xeill.net");
```

### Obtener el título de la página

La clase `WebDriver` tiene el método `getTitle()` que siempre se usa para obtener el título de la página cargada actualmente.

```java
String title = driver.getTitle();
```

### Finalización de una sesión de navegador

El método `close()` se utiliza para cerrar la ventana del navegador.

```java
driver.close();
```

## Referencias
https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/package-summary.html
https://www.guru99.com/first-webdriver-script.html

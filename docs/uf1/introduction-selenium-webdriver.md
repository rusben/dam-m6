# Introducción a Selenium Webdriver
`Selenium Webdriver` es una colección de `API` de código abierto que se utiliza para probar aplicaciones web. La herramienta `Selenium Webdriver` se utiliza para automatizar las pruebas de aplicaciones web para verificar que funcionan como se espera. Es compatible principalmente con navegadores como `Firefox`, `Chrome`, `Opera`, `Safari` e `Internet Explorer`. También permite ejecutar pruebas en varios navegadores.

`WebDriver` también le permite utilizar un lenguaje de programación al crear sus scripts de prueba.

El uso de `WebDriver` no es únicamente para la automatización de pruebas en la web, también nos permite extraer información de forma automatizada. En nuestro caso lo utilizaremos para recopilar datos de diferentes páginas web.

## Configuración del Webdriver
### Creación de un proyecto maven

1. Creamos un nuevo proyecto `maven` y añadimos las siguientes dependencias en nuestro fichero `pom.xml`

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

2. Bajamos el driver específico para nuestro navegador. En este ejemplo utilizaremos el driver de `Firefox`, para ello bajaremos la última versión del driver, este driver llamado `geckodriver` es un fichero binario compilado para la plataforma en la que se va a ejecutar, en mi caso `Linux`, deberá ser accesible en nuestro proyecto `Java` y tener propiedades de ejecución.

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
Para comenzar, debemos importar los siguientes dos paquetes:
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
El método `get()` de `WebDriver` se utiliza para iniciar una nueva sesión de navegador y la dirige a la `URL` que se especifique como parámetro.

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

## Uso de selenium webdriver

### Localització d'elements de la GUI

La localització d'elements a `WebDriver` es fa mitjançant el mètode `findElement(By.locator())` . Es recomana que localitzeu els elements de la GUI mitjançant `IDE` i, un cop identificats correctament, exporteu el codi a `WebDriver`.

Aquí hi ha un codi de mostra de Selenium que localitza un element pel seu identificador. Facebook s'utilitza com a URL base.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Exemple {
  public static void main(String[] args) {
    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    FirefoxOptions options = new FirefoxOptions();
    WebDriver driver = new FirefoxDriver(options);
    
    String baseUrl = "http://www.facebook.com";
    String tagName = "";
        
    driver.get(baseUrl);
    tagName = driver.findElement(By.id("email")).getTagName();
    System.out.println(tagName);
    driver.close();
    System.exit(0);
  }
}
```
Hem utilitzat el mètode `getTagName()` per extreure el nom de l'etiqueta d'aquest element en concret l'identificador del qual és `email`. Quan s'executa, aquest codi hauria de poder identificar correctament el nom de l'etiqueta "entrada" i l'imprimirà a la finestra de la consola del IDE.


Resum per localitzar elements

| Variation |	Description |	Sample |
|-----------|-------------|--------|
| By.className | finds elements based on the value of the “class” attribute | findElement(By.className(“someClassName”)) |
| By.cssSelector | finds elements based on the driver’s underlying CSS Selector engine | findElement(By.cssSelector(“input#email”)) |
| By.id | locates elements by the value of their “id” attribute | findElement(By.id(“someId”)) |
| By.linkText | finds a link element by the exact text it displays | findElement(By.linkText(“REGISTRATION”)) |
| By.name | locates elements by the value of the “name” attribute | findElement(By.name(“someName”)) |
| By.partialLinkText | locates elements that contain the given link text | findElement(By.partialLinkText(“REG”)) |
| By.tagName | locates elements by their tag name | findElement(By.tagName(“div”)) |
| By.xpath | locates elements via XPath | findElement(By.xpath(“//html/body/div/table/tbody/tr/td[2]/table/
tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/ form/table/tbody/tr[5]”)) |

## Ordres comuns
### Instanciació d'elements web

En lloc d'utilitzar la llarga sintaxi `driver.findElement(By.locator())` cada vegada que accediu a un element concret, podem crear-hi una instancia d'un objecte `WebElement`. La classe `WebElement` està continguda al paquet `org.openqa.selenium.*`.

```java
WebElement myElement = driver.findElement(By.id("username"));
myElement.sendKeys("tutorial");
```

### Fent clic a un element

El clic és potser la forma més habitual d'interactuar amb elements web. El mètode `click()` s'utilitza per simular el clic de qualsevol element. L'exemple següent de `Selenium Java` mostra com es va utilitzar `click()` per fer clic al botó `Iniciar sessió`.


```java
driver.findElement(By.name("login")).click();
```

Cal tenir en compte les coses següents quan utilitzeu el mètode `click()`.

* No pren cap paràmetre/argument.
* El mètode espera automàticament que es carregui una pàgina nova si escau.
* L'element a fer clic ha de ser visible (l'alçada i l'amplada no han de ser iguals a zero).

### Ordres get

Les ordres `get` obtenen informació important sobre la pàgina/element. Aquí hi ha algunes ordres importants `get` amb les quals heu d'estar familiaritzats.

| Ordres | Ús |
|--------|----|
| `get()` | <ul><li>Obre automàticament una nova finestra del navegador i recupera la pàgina que especifiqueu entre els seus parèntesis.</li><li>És la contrapartida de l'ordre òpen` de Selenium IDE.</li><li>El paràmetre ha de ser un objecte String.</li></ul> |
| `getTitle()` | <ul><li>No necessita paràmetres</li><li>Obtén el títol de la pàgina actual</li><li>Els espais en blanc inicials i posteriors es retallen</li><li>Retorna una cadena nul·la si la pàgina no té títol</li></ul> |
| `getPageSource()` | <ul><li>No necessita paràmetres</li><li>Retorna el codi font de la pàgina com a valor String</li></ul> |
| `getCurrentUrl()` | <ul><li>No necessita paràmetres</li><li>Obtén la cadena que representa l'URL actual que està mirant el navegador</li></ul> |
| `getText()` | <ul><li>Obtén el text interior de l'element que especifiqueu</li></ul> |


## Referencias
* https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/package-summary.html
* https://www.guru99.com/first-webdriver-script.html

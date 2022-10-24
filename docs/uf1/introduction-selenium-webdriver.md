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
| `By.className` | troba elements basats en el valor de l'atribut `class` | `findElement(By.className(“someClassName”))` |
| `By.cssSelector` | troba elements basats en el motor de selecció CSS ​​subjacent del controlador | `findElement(By.cssSelector(“input#email”))` |
| `By.id` | localitza els elements pel valor del seu atribut `id` | `findElement(By.id(“someId”))` |
| `By.linkText` | troba un element d'enllaç pel text exacte que mostra | `findElement(By.linkText(“REGISTRATION”))` |
| `By.name` |  localitza elements pel valor de l'atribut `nom` | `findElement(By.name(“someName”))` |
| `By.partialLinkText` | localitza els elements que contenen el text de l'enllaç donat | `findElement(By.partialLinkText(“REG”))` |
| `By.tagName` | localitza els elements pel seu nom d'etiqueta | `findElement(By.tagName(“div”))` |
| `By.xpath` | localitza elements mitjançant XPath | `findElement(By.xpath(“//html/body/div/table/tbody/tr/td[2]/table/
tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/ form/table/tbody/tr[5]”))` |

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

### Ordres de navegació
Aquestes ordres us permeten actualitzar, entrar i canviar entre diferents pàgines web.

| Ordres | Ús |
|--------|----|
| `navigate().to()` | <ul><li>Obre automàticament una nova finestra del navegador i recupera la pàgina que especifiqueu entre els seus parèntesis.</li><li>Fa exactament el mateix que el mètode get().</li></ul> |
| `navigate().refresh()` | <ul><li>No necessita paràmetres.</li><li>Actualitza la pàgina actual.</li></ul> |
| `navigate().back()` | <ul><li>No necessita paràmetres.</li><li>Et porta enrere una pàgina a l'historial del navegador.</li></ul> |
| `navigate().forward()` | <ul><li>No necessita paràmetres.</li><li>Us fa avançar una pàgina a l'historial del navegador.</li></ul> |

### Tancar i sortir de finestres del navegador

| Ordres | Ús |
|--------|----|
| close() | <ul><li>No necessita paràmetres.</li><li>Tanca només la finestra del navegador que WebDriver controla actualment.</li></ul> |
| quit() | <ul><li>No necessita paràmetres.</li><li>Tanca totes les finestres que WebDriver ha obert.</li></ul> |

### Waits
Hi ha dos tipus de waits.
* Wait implícit: s'utilitza per establir el temps d'espera predeterminat durant tot el programa
* Wait explícit: s'utilitza per establir el temps d'espera només per a una instància concreta

#### Wait implícit
* És més senzill de codificar que Explicit Waits.
* Normalment es declara a la part d'instanciació del codi.
* Només necessitareu un paquet addicional per importar.

Per començar a utilitzar una espera implícita, hauríeu d'importar aquest paquet al vostre codi.

```java
import java.util.concurrent.TimeUnit;
```

A continuació, a la part d'instanciació del vostre codi, afegiu-ho.

```java
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
```

#### Wait explícit

Les esperes explícites es fan mitjançant les classes `WebDriverWait` i `ExpectedCondition`. Per al següent exemple de `Selenium WebDriver`, esperarem fins a 10 segons perquè un element l'identificador del qual sigui "nom d'usuari" sigui visible abans de passar a la següent ordre. Aquí teniu els passos.

***Pas 1***
Importeu aquests dos paquets:

```java
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
```

***Pas 2***
Declarar una variable `WebDriverWait`. En aquest exemple, utilitzarem `myWaitVar` com a nom de la variable.
```java
WebDriver driver = new FirefoxDriver();
WebDriverWait myWaitVar = new WebDriverWait(driver, 10);
```

***Pas 3***
Utilitzeu `myWaitVar` amb `ExpectedConditions` a les parts on necessiteu que es produeixi l'espera explícita. En aquest cas, utilitzarem l'espera explícita a l'entrada del "nom d'usuari"  abans d'escriure el text "tutorial".

```java
myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
driver.findElement(By.id("username")).sendKeys("tutorial");
```

### Condicions

Els mètodes següents s'utilitzen en operacions condicionals i en bucle:

* `isEnabled()` – s'utilitza quan voleu verificar si un determinat element està habilitat o no abans d'executar una ordre.
* `isDisplayed()` – s'utilitza quan voleu verificar si un determinat element es mostra o no abans d'executar una ordre.
* `isSelected()` – s'utilitza quan voleu verificar si una casella de selecció, botó d'opció o opció en un quadre desplegable està seleccionada. No funciona en altres elements.

### Ús de ExpectedConditions

La classe `ExpectedConditions` ofereix un conjunt més ampli de condicions que podeu utilitzar juntament amb el mètode `until()` de `WebDriverWait`.

A continuació es mostren alguns dels mètodes `ExpectedConditions` més comuns.

* `alertIsPresent()` – espera fins que es mostri un quadre d'alerta.
* `elementToBeClickable()` – Espera fins que un element sigui visible i, al mateix temps, habilitat. El codi Selenium de mostra a continuació esperarà fins que l'element amb `id="username"` es faci visible i s'habiliteu primer abans d'assignar aquest element com a variable `WebElement` anomenada `"txtUserName"`.
* `frameToBeAvailableAndSwitchToIt()` – Espera fins que el marc donat ja estigui disponible i, a continuació, canvia automàticament a ell.

### Captura d'excepcions

Quan s'utilitza `isEnabled()`, `isDisplayed()` i `isSelected()`, `WebDriver` assumeix que l'element ja existeix a la pàgina. En cas contrari, llançarà una `NoSuchElementException`. Per evitar-ho, hauríem d'utilitzar un bloc `try-catch` perquè el programa no s'interrompi.

```java
WebElement txtbox_username = driver.findElement(By.id("username"));
try {
  if(txtbox_username.isEnabled()) {
    txtbox_username.sendKeys("tutorial");
  }
} 
catch(NoSuchElementException nsee) {
  System.out.println(nsee.toString());
}
```

Si utilitzeu esperes explícites, el tipus d'excepció que hauríeu de detectar és la `TimeoutException`.

## Referencias
* https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/package-summary.html
* https://www.guru99.com/first-webdriver-script.html

# Práctica Web Scrapping

## Objetivos
Aplicar los conocimientos adquiridos durante el transcurso de la `UF`, en concreto:
* Lectura y escritura de ficheros `CSV`.
* Lectura y escritura de ficheros `XML`.
* WebScraping mediante `Selenium WebDriver`

## Desarrollo de la pràctica
Cada estudiante debe seleccionar una web de la que extraer datos de forma automatizada. Mediante el uso de `Selenium WebDriver` y las clases y funciones auxiliares que se necesiten, se debe extraer la máxima información "cruda" posible, sin datos de formato.

* Generación de uno o varios documentos `CSV` con la información extraída. La generación del documento `CSV` debe realizarse con la librería `OpenCSV`.
* Generación de uno o varios documentos `XML` con la información extraída. La generación del documento debe realizarse mediante la librería `DOM`. Se valorará el uso de la librería `JAXB`.

## Creación de clases del modelo de datos
La información extraída de la web debe integrarse en un modelo de datos. Las clases del modelo de datos son específicas para cada desarrollo, un modelo de daos más generalista permitirá que sea más adaptable a posibles futuros cambios.
Se debe incluir un diagrama `UML` con las clases que forman el modelo de datos.

## Librerías
En clase hemos generado documentos `CSV` escribiendo directamente el texto plano en el fichero `CSV`. Este procedimiento puede no resultar satisfactorio si los elementos que estamos guardando contienen carácteres como `,`. Para ello utilizaremos librerías que nos gestionen la lectura y escritura dentro del fichero `CSV`, existen diferentes librerías, en concreto usaremos `OpenCSV`.

https://www.baeldung.com/opencsv

## Entrega
* El código debe estar correctamente estructurado y comentado.
* El código deberá estar accesible en un repositorio `github` al menos para el profesor.
* Es imprescindible el uso de `Java Doc` para realizar la documentación.
* La documentación generada deberá estar en formato web y enlazada desde el repositorio.
* El proyecto deberá tener un archivo `README.md` en el que se explique al menos cómo ejecutarlo. Se valorarán la explicación de la estrategia utilizada.

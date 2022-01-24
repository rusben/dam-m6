# Práctica JDBC
## Objetivos
Aplicar los conocimientos adquiridos durante el transcurso de la `UF`, en concreto:
* Uso del JDBC PostgreSQL Driver.
* Realización de consultas, gestión de resultados, modificaciones y procedimientos.
* Gestión de transacciones y mensajes de error.

## Desarrollo de la práctica
Esta práctica es la continuación de la práctica de web scrapping. Una vez extraída la información de la web (Práctica UF1) crearemos una base de datos en la que guardaremos nuestra información.

El proyecto de partida es la `PrácticaACB`. utilizaremos ese proyecto como esqueleto de nuestro nuevo proyecto.

Los pasos a seguir son los siguientes:

* Definir el esquema de la base de datos.
* Creación de una base de datos. (Esta opción se realiza directamente en PostgreSQL)
El resto de opciones deberían realizarse mediante un menú de terminal específicamente creado para nuestra práctica.

* Definir las sentencias de creación de las tablas que guardarán la información.
* Conexión mediante JDBC con la base de datos.
* Manejo de la conexión mediante un menú de terminal que debe tener:
 * Una opción que permita borrar las tablas de la base de datos y su información.
 * Una opción que permita crear las tablas de la base de datos.
 * Una opción que permita poblar masivamente las tablas de la base de datos leyendo los ficheros generados en la primera práctica.
 * Diferentes opciones de consulta sobre la información. Ejemplos:
   - Seleccionar todos los elementos que contengan un texto concreto.
   - Seleccionar todos los elementos que cumplan una condición.
   - Seleccionar elementos concretos.
 * Posibilidad de modificar un registro concreto de información. Ejemplo:
   - Seleccionar un elemento concreto y permitir su modificación.
 * Posibilidad de modificar diferentes registros de información.
 * Posibilidad de eliminar un registro concreto de información.
 * Posibilidad de eliminar un conjunto de registros de información que cumplan un condición.

Los listados dependerán de la información que cada uno ha recogido.


## Entrega
* El código debe estar correctamente estructurado y comentado.
* El código deberá estar accesible en un repositorio `github` al menos para el profesor.
* Es imprescindible el uso de `Java Doc` para realizar la documentación.
* La documentación generada deberá estar en formato web y enlazada desde el repositorio.
* El proyecto deberá tener un archivo `README.md` en el que se explique al menos cómo ejecutarlo. Se valorarán la explicación de la estrategia utilizada.

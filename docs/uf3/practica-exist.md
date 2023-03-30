# Práctica eXist
## Objetivos
Aplicar los conocimientos adquiridos durante el transcurso de la `UF`, en concreto:
* Uso de eXist y XQuery.
* Realización de consultas, gestión de resultados, modificaciones y procedimientos.
* Gestión de transacciones y mensajes de error.

## Desarrollo de la práctica
Una vez extraída la información de la web (Práctica UF1) crearemos una base de datos eXist en la que guardaremos nuestro XML.

El proyecto de partida es la práctica `eXistSample`. utilizaremos ese proyecto como esqueleto de nuestro nuevo proyecto.

Los pasos a seguir son los siguientes:

* Conexión con la base de datos mediante Java.
* Manejo de la conexión mediante un menú de terminal que debe tener:
 * Diferentes opciones que permitan consulta sobre la información. Ejemplos:
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
* El proyecto debe ser un proyecto `maven` o `gradle`.
* El código deberá estar accesible en un repositorio `github` al menos para el profesor.
* Es imprescindible el uso de `Java Doc` para realizar la documentación.
* La documentación generada deberá estar en formato web y enlazada desde el repositorio de `github` en una página web.
* El proyecto deberá tener un archivo `README.md` en el que se explique al menos cómo ejecutarlo. Se valorarán la explicación de la estrategia utilizada.

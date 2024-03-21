# Introducción
Es conocido por todo el mundo que las bases de datos son una parte fundamental de todas las organizaciones, pues en ellas se almacena información que será utilizada por éstas. Además, hay que decir que `XML` es parte del presente y seguramente el futuro de la administración de datos ya que ha permitido romper ciertas barreras y crear un formato estándar para procesar información.
Pues bien, `XML` está provocando la aparición de nuevas tecnologías, entre ellas la aparición de una nueva generación de bases de datos que aunque por ahora se encuentran en una fase de investigación y desarrollo, en un futuro pueden ser una alternativa a las bases de datos relacionales. Son las llamadas `native XML database`, las bases de datos `XML` nativas `BD:XML`.

# Comparativa entre las XML:DB y las bases de datos relacionales
La principal característica de las `XML:DB` es la capacidad de obtener resultados de consultas con formato `XML` y por eso estas bases de datos pertenecen a la categoría `XML-enabled database`.
La organización `XML:DB Initiative for XML Databases` describe una base de datos de este tipo como una: `modelo lógico` para documentos `XML` y almacena y recupera documentos de acuerdo con este modelo.
Todas las bases de datos relacionales son centradas en los Datos, `data-centric databases`, ya que lo que ellas almacenan son datos atómicos. Una `BD:XML`, ni tiene campos, ni almacena datos atómicos ya que lo que ella almacena son documentos `XML`, por lo que a este tipo de bases de datos se les llama bases de datos centradas en documentos, “documento-céntrico databases” .

Varios productos ofrecen diferentes características para las bases de datos nativas XML, y son generalmente estas:
* Son bases de datos y soportan transacciones, acceso multiusuario, lenguajes de consulta, etc.. diseñadas especialmente para almacenar documentos XML
* **Almacenamiento:** La deducción lógica es que una base de datos nativa en `XML` almacena información en formato `XML` pero esto no es sólo una deducción lógica ya que este tipo de bases de datos tiene repositorios con un formato tipo `XML` como puede ser DOM o Infoset. En ese mismo repositorio se almacenan los índices que se generan para cada documento `XML` almacenado. Es decir, almacena el documento `XML` entero en formato texto y proporciona alguna funcionalidad a la base de datos para acceder a él.
* **Almacenamiento de documentos en colecciones:** Las colecciones juegan en las bases de datos nativas el papel de las tablas en las bases de datos relacionales .Los documentos se agrupan en función de la información que contienen en colecciones que en la vez pueden contener otras subcolecciones dentro de ellas.
* **Validación de documentos:** Es necesario que los documentos que se almacenen sean `XML` bien formados.
* **Procesamiento de datos:** El procesamiento de datos en este tipo de bases de datos parece ser muy ágil pero esto no es totalmente así debido al formato jerárquico en el que está almacenada la información. Muchas bases de datos necesitan que se recupere todo el documento `XML`, se actualice en la API XML que se esté utilizando y posteriormente se vuelva a almacenar en el repositorio. Esto se debe a que no existe un lenguaje estándar que permita la actualización, inserción o eliminación de elementos de un documento `XML`. Existe un lenguaje que permite realizar actualizaciones en un documento XML aunque algunos gestores de este tipo de bases de datos no lo soportan, este lenguaje es XUpdate (http://www.xmldb.org/xupdate)
* **Consultas:** Este tipo de bases de datos no utiliza `SQL` como lenguaje de consulta. En lugar de éste utilizan `XPath` o `XQuery`. Algunas bases de datos permiten seleccionar los elementos que tendrán índices mientras que otros indexan todo el contenido del documento. El problema que tienen las bases de datos que sólo permiten búsquedas con `XPath` es que no permiten realizar búsquedas muy complicadas ya que no permiten la ordenación ni el cross join ya que inicialmente `XPath` fue creado inicialmente para realizar búsquedas dentro de un documento y no en una base de datos. Sin embargo hay que decir que todo esto está en continua evolución. Ahora la mayoría de las BD:XML soportan uno o varios lenguajes de consulta. Uno de los más populares es `XQuery`.
* **Indexación XML:** Se debe permitir la creación de índices que aceleren las consultas realizadas frecuentemente.
* **Creación de identificadores únicos:** A cada documento `XML` se le asocia un identificador único por lo que será reconocido dentro del repositorio

# XPath y XQuery
`XPath` y `XQuery` son dos lenguajes de consulta diferentes que se utilizan para acceder y manipular datos en documentos `XML` y otros tipos de datos estructurados. A continuación, se presentan algunas diferencias clave entre `XPath` y `XQuery`:

* **Propósito:** `XPath` se utiliza principalmente para navegar por documentos `XML` y seleccionar elementos o atributos específicos, mientras que `XQuery` se utiliza para realizar consultas complejas en documentos `XML` y otros tipos de datos estructurados.

* **Estructura:** `XPath` está diseñado como un lenguaje de expresión que se utiliza en conjunto con otros lenguajes de programación, como Java o Python, mientras que `XQuery` es un lenguaje de programación completo con su propia sintaxis y estructura.

* **Enfoque:** `XPath` se centra en la selección y navegación de elementos en un documento `XML`, mientras que `XQuery` se enfoca en la selección y transformación de datos en un documento `XML`.

* **Tipos de datos:** `XPath` tiene un conjunto limitado de tipos de datos básicos, como cadenas, números y booleanos, mientras que `XQuery` tiene una amplia variedad de tipos de datos, incluyendo secuencias, objetos y funciones.

* **Soporte para módulos:** `XQuery` permite la creación de módulos reutilizables que se pueden importar en otros archivos `XQuery`, lo que facilita el desarrollo y mantenimiento de código. `XPath` no tiene soporte nativo para módulos.

`XPath` es una herramienta más limitada diseñada para navegar por documentos `XML`, mientras que `XQuery` es una herramienta más completa diseñada para realizar consultas complejas en documentos `XML` y otros tipos de datos estructurados..

`XQuery` y `XPath` son dos lenguajes de consulta diferentes pero relacionados que se utilizan para buscar y manipular datos en documentos `XML`. A continuación, se presentan algunas similitudes y diferencias clave entre `XQuery` y `XPath`:

## Similitudes

* Ambos lenguajes se utilizan para buscar y recuperar datos de documentos `XML`.
* Ambos lenguajes están basados en expresiones `XPath`, que son utilizadas para ubicar elementos específicos dentro de un documento `XML`.
* Ambos lenguajes están diseñados para trabajar con datos estructurados y jerárquicos, lo que los hace ideales para trabajar con documentos `XML`.

## Diferencias

* `XPath` se utiliza principalmente para seleccionar elementos y atributos específicos dentro de un documento `XML`, mientras que `XQuery` se utiliza para realizar consultas más complejas que pueden incluir la selección, filtrado y transformación de datos.
* `XQuery` es un lenguaje más completo y complejo que `XPath`, y puede manejar consultas más complejas que `XPath`.
* `XPath` se utiliza principalmente como parte de otras tecnologías de `XML`, como `XSLT` y `XForms`, mientras que `XQuery` se utiliza como un lenguaje independiente para la consulta de datos `XML`.
* `XPath` utiliza una sintaxis más simple y compacta que `XQuery`, lo que lo hace más fácil de leer y escribir para tareas más simples de búsqueda y selección.
* `XQuery` tiene la capacidad de realizar operaciones sobre múltiples documentos `XML`, mientras que `XPath` solo puede trabajar con un documento `XML` a la vez.

`XPath` se utiliza para seleccionar elementos y atributos específicos dentro de un documento `XML`, mientras que `XQuery` se utiliza para realizar consultas más complejas y para manipular y transformar datos `XML`. Ambos lenguajes se basan en expresiones `XPath`, pero `XQuery` es un lenguaje más completo y complejo que `XPath`, y puede manejar consultas más complejas y operaciones sobre múltiples documentos `XML`


# Colecciones de datos

## Colección de Datos 1: Lista de Empleados

```xml
<employees>
    <employee id="1001">
        <name>John Doe</name>
        <department_id>D001</department_id>
        <salary>60000</salary>
        <years_of_experience>7</years_of_experience>
        <supervisor_id>2001</supervisor_id>
    </employee>
    <employee id="1002">
        <name>Jane Smith</name>
        <department_id>D002</department_id>
        <salary>55000</salary>
        <years_of_experience>4</years_of_experience>
        <supervisor_id>2002</supervisor_id>
    </employee>
    <!-- Otros empleados -->
</employees>
```

Atributos de los empleados:

* id: Identificador único del empleado.
* name: Nombre del empleado.
* department_id: Identificador del departamento en el que trabaja el empleado.
* salary: Salario del empleado.
* years_of_experience: Años de experiencia del empleado.
* supervisor_id: Identificador del supervisor del empleado.

## Colección de Datos 2: Registro de Estudiantes

```xml
<students>
    <student id="1001">
        <name>Emily Johnson</name>
        <course_id>CS101</course_id>
        <grades>
            <subject name="Math">
                <grade>95</grade>
            </subject>
            <!-- Otros sujetos y calificaciones -->
        </grades>
        <absences>2</absences>
        <tutor_id>2001</tutor_id>
    </student>
    <student id="1002">
        <name>Michael Brown</name>
        <course_id>PH101</course_id>
        <grades>
            <subject name="Physics">
                <grade>85</grade>
            </subject>
            <!-- Otros sujetos y calificaciones -->
        </grades>
        <absences>4</absences>
        <tutor_id>2002</tutor_id>
    </student>
    <!-- Otros estudiantes -->
</students>

<courses>
    <course id="CS101">
        <name>Computer Science</name>
        <professor>
            <name>Dr. Smith</name>
        </professor>
        <student_id>1001</student_id>
        <!-- Otros datos del curso -->
    </course>
    <course id="PH101">
        <name>Physics</name>
        <professor>
            <name>Dr. Johnson</name>
        </professor>
        <student_id>1002</student_id>
        <!-- Otros datos del curso -->
    </course>
    <!-- Otros cursos -->
</courses>

<professors>
    <professor id="2001">
        <name>Dr. Adams</name>
        <!-- Otros datos del profesor -->
    </professor>
    <professor id="2002">
        <name>Dr. Davis</name>
        <!-- Otros datos del profesor -->
    </professor>
    <!-- Otros profesores -->
</professors>

```

Atributos de los estudiantes:

* id: Identificador único del estudiante.
* name: Nombre del estudiante.
* course_id: Identificador del curso en el que está inscrito el estudiante.
* grades: Calificaciones del estudiante en diferentes asignaturas.
* absences: Número de ausencias del estudiante.
* tutor_id: Identificador del tutor del estudiante.

## Colección de Datos 3: Registro de Películas

```xml
<movies>
    <movie id="1">
        <title>The Shawshank Redemption</title>
        <genre>Drama</genre>
        <director>Frank Darabont</director>
        <year>1994</year>
        <rating>9.3</rating>
    </movie>
    <movie id="2">
        <title>The Godfather</title>
        <genre>Crime</genre>
        <director>Francis Ford Coppola</director>
        <year>1972</year>
        <rating>9.2</rating>
    </movie>
    <!-- Otros registros de películas -->
</movies>
```

## Colección de Datos 4: Registro de Libros

```xml
<books>
    <book id="1">
        <title>To Kill a Mockingbird</title>
        <author>Harper Lee</author>
        <genre>Fiction</genre>
        <year>1960</year>
        <rating>4.27</rating>
    </book>
    <book id="2">
        <title>1984</title>
        <author>George Orwell</author>
        <genre>Dystopian</genre>
        <year>1949</year>
        <rating>4.18</rating>
    </book>
    <!-- Otros registros de libros -->
</books>
```

## Colección de Datos 5: Registro de Canciones

```xml
<songs>
    <song id="1">
        <title>Bohemian Rhapsody</title>
        <artist>Queen</artist>
        <genre>Rock</genre>
        <year>1975</year>
        <rating>4.9</rating>
    </song>
    <song id="2">
        <title>Thriller</title>
        <artist>Michael Jackson</artist>
        <genre>Pop</genre>
        <year>1982</year>
        <rating>4.8</rating>
    </song>
    <!-- Otros registros de canciones -->
</songs>
```

## Soluciones

### Ejercicios: Registro de Películas

1. Obtener el título de una película específica:

```xpath
//movies/movie[@id="1"]/title/text()
```

2. Listar los directores de las películas de un género determinado:

```xpath
//movies/movie[genre="Drama"]/director/text()
```

3. Encontrar los títulos de las películas que tienen una calificación superior a 8:

```xpath
//movies/movie[number(rating) > 8]/title/text()
```

4. Obtener los años de lanzamiento de las películas dirigidas por un director específico:

```xpath
//movies/movie[director="Francis Ford Coppola"]/year/text()
```

5. Encontrar los géneros de las películas que fueron lanzadas después de 2000:

```xpath
//movies/movie[number(year) > 2000]/genre/text()
```

### Ejercicios: Registro de Libros
 
1. Obtener el autor de un libro específico:

```xpath
//books/book[@id="1"]/author/text()
```

2. Listar los títulos de los libros de un género determinado:

```xpath
//books/book[genre="Dystopian"]/title/text()
```

3. Encontrar los autores de los libros que tienen una calificación superior a 4.5:

```xpath
//books/book[number(rating) > 4.5]/author/text()
```

4. Obtener los años de publicación de los libros escritos por un autor específico:

```xpath
//books/book[author="Harper Lee"]/year/text()
```

5. Encontrar los géneros de los libros que fueron publicados después de 2000:

```xpath
//books/book[number(year) > 2000]/genre/text()
```

### Ejercicios: Registro de Canciones

1. Obtener el título de una canción específica:

```xpath
//songs/song[@id="1"]/title/text()
```

2. Listar los artistas de las canciones de un género determinado:

```xpath
//songs/song[genre="Pop"]/artist/text()
```

3. Encontrar los títulos de las canciones que tienen una calificación superior a 4.7:

```xpath
//songs/song[number(rating) > 4.7]/title/text()
```

4.  Obtener los años de lanzamiento de las canciones interpretadas por un artista específico:

```xpath
//songs/song[artist="Queen"]/year/text()
```

5.  Encontrar los géneros de las canciones que fueron lanzadas después de 2000:

```xpath
//songs/song[number(year) > 2000]/genre/text()
```

### Ejercicios: Registro de Estudiantes

1. Obtener los nombres completos de todos los estudiantes.

```xpath
//student/name/text()
```

2. Obtener el nombre completo de los estudiantes que tienen una calificación superior a 90 en matemáticas.

```xpath
//student[grades/subject[@name='Math']/grade > 90]/name/text()
```

3. Contar el número total de estudiantes que están en el curso "CS101".

```xpath
count(//student[course_id = 'CS101'])
```

4. Obtener los nombres completos de los estudiantes que tienen más de 3 ausencias.

```xpath
//student[absences > 3]/name/text()
```

5. Obtener el nombre completo de los estudiantes que tienen un tutor con el ID "2001".

```xpath
//student[tutor_id = '2001']/name/text()
```

6. Obtener la calificación de un estudiante específico en una asignatura dada.
```xpath
//student[@id="1001"]/grades/subject[@name="Math"]/grade/text()
```

7. Listar los nombres de los profesores de un curso determinado.
```xpath
//course[@name="Computer Science"]/professor/name/text()
```

8. Encontrar los nombres de los estudiantes que tienen más de 90 en cualquier asignatura.
```xpath
//student[grades/subject/grade > 90]/name/text()
```

9. Obtener los nombres de los estudiantes que están matriculados en un curso específico.
```xpath
//student[course_id="CS101"]/name/text()
```

10. Encontrar el nombre del curso en el que está inscrito un estudiante con un ID específico.
```xpath
//course[student_id="1001"]/name/text()
```

11. Encontrar los estudiantes que tienen más de 3 ausencias.
```xpath
//student[absences > 3]/name/text()
```

12. Obtener el nombre del tutor de un estudiante específico.
```xpath
//student[@id="1001"]/tutor/name/text()
```

13. Encontrar los IDs de los estudiantes que están bajo la tutoría de un tutor específico.
```xpath
 //student[tutor_id="2001"]/id/text()
```
  
### Ejercicios: Lista de Empleados

1. Obtener los nombres completos de todos los empleados.

```xpath
//employee/name/text()
```

2. Obtener los nombres de los departamentos donde trabajan los empleados que tienen un salario mayor a $50000.

```xpath
//employee[salary > 50000]/department_id/text()
```

3. Contar el número total de empleados que tienen más de 5 años de experiencia.

```xpath
count(//employee[years_of_experience > 5])
```

4. Obtener los nombres completos de los empleados que tienen un supervisor con el ID "2002".

```xpath
//employee[supervisor_id = '2002']/name/text()
```

5. Obtener los nombres de los empleados que trabajan en el departamento con el ID "D002".

```xpath
//employee[department_id = 'D002']/name/text()
```

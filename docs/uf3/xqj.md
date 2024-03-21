# XQJ API
L'`API XQJ` (`XML Query Language for Java`) és una interfície de programació d'aplicacions que permet als desenvolupadors `Java` interactuar amb els sistemes de bases de dades `XML` utilitzant el llenguatge de consulta `XQuery`.

`XQuery` és un llenguatge de consulta utilitzat per extreure informació de bases de dades XML. L'`API XQJ` proporciona una forma estàndard d'interactuar amb les bases de dades `XML` utilitzant `XQuery`, cosa que permet als desenvolupadors integrar fàcilment la funcionalitat de consulta `XML` a les seves aplicacions Java.

L'`API XQJ` consta d'una sèrie de classes i interfícies que els desenvolupadors poden fer servir per crear i executar consultes XQuery, processar els resultats de la consulta i controlar la connexió a la base de dades `XML`. Algunes de les classes més importants inclouen XQConnection, que representa la connexió a la base de dades `XML`, `XQPreparedExpression`, que es fa servir per compilar i executar consultes XQuery, i XQResultSequence, que representa els resultats d'una consulta `XQuery`.

L'`API XQJ` és compatible amb una àmplia gamma de sistemes de bases de dades `XML`, incloent `eXist`, `MarkLogic`, `BaseX` i `Saxon`, entre d'altres. Els desenvolupadors poden utilitzar l'`API XQJ` per realitzar una varietat de tasques relacionades amb la gestió de dades XML, com ara la creació i l'edició de documents `XML`, la recerca d'informació específica en documents `XML` i la transformació de dades `XML` en altres formats.

En resum, l'`API XQJ` és una eina útil per als desenvolupadors de Java que necessiten interactuar amb sistemes de bases de dades `XML` i fer-hi consultes XQuery. L'API proporciona una forma estàndard i fàcil de treballar amb dades `XML`, cosa que permet als desenvolupadors integrar la funcionalitat de consulta `XML` a les seves aplicacions de forma ràpida i senzilla.


## Conceptes bàsics

Hi ha quatre conceptes principals que cal entendre a l'`API XQJ` per fer sol·licituds a `eXist`: fonts de dades, connexions, expressions i seqüències de resultats:

* ***`XQDataSource`***: la font de dades proporciona el controlador principal de `XQJ` i defineix com us connecteu al servidor. Amb la implementació `net.xqj.`eXist`.`eXist`XQDataSource` de `xqj.net`, heu de definir dues propietats per poder connectar-vos a `eXist`

  * `serverName`: aquest és el nom d'amfitrió o l'adreça IP del servidor `eXist` al qual us voleu connectar. Si esteu executant el vostre client `XQJ` a la mateixa màquina que `eXist`, podeu utilitzar `localhost` o `127.0.0.1`
  * `port`: aquest és el port `TCP` al qual escolta el servidor ``eXist`` al qual us voleu connectar. Si no heu tornat a configurar aquesta configuració a `eXist`, serà `8080` per defecte.


### Exemple: Configuració de `XQDataSource` per connectar-se a ``eXist``

```java
final XQDataSource xqs = new eXistXQDataSource();
xqs.setProperty("serverName", "localhost");
xqs.setProperty("port", "8080");
```

* ***`XQConnection`***: la connexió representa una sessió connectada amb XQJ amb el servidor i s'obté de la font de dades. Quan sol·liciteu la connexió des de la font de dades, heu de proporcionar el vostre nom d'usuari i contrasenya per accedir a `eXist`. La implementació `eXist XQJ` utilitza `REST`, de manera que no hi ha connexió persistent; més aviat, les trucades `HTTP` es fan segons les necessitats de l'objecte `XQConnection`. Tanmateix, sempre hauríeu de trucar a close a l'objecte `XQConnection` per netejar qualsevol objecte retingut.


### Exemple: obrir una `XQConnection` autenticada a `eXist`
```java
XQConnection connection = dataSource.getConnection("admin", "password");
```

* ***`XQExpression`***: l'expressió representa una expressió `XQuery` que es pot enviar al servidor i executar-se. També és possible utilitzar `XQPreparedExpression` si voleu executar la mateixa expressió diverses vegades amb paràmetres diferents (p. ex., enllaços de variables externes).

* ***`XQResultSequence`***: com a resultat de l'execució d'una `XQExpression` o `XQPreparedExpression`, es genera una seqüència de resultats que es pot repetir per recuperar resultats del servidor.

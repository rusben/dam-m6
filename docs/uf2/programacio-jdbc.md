# Programació amb JDBC
* [Introducció a JDBC](#intro-jdbc)
* [Connexió a una base de dades](#connexio-base-dades)
* [Consultes i modificacions bàsiques](#consultes-modificacions-basiques)
* [Execució de sentències SQL: la classe Statement](#execucio-sentencies-statement)
* [Tractament de resultats: la classe ResultSet](#tractament-resultats-resultset)
* [Consultes i modificacions avançades](#consultes-modificacions-avancades)

## Introducció a JDBC <a name="intro-jdbc"></a>
Suposem que volem desenvolupar una aplicació en `Java` i que ja hem decidit quin `SGBD` i driver utilitzarem.

Vegem com s'usa l'`API JDBC`, és a dir, com es programa mitjançant les classes d'aquesta biblioteca.

Començarem veient com s'estableix connexió amb una BD.

A continuació aprendrem la manera d'enviar consultes, tractar els resultats, fer modificacions i treballar amb procediments emmagatzemats.

Finalment veurem la manera de gestionar les transaccions i el tractament d'errors.

Recursos necessaris:
`JDBC4 Postgresql Driver` https://jdbc.postgresql.org/download.html

# Connexió a una base de dades <a name="connexio-base-dades"></a>

El primer pas per treballar amb una BD és la connexió. Per a poder-nos connectar cal seguir quatre passos:

1. Importar els packages necessaris. Tal com s'ha comentat, l'`API JDBC` és formada per dos packages, `java.sql` i `javax.sql`. El primer conté les classes i interfícies essencials (inclou les classes `Driver`,  `Connection`, `Statement`, `ResultSet`, `PreparedStatement`, `CallableStatement`, principalment) i l'haurem d'importar sempre. El segon és l'extensió estàndard i conté classes més especialitzades que s'escapen dels objectius d'aquest nucli formatiu  (`RowSet`, `DataSource` i `PooledConnection`, entre d'altres).

2. Carregar el driver adequat. La manera tradicional de carregar un driver és forçant la càrrega del driver a partir del seu nom, utilitzant el mètode `forName()` de la classe `Class`. Per exemple, per carregar el driver del `PostgreSQL` faríem el següent:

	```java
	Class.forName( "org.postgresql.Driver" );
	```

	Aquest nom identifica la classe `Driver` del package `org.postgresql` (recordem que és la classe que implementa la interfície `java.sql.Driver`). Si volem utilitzar un altre driver haurem de fer una mica d'investigació per esbrinar el nom del `package` i el nom de la classe que implementa la interfície `java.sql.Driver`.

	Carregar una classe a partir del seu nom pot fallar si el classloader (objecte responsable de carregar les classes necessàries per a l'execució d'un programa) no és capaç de trobar cap classe amb aquest nom. Per tant, ens hem d'assegurar que el `CLASSPATH` apunti al fitxer (normalment `.jar`) que conté el driver. En cas de no trobar-la, es genera una excepció de tipus `ClassNotFoundException`.

	En la versió `JDBC 4.0` es proposa delegar la responsabilitat de carregar el driver al `DriverManager`, que serà l'encarregat de buscar el driver en els directoris o fitxers jar definits al `CLASSPATH` quan siguin necessaris.

3. Obrir la connexió. Tot i que el concepte d'obrir connexió sigui simple, amaga una certa complexitat quan hem d'indicar la BD que volem obrir.

	```java
	String dbURL="jdbc:postgresql://localhost/bdMail";
	Connection conn = DriverManager.getConnection( dbURL,"usuari","contrasenya");
	```

	L'encarregat d'obrir una connexió amb una BD és el `DriverManager`, per mitjà del mètode `getConnection()` i requereix tres paràmetres:


	a. El primer és l'anomenat url i identifica la BD a la qual ens volem connectar. És una cadena formada per tres parts: la primera sempre és `jdbc` i la resta, variables.

	`jdbc:<subprotocol>:<subname>`

	El subprotocol és el nom del driver que utilitzarem per a connectar-nos. Un altre cop, cal fer una mica d'investigació.

	El subname serveix per a identificar la BD pròpiament. El seu format depèn del driver que emprem i, per tant, no té un format estàndard. Aquest és un tercer punt d'investigació.

	En els casos més explícits, identifica el servidor (on hi ha l'SGBD), el port on escolta l'SGBD i el nom de la BD. Si no indiquem servidor s'entén que és el mateix ordinador (localhost) i, si tampoc no ho fem amb el port, s'entén que és el port per defecte de l'SGBD.

	En l'exemple anterior ens estaríem connectant a una BD PostgreSQL que hi ha en el mateix ordinador, escoltant el port per defecte i que s'anomena bdMail.

	b. El segon i tercer paràmetres corresponen al nom d'usuari de la BD i a la contrasenya corresponent. Cal assegurar-nos que ens connectem amb un usuari que tingui suficients privilegis per a executar les sentències SQL que vinguin a continuació.

4. Tancar la connexió. Sens dubte, és l'operació més senzilla de les vistes fins ara. Simplement cal cridar el mètode `close()` de la connexió que volem tancar.

	```java
	conn.close();
	```

	Si no tanquem la connexió ho farà el garbage collector quan destrueixi l'objecte connexió.
	En tot cas, en aplicacions client és molt important tancar les connexions quan ja no les volem utilitzar; així aconseguim que el servidor alliberi recursos i que els pugui dedicar a un altre client.

	Arribats en aquest punt ens podem plantejar si els passos que anem seguint són elegants. Hem indicat el nom del driver a l'hora de carregar-lo, i l'hem de tornar a indicar a l'hora de connectar-nos. És necessària aquesta redundància? En la versió `JDBC 4.0` es delega la càrrega de drivers al `DriverManager`, de manera que només cal indicar el driver en el moment de crear la connexió.

	També ens podem plantejar si és convenient indicar l'equip, el port, el nom de la BD, el nom d'usuari i la contrasenya en el codi font. Ho és? No massa, especialment si volem distribuir l'aplicació sense haver-la de recompilar cada cop! De fet, a partir de `JDBC 2.0` ja es proposa utilitzar una interfície anomenada `DataSource` per a desvincular el codi font dels detalls de connexió.

## Exemple 1

Verifica que pots realitzar la connexió amb la base de dades amb aquest exemple emprant d'IDE Eclipse

***ex001Connexio.java***

```java
// export CLASSPATH=/usr/share/java/postgresql.jar:.

import java.sql.*;
import java.io.*;

public class ex001Connexio
{
	public static void main( String[] args ) throws Exception
	{
		//Carreguem el driver de postgreSQL
		Class.forName( "org.postgresql.Driver" );

		//Obrim la connexió amb la base de dades anomenada dbMail
		//utilitzant el driver de postgreSQL
		//Ens connectem amb "usuari" amb contrassenya "usuari"
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");

		//Tanquem la connexió. No és estríctament necessari, però és un bon hàbit!
		conn.close();
	}
}
```

## Exemple 2

Verifica el funcionament de la classe `ex002CreacioDBMail.java` que permet crear la base de dades dbMail a PostgreSQL on l'usuari mailer amb  password 1234 creat previament pot verificar la seva connexió.

***ex002CreacioBDMail.java***

```java
// export CLASSPATH=/usr/share/java/postgresql.jar:.

import java.sql.*;
import java.io.*;

public class ex002CreacioBDMail
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");

		Statement st = conn.createStatement();

		//Eliminem les taules i relacions que hi pugui haver
		try { st.executeUpdate("ALTER TABLE Missatges DROP CONSTRAINT missatges_usuaris_fkey");}
		catch (SQLException e) {}
		try { st.executeUpdate("ALTER TABLE Missatges DROP CONSTRAINT missatges_forums_fkey");}
		catch (SQLException e) {}
		try { st.executeUpdate("ALTER TABLE Lectures DROP CONSTRAINT lectures_missatges_fkey");}
		catch (SQLException e) {}
		try { st.executeUpdate("ALTER TABLE Lectures DROP CONSTRAINT lectures_usuaris_fkey");}
		catch (SQLException e) {}
		try { st.executeUpdate("DROP TABLE Usuaris");} catch (SQLException e) {}
		try { st.executeUpdate("DROP TABLE Forums");} catch (SQLException e) {}
		try { st.executeUpdate("DROP TABLE Missatges");} catch (SQLException e) {}
		try { st.executeUpdate("DROP TABLE Lectures");} catch (SQLException e) {}

		//Creem les noves taules
		st.executeUpdate("CREATE TABLE Usuaris (nom_usuari VARCHAR(10) PRIMARY KEY, "+
			"contrasenya VARCHAR(10), nom VARCHAR(20), cognoms VARCHAR(40))");
		st.executeUpdate("CREATE TABLE Forums (codi_forum NUMERIC(4,0) PRIMARY KEY, nom VARCHAR(20))");
		st.executeUpdate("CREATE TABLE Missatges (codi_missatge NUMERIC(4,0) PRIMARY KEY, "+
			"codi_forum NUMERIC(4,0), autor VARCHAR(10), titol VARCHAR(40),"+
			"text VARCHAR(250), fil NUMERIC(4))");
		st.executeUpdate("CREATE TABLE Lectures (codi_missatge NUMERIC(4,0), "+
			"nom_usuari VARCHAR(10), data_hora TIMESTAMP, PRIMARY KEY (codi_missatge,nom_usuari))");

		//Creem les relacions
		st.executeUpdate("ALTER TABLE Missatges ADD CONSTRAINT missatges_usuaris_fkey "+
			"FOREIGN KEY (autor) REFERENCES Usuaris(nom_usuari)");
		st.executeUpdate("ALTER TABLE Missatges ADD CONSTRAINT missatges_missatges_fkey "+
			"FOREIGN KEY (fil) REFERENCES Missatges(codi_missatge)");
		st.executeUpdate("ALTER TABLE Missatges ADD CONSTRAINT missatges_forums_fkey "+
			"FOREIGN KEY (codi_forum) REFERENCES Forums");
		st.executeUpdate("ALTER TABLE Lectures ADD CONSTRAINT lectures_missatges_fkey "+
			"FOREIGN KEY (codi_missatge) REFERENCES Missatges");
		st.executeUpdate("ALTER TABLE Lectures ADD CONSTRAINT lectures_usuaris_fkey "+
			"FOREIGN KEY (nom_usuari) REFERENCES Usuaris");

		//Afegim alguns registres
		st.executeUpdate("INSERT INTO Usuaris (nom_usuari,contrasenya,nom,cognoms) "+
			"VALUES ('mPalau','1234','Manel','Palau Roca')");
		st.executeUpdate("INSERT INTO Usuaris (nom_usuari,contrasenya,nom,cognoms) "+
			"VALUES ('cMas','1234','Clara','Mas Daumal')");

		st.executeUpdate("INSERT INTO Forums (codi_forum,nom) VALUES (1,'JDBC')");
		st.executeUpdate("INSERT INTO Forums (codi_forum,nom) VALUES (2,'Java')");

		st.executeUpdate("INSERT INTO Missatges VALUES (1,1,'mPalau','error al compilar',"+
			"'no puc compilar...',NULL)");
		st.executeUpdate("INSERT INTO Missatges VALUES (2,1,'cMas','Re:error al compilar',"+
			"'prova definint el CLASSPATH...',1)");
		st.executeUpdate("INSERT INTO Missatges VALUES (3,1,'mPalau','error en executar',"+
			"'ara no puc executar...',NULL)");
		st.executeUpdate("INSERT INTO Missatges VALUES (4,1,'mPalau','Re:error al compilar',"+
			"'Gràcies...',2)");
		st.executeUpdate("INSERT INTO Missatges VALUES (5,1,'cMas','Re: error en executar',"+
			"'torna a provar amb el CLASSPATH...',3)");
		st.executeUpdate("INSERT INTO Missatges VALUES (6,1,'mPalau','Re: error en executar',"+
			"'doncs aquest cop no és això...',5)");

		st.executeUpdate("INSERT INTO Lectures VALUES(1,'mPalau','3/4/2010 16:19')");
		st.executeUpdate("INSERT INTO Lectures VALUES(1,'cMas',to_timestamp('3/4/2010 16:19','DD/MM/YYYY HH24:MI'))");

		//Mostrem les dades afegides
		System.out.println("USUARIS------------------------");
		ResultSet rs = st.executeQuery("SELECT * FROM Usuaris");
		while (rs.next()) System.out.println(rs.getString(1)+"--"+rs.getString("cognoms"));
		rs.close();

		System.out.println("FORUMS------------------------");
		rs = st.executeQuery("SELECT * FROM Forums");
		while (rs.next()) System.out.println(rs.getString(1)+"--"+rs.getString("nom"));
		rs.close();

		System.out.println("MISSATGES------------------------");
		rs = st.executeQuery("SELECT * FROM Missatges");
		while (rs.next()) System.out.println(rs.getString("codi_missatge")+"--"+
					rs.getString("titol")+"--"+rs.getString("text"));
		rs.close();

		st.close();
		conn.close();
	}
}

```

## Consultes i modificacions bàsiques <a name="consultes-modificacions-basiques"></a>

Entrem ara a la part més interessant de la programació en JDBC o, com a mínim, la que ens ocuparà més temps.
Veurem la manera de fer consultes a la BD i de modificar-ne les dades per mitjà de sentències SQL.
Començarem enviant consultes o modificacions mitjançant la classe Statement i tractant els resultats de les consultes amb la classe ResultSet.
Amb això tindrem una idea bàsica de comunicació entre aplicació i BD.

![JDBC](../images/jdbc1.png)

## Execució de sentències SQL: la classe Statement <a name="execucio-sentencies-statement"></a>

El procés per a fer una consulta o una modificació arrenca d'allà mateix. Hem de crear un objecte de tipus `Statement` que contindrà la sentència SQL de consulta (`SELECT`), modificació de dades (`INSERT`, `UPDATE` i `DELETE`) o modificació de l'estructura de BD (`CREATE TABLE`, `DROP TABLE`, `ALTER TABLE`, etc.).

La responsabilitat de crear nous objectes de tipus `Statement` és de la connexió a la qual volem enviar la sentència SQL.

```java
Statement st = conn.createStatement();
```

Un cop tenim l'objecte de tipus `Statement` creat, utilitzarem el mètode `executeQuery()` per a les consultes i `executeUpdate()` per a les modificacions. En el cas de les consultes, el mètode `executeQuery()` retorna un objecte `ResultSet` que ens permetrà accedir a les dades consultades. Ho tractarem en el subapartat següent.

```java
ResultSet rs;
rs = st.executeQuery("SELECT * FROM Usuaris");
```

En el cas de modificacions, el mètode executeUpdate retorna un enter.
Aquest enter indica el nombre de files afectades. En el cas de modificar l'estructura de la BD (execució de sentències SQL de tipus `DDL`), retorna un 0.

```java
st.executeUpdate("DROP TABLE Usuaris");
st.executeUpdate("CREATE TABLE Usuaris ("+"nom_usuari VARCHAR(10) PRIMARY KEY, "+
"contrasenya VARCHAR(10), nom VARCHAR(20), "+ "cognoms VARCHAR(40))");

st.executeUpdate("INSERT INTO Usuaris "+"(nom_usuari,contrasenya,nom,cognoms) VALUES "+"('mPalau','1234','Manel','Palau Roca')");
```

Les tres sentències SQL d'aquest exemple sempre fan el mateix. En alguns casos això ja és suficient (per exemple, quan creem la taula `Usuaris`), però normalment no serà així.

La darrera sentència de l'exemple afegeix l'usuari Manel a la taula Usuaris, però és un cas poc habitual. Normalment, quan s'afegeixen files en una BD, els valors de les columnes els introdueix l'usuari de l'aplicació en temps d'execució o es carreguen des d'un fitxer. En tot cas, són valors que no es coneixen en temps de compilació.

Per aconseguir executar una sentència SQL amb valors canviants concatenarem les parts fixes de la sentència amb les parts canviants (que se substituiran per variables). El codi queda una mica il·legible, però amb el temps ens hi acabarem acostumant.

```java
String nomUsuari,contrasenya,nom,cognoms;
...
st.executeUpdate("INSERT INTO Usuaris "+ "(nom_usuari,contrasenya,nom,cognoms) VALUES "+"('"+nomUsuari+"','"+contrasenya+"','"+nom+"','"+cognoms+"')");
```

Podem aplicar aquest mateix patró per fer consultes SQL utilitzant la clàusula `WHERE`. Per exemple, ens pot interessar consultar els missatges del fòrum que l'usuari de l'aplicació seleccioni. Fixem-nos que la part canviant correspon a una columna numèrica i que, per tant, no està envoltada de cometes simples!

```java
int codiForum;
...
ResultSet rs = st.executeQuery("SELECT * "+"FROM Missatges WHERE codi_forum="+codiForum);
```

En el cas de columnes de tipus data hem de fer una atenció especial a la interpretació que en farà l'SGBD. Depenent de la configuració local, l'SGBD pot interpretar les dates en format `dd/mm/yyyy` o `mm/dd/yyyy`. Per a assegurar la interpretació correcta usarem funcions de l'SGBD per a fer la conversió. En el cas de PostgreSQL, per exemple, utilitzarem la funció `to_timestamp()`.

```java
//La interpretació dependrà de la config. de l'SGBD
st.executeUpdate("INSERT INTO Lectures "+ "VALUES(1,'mPalau','3/4/2010 16:19')");
//assegurem que l'SGBD ho interpreti correctament!
st.executeUpdate("INSERT INTO Lectures "+ "VALUES(1,'cMas', to_timestamp('3/4/2010 16:19'"+ ",'DD/MM/YYYY HH24:MI'))");
```

Per definir sentències SQL dinàmiques amb paràmetres de tipus temps haurem de tenir en compte el format de les dates en `Java`. Per exemple, el mètode `toString()` de la classe `Timestamp` retorna un data en format `YYYY-MM-DD hh:mm::ss`. El codi necessari seria:

```java
st.executeUpdate("INSERT INTO Lectures "+ "VALUES("+codiMissatge+",'"+nomUsuari+"',"+ "to_timestamp('"+ts+"','YYYY-MM-DD HH24:MI'))");
```

## Exemple 3
```java
import java.sql.*;
import java.io.*;

public class ex003InsertStatementVars
{
	public static void main( String[] args ) throws Exception
	{
		//Per llegir de teclat
		InputStreamReader stdin =new InputStreamReader(System.in);
		BufferedReader cons =new BufferedReader(stdin);

		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");

		//Creem un objecte Statement
		Statement st = conn.createStatement();

		//Llegim les dades
		String nomUsuari=cons.readLine();
		String contrasenya=cons.readLine();
		String nom=cons.readLine();
		String cognoms=cons.readLine();

		st.executeUpdate("INSERT INTO USUARIS (nom_usuari,contrasenya,nom,cognoms) "+
			"VALUES('"+nomUsuari+"','"+contrasenya+"','"+nom+"','"+cognoms+"')");

		//tanquem Statement i Connection.
		st.close();
		conn.close();
	}
}

```

## Tractament de resultats: la classe ResultSet  <a name="tractament-resultats-resultset"></a>
Com ja hem dit, la classe ResultSet ens permetrà accedir als resultats de les consultes. Aquest accés, però, no és lliure i ens haurem de cenyir a les restriccions següents:

* Simultàniament només podem accedir a una sola fila. Per poder accedira totes les files haurem de fer un recorregut i, en cada iteració, accedir a una de les files.
* El recorregut, per defecte, només pot anar endavant.
* Durant el recorregut, d'entrada, només podem consultar les files. No les podem modificar.

Per tant, la classe `ResultSet` ens oferirà mètodes per poder fer un recorregut per les files de la consulta i, en cada iteració, consultar el valor de les columnes de la fila actual tenint en compte que:

* Podem consultar el valor de les columnes a partir del nom corresponent o a partir d'un enter que representa la posició de la columna dins de la taula (començant per 1).
* Disposem de mètodes diferents per a cada tipus de dades de les columnes que es vol consultar.

| Tipus estàndard SQL |	Mètode getTipus |
|-----------------|-----------------|
| CHAR | getString |
| VARCHAR | getString |
| SMALLINT | getShort |
| INTEGER | getInt |
| FLOAT | getFloat / getDouble |
| DOUBLE | getDouble |
| DECIMAL | getBigDecimal |
| DATE | getDate |
| TIME | getTime |
| MONEY | getDouble |

En l'exemple següent consultem les dades dels usuaris (emmagatzemades a la taula `Usuaris` de la nostra `BD` de referència), fem un recorregut i, en cada iteració, mostrem la columna número 1 (que correspon a la columna `nom_usuari`) i els cognoms.

```java
rs = st.executeQuery("SELECT * FROM Usuaris");
while (rs.next()) {
               System.out.print(rs.getString(1)+"--"+rs.getString("cognoms"));
}
rs.close();
```

S'intueix que darrere d'un `ResultSet` hi ha un cursor que apunta a la fila actual. Quan es crea un objecte `ResultSet`, el cursor apunta a la posició anterior a la primera fila i, cada cop que executem el mètode next, el cursor avança.
Quan el mètode next no troba cap més fila, retorna fals i el recorregut finalitza. Però, en realitat, les coses no són ben bé així.

Cada cop que `JDBC` demana dades a la BD (el què es coneix com a `fetch`) no rep una fila i prou. Per qüestions de rendiment, la BD envia unes quantes files. A més, el nombre de files que s'envien depèn de cada driver.

En el cas del driver `JDBC` de `PostgreSQL`, per defecte, s'envien totes les dades de la consulta de cop. Mentre no són tractades, aquestes dades es guarden a l'equip client en una memòria cau. Hem de vigilar que el volum de dades de la consulta no sigui massa gran, sinó podem exhaurir la memòria de l'equip client!

En relació amb aquest tema, hem de tenir cura de fer consultes de les dades que ens siguin estrictament necessàries. En el cas anterior tenim un exemple clar de consulta ineficient. No té cap sentit fer una consulta de totes les columnes de la taula Usuaris si després només en mostrem dues. El mateix codi refinat seria:

```java
rs = st.executeQuery("SELECT nom_usuari,cognoms "+"FROM Usuaris");
while (rs.next()) {
              System.out.print(rs.getString(1)+"--"+ rs.getString("cognoms"));
}
rs.close();
```

## Exemple 4
```java
import java.sql.*;
import java.io.*;

public class ex004ConsultaStatement
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");

		//Creem un objecte Statement
		Statement st = conn.createStatement();

		//Consultem les dades de tots els usuaris
		ResultSet rs = st.executeQuery("SELECT * FROM Usuaris");
		while (rs.next()) System.out.println(rs.getString(1)+"--"+rs.getString("cognoms"));

		//tanquem el ResultSet, Statement i Connection.
		rs.close();
		st.close();
		conn.close();
	}
}
```

## Consultes i modificacions avançades <a name="consultes-modificacions-avancades"></a>

Tal com hem vist, en la versió `JDBC 2.0` es va introduir la possibilitat de fer recorreguts millorats (endavant, endarrere i desplaçaments directes a qualsevol posició) i també la possibilitat de modificar les files mitjançant mètodes (sense utilitzar l'SQL).
Per a permetre aquestes noves funcionalitats, cal crear l'objecte `Statement` amb el mateix mètode `createStatement`, però amb dos paràmetres que determinen el tipus de moviment i el tipus d'operacions permeses (vegeu la taula següent).

Segons el ***tipus de moviment***:

* ***TYPE_FORWARD_ONLY***: és el tipus de moviment assignat per defecte. Només permet fer un sol recorregut cap endavant.
* ***TYPE_SCROLL_INSENSITIVE***: permet llibertat de moviments (endavant, endarrere) tants cops com calgui.
* ***TYPE_SCROLL_SENSITIVE***: com l'anterior, però reflecteix els canvis que es van produint en la BD mentre està actiu. S'entén que aquests canvis normalment són generats per l'execució simultània d'altres aplicacions en altres equips.

Segons els ***tipus d'operacions*** admeses:
* ***CONCUR_READ_ONLY***: només permet fer consultes i és l'opció per defecte.
* ***CONCUR_UPDATABLE***: permet fer consultes i modificacions.

En total tenim sis combinacions possibles, però en la pràctica no tots els drivers les permeten. Per exemple, el driver de `PostgreSQL` (i no és l'únic) no possibilita el tipus de moviment sensitiu i, per tant, ofereix quatre combinacions. Per a solucionar aquesta limitació es recomana repetir les consultes cada cop que es  vulguin tenir les dades actualitzades.

## ResultSet amb llibertat de moviments

Per a treballar amb `ResultSet` lliures de moviments cal triar l'opció `TYPE_SCROLL_INSENSITIVE` o `TYPE_SCROLL_SENSITIVE` (si el driver ho permet). Així, podrem fer recorreguts:

* ***Endavant***: amb el mètode `beforeFirst()` i `next()`. El primer no és necessari, ja que és la posició inicial del ResultSet i el segon ja l'hem vist.
* ***Endarrere***: amb els mètodes `afterLast()` i `previous()`.

L'exemple següent fa un recorregut endarrere per les files de la taula `Usuaris`. En cada iteració mostra la clau primària i els cognoms dels usuaris.

```java
st=conn.createStatement(
ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_READ_ONLY);

rs = st.executeQuery("SELECT * FROM Usuaris");
rs.afterLast();
while (rs.previous())
System.out.println(rs.getString(1)+"--"+ rs.getString("cognoms"));
rs.close();
```

## Exemple 5
```java
import java.sql.*;
import java.io.*;

public class ex005ConsultaStatementScrollReves
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");

		//Creem un objecte Statement
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY);

		//Consultem les dades de tots els usuaris del revés!
		ResultSet rs = st.executeQuery("SELECT * FROM Usuaris");
		rs.afterLast();
		while (rs.previous()) System.out.println(rs.getString(1)+"--"+rs.getString("cognoms"));

		//tanquem el ResultSet, Statement i Connection.
		rs.close();
		st.close();
		conn.close();
	}
}

```

* ***Aleatoris***: amb els mètodes `first()`, `last()`, `absolute(int n)` i `relative(int n)`. Permeten moure'ns a la primera, a la darrera, a una posició concreta o a una de relativa, respectivament. Si intentem moure'ns fora de rang genera un error.

A continuació tenim un exemple per a veure com s'utilitzen els diversos mètodes de posicionament.

```java
st=conn.createStatement(
ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
rs = st.executeQuery("SELECT * FROM Usuaris");
rs.last();
...
rs.relative(-1);
...
rs.first();
...
rs.absolute(3);
```

## Exemple 6
```java
import java.sql.*;
import java.io.*;

public class ex006ConsultaStatementScrollAleatori
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");

		//Creem un objecte Statement
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY);

		//Consultem les dades de tots els usuaris del revés!
		ResultSet rs = st.executeQuery("SELECT * FROM Usuaris");
		rs.last();
		System.out.println(rs.getString(1)+"--"+rs.getString("cognoms"));
		rs.relative(-1);
		System.out.println(rs.getString(1)+"--"+rs.getString("cognoms"));
		rs.first();
		System.out.println(rs.getString(1)+"--"+rs.getString("cognoms"));
		rs.absolute(3);
		System.out.println(rs.getStrixºng(1)+"--"+rs.getString("cognoms"));
		try
		{
			//el posicionament relatiu fora de rang genera un error!!!
			rs.relative(-300);
			System.out.println(rs.getString(1)+"--"+rs.getString("cognoms"));
		 } catch (SQLException e) {}
		try
		{
			//el posicionament absolut fora de rang genera un error!!!
			rs.absolute(300);
			System.out.println(rs.getString(1)+"--"+rs.getString("cognoms"));
		 } catch (SQLException e) {}

		//tanquem el ResultSet, Statement i Connection.
		rs.close();
		st.close();
		conn.close();
	}
}
```

## Modificacions per mitjà d'un ResultSet

Amb l'opció `CONCUR_UPDATABLE` tenim la possibilitat d'actualitzar la BD a mesura que anem recorrent el `ResultSet`, i sense utilitzar sentències `SQL`.

En la pràctica, però, poden aparèixer problemes. El motiu de fons és que l'`SGBD` ha de poder propagar automàticament la modificació des de les dades llegides (i que es troben en el `ResultSet`) cap a les taules que emmagatzemen aquestes dades. Aquesta propagació automàtica dels canvis, en general, només es pot fer quan la sentència `SELECT` que està lligada al `ResultSet` està basada en una única taula i, entre les dades llegides, s'inclou la clau primària.

Un cop superada aquesta problemàtica, els canvis possibles són modificació, inserció i esborraments.

### Modificació
Un cop situats a la fila que volem modificar, disposem de mètodes per a canviar el valor de les columnes. Són els mètodes `updateXXX` i són antagònics als `getXXX`.
Després de modificar les columnes d'una fila cal enviar els canvis a la BD amb el mètode `updateRow()`. Si ens movem de fila sense executar aquest mètode, els canvis normalment es perdran (depèn del driver que utilitzem). Per contra, si volem descartar els canvis fets, tenim el mètode `cancelRowUpdates()`.

L'exemple següent afegeix un "." al final de cada nom d'usuari.


```java
st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
ResultSet.CONCUR_UPDATABLE);
rs = st.executeQuery("SELECT * FROM Usuaris");
while (rs.next())
{
rs.updateString("nom",rs.getString("nom")+".");
rs.updateRow();
}
rs.close();
```

## Exemple 7
```java
import java.sql.*;
import java.io.*;

public class ex007ResultSetUpdatable
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");

		//Creem un objecte Statement
		Statement st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
			ResultSet.CONCUR_UPDATABLE);

		//Consultem les dades de tots els usuaris per afegir un . al final del nom
		ResultSet rs = st.executeQuery("SELECT * FROM Usuaris");
		while (rs.next())
		{
			rs.updateString("nom",rs.getString("nom")+".");
			rs.updateRow();
		}
		rs.close();

		//Consultem les dades de tots els usuaris
		rs = st.executeQuery("SELECT * FROM Usuaris");
		while (rs.next())
			System.out.println(rs.getString("nom"));
		rs.close();

		//tanquem Statement i Connection.
		st.close();
		conn.close();
	}
}
```



També podem fer modificacions sobre un `ResultSet` amb llibertat de moviments.

L'exemple següent se situa a la darrera fila i obté el número de fila amb `getRow()`. Així sabem el nombre total de files consultades. A continuació se situa sobre una posició aleatòria entre la primera i la darrera fila i afegeix un `.` al final del nom.

```java
st = conn.createStatement(
ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
rs = st.executeQuery("SELECT * FROM Usuaris");
rs.last();
int nFiles=rs.getRow();
int pos=(int)(Math.random()*nFiles)+1;
rs.absolute(pos);
rs.updateString("nom",rs.getString("nom")+".");
rs.updateRow();
```

## Exemple 8
```java
import java.sql.*;
import java.io.*;

public class ex008ResultSetUpdatableAleatori
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");

		//Creem un objecte Statement
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE);

		//Afegir un . al final del nom d'un usuari aleatori
 		ResultSet rs = st.executeQuery("SELECT * FROM Usuaris");
		rs.last();
		int nFiles=rs.getRow();
		int pos=(int)(Math.random()*nFiles)+1;
		rs.absolute(pos);
		rs.updateString("nom",rs.getString("nom")+".");
		rs.updateRow();

		//Eliminem el darrer registre
		rs.last();
		rs.deleteRow();

		//Inserim un usuari nou
		rs.moveToInsertRow();
		rs.updateString("nom_usuari","root");
		rs.updateString("contrasenya","super");
		rs.updateString("nom","administrador");
		rs.updateString("cognoms","");
		rs.insertRow();

		//Consultem les dades de tots els usuaris
		rs = st.executeQuery("SELECT * FROM Usuaris");
		while (rs.next())
			System.out.println(rs.getString("nom"));
		rs.close();

		//tanquem Statement i Connection.
		st.close();
		conn.close();
	}
}
```

### Inserció
Per poder inserir de primer ens hem de moure a una nova fila mitjançant el mètode `moveToInsertRow()`. A partir d'aquí, assignem el valor de les diverses columnes amb `updateXXX` i, per afegir cridem `insertRow()`.

```java
rs.moveToInsertRow();
rs.updateString("nom_usuari","root");
rs.updateString("contrasenya","super");
rs.updateString("nom","administrador");
rs.updateString("cognoms","");
rs.insertRow();
```

### Esborraments
Els esborraments són el cas més simple. Només cal situar-se a la fila adequada i cridar el mètode `deleteRow()`.

L'exemple següent se situa a la darrera fila i l'esborra.

```java
rs.last();
rs.deleteRow();
```

# Consultes i modificacions amb PreparedStatement

Amaga

La classe PreparedStatement és una alternativa a la classe Statement. Permeten fer el mateix, consultes i modificacions a la BD, però canvia la manera de fer-ho.

D'entrada, un PreparedStatement no es pot reutilitzar per a executar una segona sentència SQL. Cada sentència anirà lligada a un objecte PreparedStatement diferent.

Les sentències SQL que definim en un PreparedStatement normalment són incompletes, en el sentit que poden tenir alguns valors indefinits. Utilitzarem un ? per a cada valor indefinit de la sentència.

```java
SELECT * FROM USUARIS WHERE nom_usuari=?
```

Una sentència incompleta no es pot executar, però sí que es pot enviar a l'SGBD i fer alguns dels passos necessaris per a dur-la a terme (ho podem entendre com una precompilació). L'objectiu és clar. Tan bon punt sapiguem els valors que completen la sentència, aquesta es podrà executar ràpidament, ja que una part de la feina ja s'haurà fet amb antelació.

L'escenari ideal dels `PreparedStatement` és la situació en què una sentència s'ha d'executar repetidament, canviant només alguns valors; per exemple, si volem afegir unes quantes files a una taula. S'entén que el rendiment és superior que si utilitzem `Statement` independents, ja que l'`SGBD` analitza un sol cop la sentència `SQL` i l'executa tantes vegades com calgui.

Si una sentència només s'ha d'executar un sol cop, utilitzar `PreparedStatements` deixa de ser tan beneficiós i el rendiment s'equipara a la utilització de `Statement` independents.

Per a assignar els valors d'un `PreparedStatement` tenim un seguit de mètodes `setXXX`, de funcionament idèntic a l'`updateXXX` dels ResultSets modificables amb l'excepció que els paràmetres només es poden indexar a partir de la posició i no amb el nom de la columna.

Vegem com podem afegir uns quants fòrums a la BD de referència. Per a aquest exemple, suposem que tenim una classe `Forum` implementada amb els mètodes suficients. Fixem-nos, també, en el mètode clearParameters que esborra el valor dels paràmetres i ens prepara per a l'execució següent.

```java
Forum[] dades={ new Forum(3,"PostgreSQL"), new Forum(4,"ODBC"), new Forum(5,"Oracle") };
sql="INSERT INTO FORUMS(codi_forum,nom) VALUES (?,?)";
PreparedStatement pst = conn.prepareStatement(sql);
for(Forum f:dades)
{
pst.clearParameters();
pst.setInt(1,f.getCodiForum());
pst.setString(2,f.getNom());
pst.executeUpdate();
}
```

## Exemple 9
```java
import java.sql.*;
import java.io.*;

class Forum
{
	int codiForum;
	String nom;

	public Forum()
	{
		codiForum=-1;
		nom="";
	}

	public Forum(int codiForum, String nom)
	{
		this.codiForum=codiForum;
		this.nom=nom;
	}

	public int getCodiForum() {return codiForum;}
	public String getNom() {return nom;}
}

public class ex009PreparedStatementInsert
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");


		//Afegim uns quants fòrums
		Forum[] dades={new Forum(3,"PostgreSQL"),new Forum(4,"ODBC"),
				new Forum(5,"Oracle"),new Forum(6,"MySQL")};

		String sql="INSERT INTO FORUMS (codi_forum,nom) VALUES (?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		for(Forum f:dades)
		{
			pst.clearParameters();
			pst.setInt(1,f.getCodiForum());  //el primer ? correspon al codi del fòrum
			pst.setString(2,f.getNom());	//el segon  ? correspon al nom del fòrum
			pst.executeUpdate();
		}

		//Consultem les dades del fòrums
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Forums");
		while (rs.next())
			System.out.println(rs.getInt("codi_forum")+"--"+rs.getString("nom"));
		rs.close();

		//tanquem Statement i Connection.
		st.close();
		conn.close();
	}
}

```

## Tractament de valors nuls

En els exemples que hem vist no hem fet cap consideració especial per als valors nuls, i de tant en tant cal fer-ne. Totes les columnes que no tenen la restricció `NOT NULL` ocasionalment poden contenir un valor nul.

Hi ha quatre situacions en què poden aparèixer complicacions degudes als valors nuls:

* La lectura de valors nuls d'un `ResultSet`.
* L'ús de valors nuls en sentències `SQL` dinàmiques.
* L'ús de valors nuls en la clàusula `WHERE`.
* La modificació d'un `ResultSet` amb valors nuls.

## Lectura de valors nuls d'un ResultSet

Cada cop que fem una consulta a la BD, la nostra aplicació ha de vigilar de no rebre valors nuls i protegir-se si s'escau.
En la majoria de casos no cal usar cap mètode especial per a detectar si un valor és nul. Els mètodes getString, getBigDecimal, getBytes, getDate, getTime, getTimestamp, getAsciiStream, getCharacterStream, getUnicodeStream, getBinaryStream, getObject, getArray, getBlob, getClob i getRef retornen un objecte. Si el valor consultat en la BD és nul, l'objecte Java també ho serà.
Cal posar una atenció especial en els mètodes que no retornen objectes. En cas de valor nul, els mètodes getByte, getShort, getInt, getLong, getFloat i getDouble retornen un 0. I el mètode getBoolean retorna fals. En aquests casos hem d'utilitzar un mètode addicional, wasNull() per a esbrinar si el 0 i el valor fals corresponen realment a un 0 i un fals, respectivament, o a un valor nul.


El codi següent obté els missatges dels fòrums que inicien nous fils, és a dir, els que tenen el fil nul.

```java
rs=st.executeQuery("SELECT * FROM Missatges");
while (rs.next())
{
int fil=rs.getInt("fil");
if (rs.wasNull()) {
System.out.println(rs.getInt("codi_forum")+"--"+rs.getString("titol"));
}
}
rs.close();
```

## Exemple 10
```java
import java.sql.*;
import java.io.*;

public class ex010Nulls
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");
		Statement st = conn.createStatement();

		//busquem tots els missatges que comencen fils. Només és un exemple,
		//per fer-ho més eficient podíem buscar SELECT * FROM Missatges WHERE fil IS NULL.
		ResultSet rs=st.executeQuery("SELECT * FROM Missatges");
		while (rs.next())
		{
			int fil=rs.getInt("fil");
			if (rs.wasNull()) {
				System.out.println(rs.getInt("codi_forum")+"--"+rs.getString("titol"));
			}
		}
		rs.close();

		//tanquem Statement i Connection.
		st.close();
		conn.close();
	}
}
```

## Ús de valors nuls en sentències SQL dinàmiques

Per a afegir un missatge en un fòrum, podríem tenir un codi semblant a aquest:

```java
int codiForum, ordre, fil;

String autor,titol,text;

...

st.executeUpdate("INSERT INTO Missatges "+"VALUES("+codiMissatge+","+codiForum+",'"+autor+"','"+titol+"','"+text+"',"+fil+")");
```

Aquest codi és correcte, però no permet tractar valors nuls. Per exemple:

No permet obrir un fil nou. O, dit d'una altra manera, cap valor de la variable fil no es convertirà en un NULL en la BD. Aquest problema és comú a tots els tipus bàsics.

No permet deixar el text nul. Fins i tot fent que el text valgués "NULL", aquest NULL quedaria tancat entre cometes simples i, per tant, la BD l'interpretaria com un text que conté literalment NULL.
La solució al problema dels tipus bàsics passa o bé per treballar amb els seus objectes equivalents o bé per determinar un valor que representi el nul. Per exemple, el fil ha d'identificar un missatge i, per tant, serà un valor enter >0.

Podríem reservar el –1 per identificar els valors nuls.
Per solucionar el segon problema hauríem de treure les cometes simples de la part estàtica de la sentència SQL, i afegir-la quan l'objecte fos necessari.

```java
st.executeUpdate("INSERT INTO Missatges VALUES("+
codiMissatge+","+codiForum+",'"+autor+"','"+ titol+"',"+
(text==null?"NULL":"'"+text+"'")+","+ (fil==-1?"NULL":fil)+")");
```

## Exemple 11
```java
import java.sql.*;
import java.io.*;

public class ex011NullsStatement
{
	public static void main( String[] args ) throws Exception
	{
		//Per llegir de teclat
		InputStreamReader stdin =new InputStreamReader(System.in);
		BufferedReader cons =new BufferedReader(stdin);

		//Connectem i creem un Statement
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");
		Statement st = conn.createStatement();

		//Llegim les dades
		int codiMissatge=Integer.parseInt(cons.readLine());
		int codiForum=Integer.parseInt(cons.readLine());
		String autor=cons.readLine();
		String titol=cons.readLine();
		String text=cons.readLine();
		if (text.equals("")) {System.out.println("textn ull"); text=null;}
		int fil=Integer.parseInt(cons.readLine());

		st.executeUpdate("INSERT INTO Missatges "+
			"VALUES("+codiMissatge+","+codiForum+",'"+autor+"','"+
			titol+"',"+(text==null?"NULL":"'"+text+"'")+","+
			(fil==-1?"NULL":fil)+")");

		//Mostrem missatges
		ResultSet rs=st.executeQuery("SELECT * FROM Missatges");
		while (rs.next())
		{
			System.out.println(rs.getInt("codi_forum")+"--"+rs.getString("titol"));
		}
		rs.close();


		//tanquem Statement i Connection.
		st.close();
		conn.close();
	}
}

```

## Ús de valors nuls en la clàusula WHERE
La sentència patró que es defineix en un `PreparedStatment` està oberta a la possibilitat de valors nuls, però només funcionarà si els nuls no estan en les condicions `WHERE` de la consulta SQL.
Els nuls que apareixen en les condicions `WHERE` tenen una sintaxi diferent, `xxx IS NULL`, en comptes de `xxx=valor`. Aquest canvi de sintaxi impedeix tenir un PreparedStatement que sigui vàlid per a condicions amb valors nuls i valors no nuls.
Per a la resta de casos, és a l'hora de definir els valors que haurem de tenir en compte els valors nuls. Quan els valors siguin objectes, podem continuar emprant el mètode `setXXX`, però en els tipus bàsics serà imprescindible la utilització del mètode `setNull`.

```java
...
pst.clearParameters();
pst.setInt(1,codiMissatge);
pst.setInt(2,codiForum);
pst.setString(3,autor);
pst.setString(4,titol);
pst.setString(5,text);
if (fil==-1) pst.setNull(6,java.sql.Types.INTEGER);
else pst.setInt(6,fil);
pst.executeUpdate();
```

## Exemple 12
```java
import java.sql.*;
import java.io.*;

public class ex012NullsPreparedStatement
{
	public static void main( String[] args ) throws Exception
	{
		//Per llegir de teclat
		InputStreamReader stdin =new InputStreamReader(System.in);
		BufferedReader cons =new BufferedReader(stdin);

		//Connectem i creem un Statement
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");
		PreparedStatement pst = conn.prepareStatement("INSERT INTO Missatges VALUES(?,?,?,?,?,?)");

		//Llegim les dades
		int codiMissatge=Integer.parseInt(cons.readLine());
		int codiForum=Integer.parseInt(cons.readLine());
		String autor=cons.readLine();
		String titol=cons.readLine();
		String text=cons.readLine();
		if (text.equals("")) {System.out.println("textn ull"); text=null;}
		int fil=Integer.parseInt(cons.readLine());

		//Provem d'afegir amb el text o el fil nul
		pst.clearParameters();
		pst.setInt(1,codiMissatge);
		pst.setInt(2,codiForum);
		pst.setString(3,autor);
		pst.setString(4,titol);
		if (text==null) pst.setNull(5,java.sql.Types.VARCHAR);
		else pst.setString(5,text);
		if (fil==-1) pst.setNull(6,java.sql.Types.INTEGER);
		else pst.setInt(6,fil);
		pst.executeUpdate();

		//Mostrem missatges
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM Missatges");
		while (rs.next())
		{
			System.out.println(rs.getInt("codi_forum")+"--"+rs.getString("titol"));
		}
		rs.close();


		//tanquem Statement i Connection.
		st.close();
		conn.close();
	}
}
```

## Modificació d'un ResultSet amb valors nuls
En aquest darrer cas torna a passar el mateix. Tenim un mètode especial per a assignar nuls, que serà imprescindible per als tipus bàsics, i opcional, per a la resta.

```java
rs.moveToInsertRow();
rs.updateInt(1,codiMissatge);
rs.updateInt(2,codiForum);
rs.updateString(3,autor);
rs.updateString(4,titol);
rs.updateString(5,text);
if (fil==-1) rs.updateNull(6);
else rs.updateInt(6,fil);
rs.insertRow();
rs.close();
```

## Exemple 13
```java
import java.sql.*;
import java.io.*;

public class ex013NullsUpdatableResultSet
{
	public static void main( String[] args ) throws Exception
	{
		//Per llegir de teclat
		InputStreamReader stdin =new InputStreamReader(System.in);
		BufferedReader cons =new BufferedReader(stdin);

		//Connectem i creem un Statement i ResultSet updatable
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");
		Statement st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
			ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery("SELECT * FROM Missatges");

		//Llegim les dades
		int codiMissatge=Integer.parseInt(cons.readLine());
		int codiForum=Integer.parseInt(cons.readLine());
		String autor=cons.readLine();
		String titol=cons.readLine();
		String text=cons.readLine();
		if (text.equals("")) {System.out.println("textn ull"); text=null;}
		int fil=Integer.parseInt(cons.readLine());

		//Provem d'afegir amb el text o el fil nul
		rs.moveToInsertRow();
		rs.updateInt(1,codiMissatge);
		rs.updateInt(2,codiForum);
		rs.updateString(3,autor);
		rs.updateString(4,titol);
		rs.updateString(5,text);
		if (fil==-1) rs.updateNull(6);
		else rs.updateInt(6,fil);
		rs.insertRow();
		rs.close();

		//Mostrem missatges
		rs=st.executeQuery("SELECT * FROM Missatges");
		while (rs.next())
		{
			System.out.println(rs.getInt("codi_forum")+"--"+rs.getString("titol"));
		}
		rs.close();


		//tanquem Statement i Connection.
		st.close();
		conn.close();
	}
}
```

## Procediments emmagatzemats

`JDBC` ofereix l'objecte `CallableStatement` per poder executar procediments emmagatzemats. La sintaxi utilitzada s'aparta de l'SQL i s'anomena escape syntax. A més a més, tindrem mètodes per a definir els paràmetres d'entrada i recollir els de sortida. La forma general d'una crida serà:

```java
{? = call nomProcedimentEmmagatzemat(?, ?, ...)}
```

en què els ? corresponen als paràmetres d'entrada, de sortida, i d'entrada i sortida.

El següent és un exemple d'un procediment emmagatzemat que rep un paràmetre d'entrada (import) i retorna l'import amb IVA.

```java
CREATE FUNCTION ambIva(import FLOAT) RETURNS FLOAT AS $$
BEGIN
RETURN import * 1.21;
END;
$$ LANGUAGE plpgsql;
```

La creació del `CallableStatement` per a aquest procediment emmagatzemat seria:

```java
CallableStatement cst =conn.prepareCall("{?=call ambIva(?)}");
```

Els procediments emmagatzemats també es poden crear des de JDBC, mitjançant un Statement, però és una pràctica poc habitual. La diferència principal és que tota la sentència quedarà definida en una sola línia de text.

```java
st.executeUpdate("CREATE OR REPLACE FUNCTION "+"ambIva(import FLOAT) RETURNS FLOAT AS $$ "+"BEGIN RETURN import * 1.21; END; "+"$$ LANGUAGE plpgsql;");
```

*** Paràmetres d'entrada ***
Per a passar paràmetres d'entrada utilitzarem mètodes setXXX, un cop creat el CallableStatement. El funcionament d'aquests mètodes és idèntic als mètodes setXXX dels ResultSet modificables.

*** Paràmetres de sortida ***
Els paràmetres de sortida s'han de registrar abans de fer la crida al procediment emmagatzemat. Durant el registre indiquem el tipus de paràmetre que rebrem.
Després d'executar el procediment emmagatzemat es poden recollir els resultats amb els mètodes getXXX.

```java
CallableStatement cst = conn.prepareCall("{?=call ambIva(?)}");
cst.registerOutParameter(1, java.sql.Types.REAL);
cst.setFloat(2,10.0f);
cst.execute();
System.out.println(cst.getFloat(1));
```

## Exemple 14
```java
// $createlang plpgsql bdMail


import java.sql.*;
import java.io.*;

public class ex14StoredProcedureResultSet
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");
		conn.setAutoCommit(false);
		Statement st = conn.createStatement();

		//Creem un procediment emmagatzemat amb un paràmetre IN i un OUT
		st.executeUpdate("CREATE OR REPLACE FUNCTION consulta(cf INT) RETURNS refcursor AS $$ "+
			"DECLARE cur refcursor; BEGIN  OPEN cur FOR SELECT * FROM Missatges WHERE codiForum=cf; RETURN cur; END;$$ LANGUAGE plpgsql;");

		//... i l'executem
		CallableStatement cst = conn.prepareCall("{?=call consulta(?)}");
		cst.registerOutParameter(1, java.sql.Types.OTHER);
		cst.setInt(2,2);
		cst.execute();
		ResultSet rs=(ResultSet)cst.getObject(1);
		while (rs.next())
		{
			System.out.println(rs.getInt("codiForum")+"--"+rs.getInt("ordre")+"--"+rs.getString("titol"));
		}
		//tanquem resultset, statements i connexió
		rs.close();
		cst.close();
		st.close();
		conn.close();
	}
}
```

## Paràmetres d'entrada i sortida
Per aconseguir paràmetres d'entrada i sortida farem una combinació dels dos anteriors. Utilitzarem el mètode `setXXX` per a donar el valor d'entrada i, abans d'executar el procediment emmagatzemat, també el registrarem. Un cop acabat, obtindrem els paràmetres de sortida amb `getXXX`.

```java
st.executeUpdate("CREATE OR REPLACE FUNCTION "+
"ambIva2(INOUT import FLOAT) AS $$ "+
"BEGIN import:= import*1.21; END; "+
"$$ LANGUAGE plpgsql;");
cst = conn.prepareCall("{call ambIva2(?)}");
cst.registerOutParameter(1, java.sql.Types.REAL);
cst.setFloat(1,10.0f);
cst.execute();
System.out.println(cst.getFloat(1));
```

## Exemple 15
```java
// $createlang plpgsql bdMail


import java.sql.*;
import java.io.*;

public class ex015StoredProcedureIN_OUT
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");
		Statement st = conn.createStatement();

		//Creem un procediment emmagatzemat amb un paràmetre IN i un OUT
		st.executeUpdate("CREATE OR REPLACE FUNCTION ambIva(import FLOAT) RETURNS FLOAT AS $$ "+
			"BEGIN    RETURN import * 1.18; END;$$ LANGUAGE plpgsql;");

		//... i l'executem
		CallableStatement cst = conn.prepareCall("{?=call ambIva(?)}");
		cst.registerOutParameter(1, java.sql.Types.REAL);
		cst.setFloat(2,10.0f);
		cst.execute();
		System.out.println(cst.getFloat(1));

		//tanquem resultset, statements i connexió
		cst.close();
		st.close();
		conn.close();
	}
}
```

## Exemple 16
``` java
// $createlang plpgsql bdMail


import java.sql.*;
import java.io.*;

public class ex016StoredProcedureINOUT
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");
		Statement st = conn.createStatement();

		//Creem un procediment emmagatzemat amb un paràmetre IN i un OUT
		st.executeUpdate("CREATE OR REPLACE FUNCTION ambIva2(INOUT import FLOAT) AS $$ "+
			"BEGIN    import:= import*1.18; END;$$ LANGUAGE plpgsql;");

		//... i l'executem
		CallableStatement cst = conn.prepareCall("{call ambIva2(?)}");
		cst.registerOutParameter(1, java.sql.Types.REAL);
		cst.setFloat(1,10.0f);
		cst.execute();
		System.out.println(cst.getFloat(1));

		//tanquem resultset, statements i connexió
		cst.close();
		st.close();
		conn.close();
	}
}
```

# Gestió d'errors

Totes les aplicacions estan exposades a errors durant l'execució, però, si a més, estan connectades a una `BD`, les probabilitats augmenten.
Podem trobar-ne la raó pel fet de cooperar amb un sistema complex (l'`SGBD`), al qual ens connectem per xarxa i que, simultàniament, dóna servei a altres aplicacions.
Per si no fos prou, les sentències que enviem sovint són dinàmiques i, per tant, susceptibles d'errors només detectables en temps d'execució.

`JDBC` exposa els errors mitjançant excepcions facilitant d'una manera considerable el desenvolupament d'aplicacions. Com és habitual en Java, es defineix una jerarquia d'excepcions que deriven d'`Exception`. Al capdamunt d'aquesta jerarquia hi ha la classe `SQLException`.

Una gran part de les excepcions que genera `JDBC` són o deriven de `SQLException`, tot i que podrem explotar poc la jerarquia. Les excepcions que ens interessarà capturar són sobretot `SQLException` i, puntualment, alguna excepció derivada. Així doncs, rarament podrem tractar les excepcions a nivell de blocs `try-catch` i ho haurem de fer a partir de la informació que ens facilitaran els objectes `SQLException`.

## Obtenció d'informació sobre una SQLException

Els objectes `SQLException` exposen el tipus d'error generat mitjançant tres mètodes:

* ***getMessage()***: retorna una cadena amb informació textual de l'error. És el mètode estàndard heretat de la classe Exception.
* ***getSQLState()***: retorna una cadena de cinc caràcters amb un codi d'error definit a `XOPEN SQLState` o a `SQL:2003`. Haurem d'investigar quin estàndard segueix l'`SGBD` que utilitzem i els codis dels errors que ens interessa detectar.
* ***getErrorCode()***: retorna un enter que correspon al codi d'error. El significat d'aquest enter dependrà de l'`SGBD` al qual estem connectats.

```java
int codiMissatge;
String nomUsuari;
...
try {
   st.executeUpdate("INSERT INTO Lectures "+
   "VALUES("+codiMissatge+",'"+nomUsuari+"',"+
   "to_timestamp('"+ts+"','YYYY-MM-DD HH24:MI'))");
}
catch (SQLException e) {
   System.out.println("Missatge:"+e.getMessage());
   System.out.println("SQLState:"+e.getSQLState());
   System.out.println("ErrorCode:"+e.getErrorCode());
}
```

En aquest exemple, la sentència `INSERT` pot generar tres tipus d'error. Si l'`SGBD` utilitzat és el `PostgreSQL`, els resultats que podríem obtenir són:

1. La clau forana codi_missatge no identifica cap missatge.

	```console
	ERROR: insert or update on table "lectures" violates foreign key
	constraint "lectures_missatges_fkey"
	Detail: Key (codi_missatge)=(455) is not present in table "missatges".
	SQLState:23503
	ErrorCode:0
	```

2. La clau forana nom_usuari no identifica cap usuari.
	```console
	ERROR: insert or update on table "lectures" violates foreign key
	constraint "lectures_usuaris_fkey"
	Detail: Key (nom_usuari)=(sadf) is not present in table "usuaris".
	SQLState:23503
	ErrorCode:0
	```

3. Aquest usuari ja havia llegit el missatge: clau primària duplicada.

	```console
	ERROR: duplicate key value violates unique constraint "lectures_pkey"
	SQLState:23505
	ErrorCode:0
	```

Podem comprovar que el driver de `PostgreSQL` no retorna `ErrorCode`, només `SQLState`. Això no presenta cap problema, ja que des de l'estàndard `SQL:1992` es va proposar reportar els errors per mitjà de codis `SQLState`. Durant uns quants anys s'han mantingut les dues versions de codis, però la versió actual del driver `JDBC` de `PostgreSQL` ha prescindit de l'`ErrorCode`.

## Exemple 17
```java
import java.sql.*;
import java.io.*;

public class ex017SQLExceptionInfo
{
	public static void main( String[] args ) throws Exception
	{
		//Per llegir de teclat
		InputStreamReader stdin =new InputStreamReader(System.in);
		BufferedReader cons =new BufferedReader(stdin);

		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");
		Statement st = conn.createStatement();

		//llegim les dades
		int codiMissatge=Integer.parseInt(cons.readLine());
		String nomUsuari=cons.readLine();
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		try
		{
			st.executeUpdate("INSERT INTO Lectures VALUES("+codiMissatge+",'"+nomUsuari+"',"+
				"to_timestamp('"+ts+"','YYYY-MM-DD HH24:MI'))");
		}
		catch (SQLException e)
		{
			System.out.println("Missatge:"+e.getMessage());
			System.out.println("SQLState:"+e.getSQLState());
			System.out.println("ErrorCode:"+e.getErrorCode());
			throw e;
		}		

		//tanquem el ResultSet, Statement i Connection.
		st.close();
		conn.close();
	}

}
```

## Exemple 18
```java
import java.sql.*;
import java.io.*;

public class ex018TractamentException
{
	//Definició de codis SQLState
	private static final String FK_ERROR = "23503";
	private static final String PK_ERROR = "23505";

	//Definició dels noms de les contraints de claus foranes
	private static final String FK_LECTURES_USUARIS   = "lectures_usuaris_fkey";
	private static final String FK_LECTURES_MISSATGES = "lectures_missatges_fkey";


	public static void main( String[] args ) throws Exception
	{
		Connection conn=null;
		Statement st=null;

		//Per llegir de teclat
		InputStreamReader stdin =new InputStreamReader(System.in);
		BufferedReader cons =new BufferedReader(stdin);

		//Provem de connectar-nos a la base de dades i crear un statement
		try
		{
			Class.forName( "org.postgresql.Driver" );
			String dbURL="jdbc:postgresql:bdMail";
			conn = DriverManager.getConnection( dbURL, "usuari","1234");
			st = conn.createStatement();
		}
		catch (Exception e)
		{
			//tanquem el què calgui i rellancem l'error
			if (st!=null) st.close();
			if (conn!=null) conn.close();
			throw e;
		}

		//Si tot és correcte, demanem dades i executem INSERT.
		boolean correcte=false;
		while (!correcte)
		{
			try
			{
				//llegim les dades
				System.out.print("CodiMissatge:");
				int codiMissatge=Integer.parseInt(cons.readLine());
				System.out.print("NomUsuari:");
				String nomUsuari=cons.readLine();
				Timestamp ts = new Timestamp(System.currentTimeMillis());

				st.executeUpdate("INSERT INTO Lectures VALUES("+codiMissatge+",'"+nomUsuari+
					"',"+"to_timestamp('"+ts+"','YYYY-MM-DD HH24:MI'))");

				correcte=true;
			}
			catch (SQLException e)
			{
				if (e.getSQLState().equals(FK_ERROR))
					if (e.getMessage().indexOf(FK_LECTURES_MISSATGES)!=-1)
						System.out.println("Error: el codi de Missatge no existeix");
					else if (e.getMessage().indexOf(FK_LECTURES_USUARIS)!=-1)
						 System.out.println("Error: el nom de l'usuari no existeix");
					else System.out.println("Error inesperat:"+e.getMessage());
				else if (e.getSQLState().equals(PK_ERROR))
					System.out.println("Error: missatge ja llegit");
				else System.out.println("Error inesperat:"+e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println("Error llegint les dades");
			}		
		}

		//tanquem Statement i Connection.
		st.close();
		conn.close();
	}
}
```

# Gestió de l'error des dels nostres programes

Analitzant els `SQLState` obtinguts en els tres exemples anteriors podem distingir entre errors provocats per claus foranes o errors provocats per claus primàries. Tanmateix, per a discriminar quina de les claus foranes ha provocat l'error no ens queda més remei que analitzar el text obtingut a `getMessage`.

Ara ja podem definir el patró que seguirem per al tractament d'errors:

1. Definir el bloc try-catch del conjunt de sentències SQL de les quals volem tractar els errors. Com més gran sigui el conjunt, més difícil ens serà determinar-ne l'error exacte. Si amb un sol bloc no podem tractar bé els errors, n'haurem de definir més d'un, sovint, niuats.
2. Capturar els diferents tipus d'errors a partir de la classe d'excepció generada. Això ens permetrà diferenciar entre errors de drivers, connexió, etc., però no entre el tipus d'error SQL.
3. Per discriminar el tipus d'error SQL ens basarem en el codi SQLState.
4. Per acabar de determinar la causa exacta de l'error haurem d'analitzar la cadena obtinguda amb el mètode getMessage buscant alguna paraula clau que ens ajudi.

Un cop detectada la causa exacta de l'error, l'aplicació haurà de decidir entre reintentar l'operació, abandonar-la silenciosament (és a dir, ignorar-la) o aturar-la amb una excepció. Exceptuant el reintent, en les altres situacions és molt important tancar tots els objectes `ResultSet`, `Statement`, etc. que puguem tenir oberts. Així permetem que l'`SGBD` alliberi recursos ràpidament i que pugui atendre altres usuaris.

## Enllaç d'interès
Podeu trobar la llista de codis `SQLState` de `PostgreSQL` en l'annex A de la documentació: http://www.postgresql.org/docs/9.4/interactive/errcodes-appendix.html

Exemple per registrar la lectura d'un missatge per part d'un usuari
```java
Class.forName( "org.postgresql.Driver" );
String dbURL="jdbc:postgresql:bdMail";
Connection conn = DriverManager.getConnection(dbURL, "usuari","usuari");
Statement st = conn.createStatement();
st.executeUpdate("INSERT INTO Lectures VALUES("+ codiMissatge+",'"+nomUsuari+"',"+ "to_timestamp('"+ts+"','YYYY-MM-DD HH24:MI'))");
```

Les diverses línies d'aquest codi poden generar tipus d'excepcions diferents:

* Class.forName(...):
  * ClassNotFoundException: no trobem el driver. No està instal·lat o no hem definit el CLASSPATH.
* DriverManager.getConnection(...):
  * SQLException: hi ha un error connectant amb la BD: l'url, el port, l'usuari, la contrasenya, etc. són incorrectes.
* Connection.createStatement()
  * SQLException: la connexió està tancada, o els paràmetres per a definir el tipus de ResultSet són incorrectes.
  * SQLFeatureNotSupportedException: el driver no suporta aquesta operació, normalment deguda al tipus de ResultSet demanat.
* Statement.executeUpdate(...)
  * SQLException: error en la instrucció SQL. Serà la situació més habitual.
  * SQLFeatureNotSupportedException: en cas que el driver no suporti alguna funcionalitat. Per exemple, en el cas de PostgreSQL, la impossibilitat de retornar les columnes autogenerades.

Si ens interessa tractar totes les excepcions d'una manera particular, no en farem prou amb un sol bloc `try-catch`. Ara bé, exceptuant els errors provocats per `executeUpdate`, la resta normalment no s'haurien de produir. Són errors del sistema més que de l'aplicació i, si es produeixen, poca cosa podrem fer per solucionar-los.

Una opció correcta és no fer res d'especial en els errors de sistema. L'exemple anterior, tal com està, seria insuficient, ja que, en cas d'error, no es tancarien els objectes oberts. En l'exemple següent mostrem una possible estructuració del codi per garantir que, tant en cas d'èxit com d'error, tots els objectes oberts quedaran tancats. La utilització del bloc `finally` simplifica la codificació. Així mateix, dins del bloc `finally`, tots els mètodes estan protegits amb un bloc `try-catch` per garantir que, tot i que es produeixi un error, el bloc `finally` continua executant-se fins al final.

```java
try {
  Class.forName( "org.postgresql.Driver" );
  String dbURL="jdbc:postgresql:bdMail";
  conn = DriverManager.getConnection( dbURL,"usuari","usuari");
  st = conn.createStatement();
  //...
} catch (Exception e) {
    throw e;
} finally {
  //tanquem el que calgui
  if (st!=null) {
    try {
			st.close();
		} catch(SQLException e) {
         //...tractem l'error
    }
  }
  if (conn!=null) {
    try {
			conn.close();
		} catch(SQLException e) {
        //...tractem l'error
    }
  }
}
```

Els errors provocats per l'execució de sentències SQL sí que els tractarem. Si el problema és provocat per dades errònies que l'usuari ha entrat, és molt interessant donar un missatge d'error fàcil d'entendre per l'usuari final. I, sobretot, donar una segona oportunitat.

En aquest exemple llegim el codi d'un missatge, el nom d'un usuari i afegim una fila a la taula Lectures per reflectir que l'usuari en qüestió ha llegit el missatge en la data i l'hora actuals.

```java
boolean correcte=false;
while (!correcte) {
	try {
		//llegim les dades
		System.out.print("CodiMissatge:");
		codiMissatge=Integer.parseInt(cons.readLine());
		System.out.print("NomUsuari:");
		nomUsuari=cons.readLine();
		ts = new Timestamp(System.currentTimeMillis());
		st.executeUpdate("INSERT INTO Lectures "+
		"VALUES("+codiMissatge+",'"+nomUsuari+"',"+
		"to_timestamp('"+ts+"','YYYY-MM-DD HH24:MI'))");
		correcte=true;
	} catch (SQLException e) {
	if(e.getSQLState().equals(FK_ERROR))
		if(e.getMessage().indexOf(FK_MISSATGES)!=-1)
			System.out.println("codi de Missatge no existeix");
		else if(e.getMessage().indexOf(FK_USUARIS)!=-1)
			System.out.println("nom de l'usuari no existeix");
		else System.out.println("Inesperat:"+e.getMessage());
	else if (e.getSQLState().equals(PK_ERROR))
		System.out.println("Error: missatge ja llegit");
		else System.out.println("Inesperat:"+e.getMessage());
	} catch (Exception e) {
		System.out.println("Error llegint les dades");
	}
}
```

# Gestió de transaccions

Cada connexió `JDBC` pot mantenir una sola transacció activa. Quan creem una connexió, per defecte, es treballa en mode `autocommit`. Això significa que cada sentència `SQL` que executem constitueix una transacció que s'inicia abans de dur a terme la sentència i que, si no es genera cap error, acaba en commit en finalitzar (per això, el nom `autocommit`).


Treballar en mode autocommit és suficient quan cada transacció només incorpora una sentència SQL o quan sabem que no poden aparèixer problemes de concurrència (per exemple, quan estem segurs que només nosaltres estem treballant amb la BD o quan sabem que totes les operacions que s'executen sobre la BD són operacions de només lectura). Quan hàgim de treballar amb transaccions que incorporen més d'una sentència SQL, o quan tinguem dubtes en relació amb les operacions que s'executen d'una manera concurrent amb les operacions que duen a terme les nostres aplicacions, és recomanable treballar amb el mode autocommit desactivat.

Amb el mode autocommit desactivat, les transaccions s'acaben explícitament cridant els mètodes `commit()` o `rollback()` i comencen implícitament en executar la primera sentència després d'un commit o un rollback. Això permet incloure diferents sentències `SQL` dins d'una transacció, tirar-les totes endarrere amb un rollback o acceptar-les totes amb un `commit`.

```java
conn.setAutoCommit(false);
...
try
{
... sentències SQL que formen la transacció
conn.commit();
}
catch (SQLException e)
{
conn.rollback();
}
```

En la nostra BD de referència tenim una taula per a registrar els missatges que han llegit els usuaris. Cada cop que un usuari llegeix un missatge haurem d'actualitzar la taula Lectures amb un codi semblant a aquest.

```java
st.executeUpdate("INSERT INTO Lectures "+"VALUES("+codiMissatge+",'"+nomUsuari+"',"+"to_timestamp('"+ts+"','YYYY-MM-DD HH24:MI'))");
rs=st.executeQuery("SELECT * FROM Missatges "+"WHERE codi_missatge="+codiMissatge);
if (rs.next())
System.out.println(rs.getString("titol")+"--"+rs.getString("text"));
```

Aquest codi sembla correcte i, de fet, ho és sempre que no es produeixi cap error. Ara bé, si mai es produeix un error després d'haver inserit la fila i abans de llegir el missatge, deixarem la BD en un estat incorrecte. Per recuperar l'estat hauríem de tractar l'error i fer un `DELETE` de la fila, però aquest `DELETE` també podria fallar i aleshores es complicaria la recuperació de l'estat.

La solució òptima per a aquest escenari és la utilització de transaccions. En l'exemple següent definim una transacció que comprèn les operacions de registrar la lectura del missatge i llegir el missatge en si. Si aconseguim registrar la lectura, llegir el missatge i mostrar-lo per pantalla, finalitzem la transacció amb `commit`. En cas contrari, desfem la transacció i tornem a l'estat inicial.

```java
conn.setAutoCommit(false);
...
try {
	st.executeUpdate("INSERT INTO Lectures "+"VALUES("+codiMissatge+",'"+nomUsuari+"',"+"to_timestamp('"+ts+"','YYYY-MM-DD HH24:MI'))");
	rs=st.executeQuery("SELECT * FROM Missatges "+"WHERE codi_missatge="+codiMissatge);

	if (rs.next()) System.out.println( rs.getString("titol")+ "--"+rs.getString("text"));
	else throw new SQLException();

	rs.close();
	conn.commit();
} catch (SQLException e) {
	conn.rollback();
}
```

## Exemple 19
```java
import java.sql.*;
import java.io.*;

public class ex019MissatgesLlegitsTransaccio
{
	public static void main( String[] args ) throws Exception
	{
		//Per llegir de teclat
		InputStreamReader stdin =new InputStreamReader(System.in);
		BufferedReader cons =new BufferedReader(stdin);

		Class.forName( "org.postgresql.Driver" );
		String dbURL="jdbc:postgresql:bdMail";
		Connection conn = DriverManager.getConnection( dbURL, "usuari","1234");
		Statement st = conn.createStatement();


		//llegim les dades
		int codiMissatge=Integer.parseInt(cons.readLine());
		String nomUsuari=cons.readLine();
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		//Desactivem els commits automàtics		
		conn.setAutoCommit(false);

		try
		{
			st.executeUpdate("INSERT INTO Lectures VALUES("+codiMissatge+",'"+nomUsuari+"',"+
				"to_timestamp('"+ts+"','YYYY-MM-DD HH24:MI'))");
			ResultSet rs=st.executeQuery("SELECT * FROM Missatges "+
					"WHERE codi_missatge="+codiMissatge);
			if (rs.next()) System.out.println(rs.getString("titol")+"--"+rs.getString("text"));
			else throw new SQLException();
			rs.close();
			conn.commit();
		}
		catch (SQLException e)
		{
			conn.rollback();
		}		

		//reactivem l'autocommit
		conn.setAutoCommit(true);

		//tanquem el ResultSet, Statement i Connection.
		st.close();
		conn.close();
	}
}
```

## Durada d'una transacció
Quan treballem amb el mode `autocommit` desactivat, cal tenir present que la durada d'una transacció convé que sigui tan curta com sigui possible. O, més ben dit, interessa que no sigui més llarga del que és estrictament necessari.

Les sentències `SQL` que s'executen durant una transacció poden afectar diverses files de taules de la `BD` diferents. Depenent de la gestió interna que fa l'`SGBD`, aquestes files o taules poden quedar bloquejades. No és fins al final de la transacció que l'`SGBD` allibera les files o taules del bloqueig. Mentrestant, sentències `SQL` que s'estiguin executant concurrentment i que afectin alguna fila o taula bloquejades poden quedar en pausa i, fins que no s'acaba la transacció, no continuen l'execució.

És a dir, com més llarga sigui l'execució d'una transacció, més probabilitats hi ha d'aturar temporalment l'execució d'altres aplicacions que accedeixin simultàniament a la `BD`. Per això és tan important que les transaccions durin estrictament el temps necessari.

# Pràctiques

## Pràctica 1: Hemeroteca
## Practica 2: Hospital
## Pràctica 3: ACB

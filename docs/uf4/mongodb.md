# MongoDB, una base de dades documental
* [Objectius generals](#objectius-generals)
* [Introducció a MongoDB](#introduccio-a-mongodb)
* [Característiques i avantatges](#caracteristiques-i-avantatges)
  * [Noves funcionalitats de MongoDB 5](#noves-funcionalitats-mongodb-5)
* [JSON: Javascript Object Notation](#json)
* [BSON](#bson)
  * [Per què BSON?](#per-que-bson)

* [Instal·lació de MongoDB 5 a Ubuntu 20.04](#installacio-mongodb)
  * [Pas 1: importa la clau GPG de MongoDB](#installacio-mongodb-pas1)
  * [Pas 2: afegeix el repositori](#installacio-mongodb-pas2)
  * [Pas 3: instal·la el paquet](#installacio-mongodb-pas3)
  * [Pas 4: arrenca el servei](#installacio-mongodb-pas4)
  * [Pas 5: configura la base de dades](#installacio-mongodb-pas5)
    * [Habilita l'autenticació de contrasenya](#habilita-autenticacio-contrasenya)
    * [Habilita l'accés des d'una màquina remota](#habilita-acces-maquina-remota)
  * [Pas 6: Creació d'usuaris i bases de dades](#creacio-usuaris-bases-dades-pas6)
    * [Importar una col·lecció](#importa-collecio)
    * [insertOne i insertMany](#insertone-insertmany)
  * [Pas 7: Afinació de MongoDB](#afinacio-mongodb-pas7)

* [Data-Sets](#datasets)
* [Exercicis](#exercicis)

# Objectius generals <a name="objectius-generals"></a>
* Conèixer les operacions bàsiques per tal de realitzar operacions `CRUD` amb `MongoDB`.
* Poder crear programes en `Java` per tal d'accedir a la informació de `MongoDB`.

Segurament hagis sentit parlar de les bases de dades `NoSQL` `(Not Only SQL)`. És una cosa que últimament està molt de moda en el món del desenvolupament.

Resumint les bases de dades `NoSQL`, difereixen en diversos punts a les bases de dades relacionals de tota la vida com són: que no fan servir `SQL` ja que no són bases de dades relacionals, que no es necessiten estructures fixes (taules, columnes etc.) i en general no suporten `ACID` (atomicitat, consistència, aïllament, durabilitat).

En el nostre cas parlarem de bases de dades documentals. Tot i que aquesta primera presa de contacte es centrarà més en les bases de dades documentals, ho farem tenint en compte que en el futur farem servir `MongoDB`.

# Introducció a MongoDB <a name="introduccio-a-mongodb"></a>

`MongoDB` és una base de dades de documents de propòsit general de codi obert.
`MongoDB` (de "humongous") és una base de dades de documents de codi obert i el líder `NoSQL` de base de dades, escrita en C++.
`MongoDB` és una base de dades de documents de codi obert que ofereix un alt rendiment, alta disponibilitat i escalat automàtic.
`MongoDB` és una base de dades de documents de codi obert dissenyada per facilitar el desenvolupament i escalat.
L'empresa de programari `10gen` va començar a desenvolupar `MongoDB` el 2007 com a component d'una plataforma planificada com a producte de servei.
El 2009, l'empresa va passar a un model de desenvolupament de codi obert, amb l'empresa oferint suport comercial i altres serveis. El 2013, `10gen` va canviar el seu nom a `MongoDB Inc`.

`MongoDB` és un dels populars servidors de bases de dades `NoSQL` utilitzats per desenvolupar aplicacions dinàmiques modernes. Fa ús de documents semblants a `JSON` i esquemes opcionals; objectes de dades emmagatzemats com a documents separats dins d'una col·lecció, a diferència de les files i columnes utilitzades a les bases de dades relacionals tradicionals.

`MongoDB` és altament escalable i flexible i ofereix consulta i indexació fàcils per als desenvolupadors. A causa de les seves capacitats d'escala horitzontal i d'equilibri de càrrega, `MongoDB` ha ofert als desenvolupadors els nivells més alts de flexibilitat i escalabilitat. `MongoDB Atlas` és un servei de bases de dades al núvol i és líder en el desenvolupament d'aplicacions modernes i pot implementar bases de dades al núvol totalment gestionades a Azure, AWS o Google Cloud.

`MongoDB` ofereix tant una edició comunitària que es pot descarregar i utilitzar gratuïtament com una edició Enterprise que forma part de la subscripció avançada de `MongoDB Enterprise`. Aquesta versió Enterprise inclou un suport complet per al vostre desplegament de MongoDB i ofereix funcions enfocades a l'empresa, com ara suport LDAP i Kerberos, xifratge en disc i auditoria.


# Característiques i avantatges <a name="caracteristiques-i-avantatges"></a>
* Open source
* Escalable
* Document Data Model

```
{
  nom: "John Smith",
  pfxs: [“Dr.”,”Sr.”],
  adreça: "10 3rd St.",
  telèfons: [
    { número: "555-1212",
      escriviu: "casa"},
    { número: "444-1212",
      escriviu: "mòbil" }
    ]
}
```

`MongoDB` és una base de dades de documents de propòsit general de codi obert.
* Un registre a MongoDB és un document, que és una estructura de dades composta de camps i valors. Els documents `MongoDB` són similars als objectes `JSON`. Els valors dels camps poden incloure altres documents, matrius i matrius de documents.

```
{
  _id: “123”,
  title: "MongoDB: The Definitive Guide",
  authors: [
    { _id: "kchodorow", name: "Kristina Chodorow“ },
    { _id: "mdirold", name: “Mike Dirolf“ }
  ],
  published_date: ISODate(”2010-09-24”),
  pages: 216,
  language: "English",
  publisher: {
    name: "O’Reilly Media",
    founded: 1980,
    locations: ["CA”, ”NY” ]
  }
}
```

* En lloc d'emmagatzemar dades en files i columnes com ho faria amb una base de dades relacional, `MongoDB` utilitza un model de dades de document i emmagatzema un format binari de documents `JSON` anomenat `BSON`.
* Els documents contenen un o més camps i cada camp conté un valor d'un tipus de dades específic, incloent matrius i dades binàries. Els documents s'emmagatzemen en col·leccions, i les col·leccions s'emmagatzemen a les bases de dades.
* No hi ha esquemes fixos a `MongoDB`, de manera que els documents poden variar en estructura i poden adaptar-se dinàmicament.
* Pot ser útil pensar en els documents com a equivalents aproximats a les files d'una base de dades relacional; i els camps com a equivalents a columnes i les col·leccions com a taules.

| RDBMS    | MongoDB  |
|----------|----------|
| Database | Database |
| Table    | Collection |
| Index    | Index    |
| Row      | Document |
| Column   | Field    |
| Join     | Embedding & Linking & $lookup |

* Ofereix una gran escalabilitat i flexibilitat; error automàtic i redundància de dades.
* Ofereix un llenguatge de consulta expressiu que és fàcil d'aprendre i utilitzar.
* Consultes ad-hoc per a analítiques en temps real.
* Admet matrius i objectes imbricats com a valors i permet esquemes flexibles i dinàmics.
* És fàcil redactar consultes que permeten ordenar i filtrar, sense importar com estiguin imbricades, i admet l'agregació, la geolocalització, les sèries temporals, la cerca de gràfics i molt més.
* Admet la fragmentació que permet dividir grans conjunts de dades en múltiples col·leccions distribuïdes que, a continuació, faciliten les consultes.
* Admet diversos motors d'emmagatzematge.
* També inclou un llenguatge de consulta ric; modificadors d'actualització atòmica; cerca de text; el marc d'agregació per a l'anàlisi similar a les operacions `SQL GROUP BY`; i `Map-Reduce` per a una anàlisi complexa i local.
* Ofereix suport complet per a índexs, inclosos els secundaris, compostos i
índexs geoespacials.
* També proporciona controladors nadius i idiomàtics per a tota la programació popular llenguatges i marcs per fer el desenvolupament natural.
* La rèplica integrada amb migració automàtica per error proporciona una alta disponibilitat.
* La fragmentació automàtica permet l'escala horitzontal per a grans desplegaments.


## Noves funcionalitats de MongoDB 5.0 <a name="noves-funcionalitats-mongodb-5"></a>

`MongoDB 5.0` és l'última versió publicada el 13 de juliol de 2021. Hi ha una sèrie de correccions fetes a partir de les versions anteriors i també algunes funcions addicionals que les podeu trobar totes a les notes de llançament oficials de `MongoDB 5.0`. Algunes de les característiques addicionals inclouen:

* Introducció de col·leccions de sèries temporals que emmagatzemen de manera eficient seqüències de mesures durant un període de temps.
* Obsoleta de l'operació de reducció de mapes.
* Introducció de nous operadors d'agregació com ara `$dateAdd`, `$dateDiff`, `$dateSubtract`, `$getField`, `$rand` i molts més. Consulteu la documentació.
* Introducció de l'etapa de pipeline `$setWindowFields` que permet realitzar operacions en un interval especificat de documents d'una col·lecció, coneguda com a finestra.
* Afegeix la capacitat de configurar filtres d'auditoria en temps d'execució.


# JSON: Javascript Object Notation <a name="json"></a>
`MongoDB` utilitza `JSON` (o millor dit, `BSON`) per tractar amb documents.

Però què és exactament JSON? Mireu el següent exemple senzill:

```
{
  "name"  : "John",
  "age"   : 33,
  "great" : true
}
```

Si sou un desenvolupador de `JavaScript`, us hauria de semblar familiar.
Un document `JSON` pot tenir una o més parelles `clau - valor`. Les claus són sempre cadenes i sempre han d'anar entre cometes (a diferència de JavaScript, on hi ha cometes de vegades opcional).

Els valors poden ser d'un dels tipus següents:
1. `Número (Number)`: format de coma flotant de doble precisió, segons la implementació.
2. `Cadena (String)`: Unicode entre cometes dobles, amb barra invertida escapada.
3. `Booleà (Boolean)`: true o false.
4. `Matriu (Array)`: una seqüència de valors ordenada, separada per comes, tancada en quadrat claudàtors.
5. `Objecte (Object)`: una col·lecció de claus no ordenada i separada per comes: parells de valors tancats amb claus.
6. `null`: buit.

# BSON <a name="bson"></a>

`MongoDB` és un sistema gestor de base de dades orientat a documents. El que vol dir, en llenguatge d'estar per casa, que el que guardem a la base de dades són documents. `MongoDB` guarda els documents en `BSON`, que no és més que una implementació binària del conegut `JSON`. Per tant tots els documents guardats a la base de dades es poden tractar com faríem en `JavaScript`. De fet, ja anirem veient que per a realitzar consultes, a la consola de `MongoDB` utilitzarem `JavaScript`.

Realment no necessiteu saber res sobre `BSON` per treballar amb `MongoDB`, així que sentiu-ho lliure d'ometre aquesta secció, però si, com jo, us pregunteu sobre el rendiment i "com coses funcionen" segueix llegint.

## Per què BSON? <a name="per-que-bson"></a>

Un dels motius és perquè es pot escanejar ràpidament.
Tenint en compte que `JSON` és només una cadena, per trobar una clau específica que necessiteu escanejar cada caràcter d'aquesta cadena, fent un seguiment del nivell de nidificació, fins que trobes aquesta clau específica. Poden ser tones de dades que cal escanejar.
`BSON`, però, emmagatzema la longitud dels valors per tal de trobar aquesta clau específica només pot saltar els valors passats i llegir la següent clau.
http://bsonspec.org/

Un document `BSON` comença amb la longitud del document, en aquest cas `23 bytes`. S'emmagatzemen com a nombres enters de `32 bits`, `little endian`, de manera que `"23"` s'emmagatzemaria realment com `\x17\x00\x00\x00`.
Mentre que per a la llegibilitat vaig escriure `23`, les longituds de les notes són de `4 bytes` ja que són de `32 bits` nombres enters.
Aleshores, per a cada clau: parell de valors, `BSON` especifica el tipus de valor com a clau d'un sol byte com a cadena acabada nul·la, la longitud del valor com a nombre enter de `32 bits` si escau i el valor mateix.
Els documents, les matrius i les cadenes tenen una terminació nul·la.

* ***Tutorial***: http://www.tutorialspoint.com/json/index.htm

Pregunta: Quin és el `JSON` corresponent per al document `XML` següent?

```xml
<person>
  <name>John</name>
  <age>25</age>
  <address>
    <city>New York</city>
   <postalCode>10021</postalCode>
  </address>
  <phones>
    <phone type="home">212-555-1234</phone>
    <phone type="mobile">646-555-1234</phone>
  </phones>
</person>
```
```json
{ "name" : "John",
  "age" : 25,
  "address" : { "city" : "New York", "postalCode" : "10021" },
  "phones" : [
    {"phone":"212-555-1234", "type" : "home"},
    {"phone":"646-555-1234", "type" : "mobile"}
  ]
}
```

Els documents es guarden en col·leccions, que podria assemblar-se a les taules que coneixem dels sistemes relacionals. La diferència principal és que els documents no tenen perquè tenir els mateixos camps. Potser un document tingui un camp que no existeix en un altre, i fins i tot els tipus de dades poden ser diferents.

Com es pot veure, no tenim un esquema definit, de manera que l'enfocament que utilitzarem amb `MongoDB` és totalment diferent al que utilitzaríem amb un `RDBMS`. En no existir les relacions directament, hem de pensar una mica en com anem guardar els documents per no sobrecarregar la nostra base de dades fent consultes massa grans o duplicant consultes.


# Instal·lació de MongoDB 5.0 a Ubuntu 20.04 <a name="installacio-mongodb"></a>

Els passos següents ens guiaran sobre com podem instal·lar MongoDB 5.0 a Ubuntu 20.04

## Pas 1: importa la clau GPG de MongoDB <a name="installacio-mongodb-pas1"></a>

Primer hem d'importar la clau `GPG` pública de `MongDB` com es mostra a continuació:

```
$ wget -qO - https://www.mongodb.org/static/pgp/server-5.0.asc | sudo apt-key add -
OK
```

En cas que rebeu un error en què falta gnup, podeu instal·lar-lo executant l'ordre següent i, a continuació, torneu a intentar afegir la clau.

```
sudo apt update
sudo apt-get install gnupg
```

## Pas 2: afegeix el repositori <a name="installacio-mongodb-pas2"></a>

Per poder instal·lar `MongoDB` amb apt, hem d'afegir el repositori `MongoDB`.

```
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/5.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-5.0.list
```

## Pas 3: instal·la el paquet <a name="installacio-mongodb-pas3"></a>

Ja hem afegit MongoDB GPG i repositori, procedim a instal·lar MongoDB. Actualitzeu primer els paquets

```
sudo apt-get update
sudo apt-get install -y mongodb-org
```

Si voleu instal·lar una versió específica de `MongoDB`, podeu passar la versió tal com es mostra a continuació.

```
sudo apt-get install -y mongodb-org=5.0.2 mongodb-org-database=5.0.2 mongodb-org-server=5.0.2 mongodb-org-shell=5.0.2 mongodb-org-mongos=5.0.2 mongodb-org-tools=5.0.2
```

## Pas 4: arrenca el servei <a name="installacio-mongodb-pas4"></a>

Un cop finalitzat el procés d'instal·lació, procediu a iniciar i habilitar MongoDB.

```
sudo systemctl start mongod
```

Confirmeu que MongoDB s'està executant realment.

```
$ systemctl status mongod
● mongod.service - MongoDB Database Server
     Loaded: loaded (/lib/systemd/system/mongod.service; disabled; vendor prese>
     Active: active (running) since Tue 2021-08-24 15:42:58 UTC; 11s ago
       Docs: https://docs.mongodb.org/manual
   Main PID: 14497 (mongod)
     Memory: 61.6M
     CGroup: /system.slice/mongod.service
             └─14497 /usr/bin/mongod --config /etc/mongod.conf

Apr 22 13:12:27 elpuig-ubuntu systemd[1]: Started MongoDB Database Server.
```

Confirma la versió de `MongoDB`
```

$ mongod --version
db version v5.0.2
Build Info: {
    "version": "5.0.2",
    "gitVersion": "6d9ec525e78465dcecadcff99cce953d380fedc8",
    "openSSLVersion": "OpenSSL 1.1.1f  21 Apr 2021",
    "modules": [],
    "allocator": "tcmalloc",
    "environment": {
        "distmod": "ubuntu2004",
        "distarch": "x86_64",
        "target_arch": "x86_64"
    }
}
```

A continuació, podeu habilitar MongoDB perquè s'iniciï automàticament en reiniciar el sistema.

```
$ sudo systemctl enable mongod
Created symlink /etc/systemd/system/multi-user.target.wants/mongod.service → /lib/systemd/system/mongod.service.
```

## Pas 5: configura la base de dades <a name="installacio-mongodb-pas5"></a>

El fitxer de configuració de `MongoDB` es troba a `/etc/mongod.conf`. Aquí trobareu les configuracions per a la ruta `db`, la ruta dels registres. Sempre podeu fer canvis a aquest fitxer en funció de les vostres necessitats d'instal·lació. Reinicieu el servei `mongod` cada vegada que feu un canvi al fitxer.

### Habilita l'autenticació de contrasenya <a name="habilita-autenticacio-contrasenya"></a>

Activem l'autenticació de contrasenya de `MongoDB` perquè els usuaris puguin iniciar sessió amb una contrasenya per llegir o editar la base de dades. Descomenteu `#security` i afegiu el contingut com a continuació:

```
security:
  authorization: enabled
```

Després d'això, reinicieu el servei `mongod`.

```
sudo systemctl restart mongod
```

### Habilita l'accés des d'una màquina remota <a name="habilita-acces-maquina-remota"></a>

Per defecte, `MongoDB` només es pot accedir localment. Si voleu poder accedir a la base de dades de forma remota, farem un petit canvi al fitxer de configuració per incloure l'adreça `IP` o el nom d'amfitrió del servidor `MongoDB`, tal com es mostra a continuació.

```
# network interfaces
net:
  port: 27017
  bindIp: 127.0.0.1,mongodb-server-ip/hostname
```

Desa els canvis i reinicia el servei `mongod`. També heu de permetre al tallafoc les adreces `IP` remotes de confiança si heu habilitat el vostre tallafoc.

```
sudo ufw allow from trusted-server-ip to any port 27017
```

## Pas 6: Creació d'usuaris i bases de dades <a name="creacio-usuaris-bases-dades-pas6"></a>

Per accedir a l'intèrpret d'ordres de `MongoDB`, executeu l'ordre `mongosh` al terminal tal com es mostra.

```
$ mongosh
Using MongoDB:		5.0.2
Using Mongosh:		1.0.5

For mongosh info see: https://docs.mongodb.com/mongodb-shell/
..........
>
```

Per llistar les bases de dades existents executeu:

```
> show dbs
admin       135 kB
config      111 kB
local      73.7 kB
```

Per crear un usuari i afegir un rol a `MongoDB`

Vegem com podem crear un usuari a `MongoDB` i donar-li rols d'administrador i permís per a totes les bases de dades:

Crea un usuari `admin`:

```
use admin
db.createUser(
  {
    user: 'admin',
    pwd: 'password',
    roles: [ { role: 'root', db: 'admin' } ]
  }
);
exit;
```

Entrem amb l'usuari `admin` que acabem de crear:

```
$ mongosh -u admin -p
```

Creem un altre usuari:

```
> db.createUser({
        user: "rusben",
        pwd:  "password",
        roles: [{role: "userAdminAnyDatabase" , db: "admin"}]
});
{ ok: 1 }
```

Per permetre a un usuari tenir permís per a una base de dades específica.

```
db.createUser({
        user: "john",
        pwd:  "123456789",
        roles: [{role: "userAdmin" , db: "employees"}]
});
```

### Importar una col·lecció <a name="importa-collecio"></a>

```
mongoimport --db activitat1 --collection people --authenticationDatabase admin --username admin --password password --drop --file /vagrant/persons.json
```

[persons.json](datasets/persons.json)


Per crear una nova base de dades a `MongoDB`, utilitzem l'ordre `use`. `MongoDB` no té l'ordre de creació. Com a exemple, creeu una nova base de dades anomenada `prova`.

```
> use employees
switched to db employees
```

Heu d'afegir informació a la base de dades perquè aparegui a la llista quan enumereu les bases de dades.

```
db.Employees.insertOne({name: "hale" , age: 20 , department: "IT"})
{
  acknowledged: true,
  insertedId: ObjectId("61252c7492f10ccae358d787")
}
```

### insertOne i insertMany <a name="insertone-insertmany"></a>

Tingueu en compte que `Collection.insert()` està obsolet i hauríeu d'utilitzar `insertOne`, `insertMany` o `bulkWrite`.

Ara, quan mostreu les bases de dades, també hauríeu d'aparèixer la vostra base de dades nova.

```
> show dbs
admin      0.000GB
config     0.000GB
employees  0.000GB
local      0.000GB
```

## Pas 7: Afinació de MongoDB <a name="afinacio-mongodb-pas7"></a>

Un cop hàgiu instal·lat `MongoDB`, és important assegurar-vos que el seu rendiment estigui en configuracions òptimes. A mesura que la informació augmenta, la nostra instal·lació de `MongoDB` hauria de poder gestionar-la i processar-la com s'esperava. L'escala es pot produir tant horitzontalment com verticalment. L'escalat horitzontal significa l'addició de recursos del servidor com ara `RAM` i `CPU`, mentre que l'escalat vertical és la introducció de més servidors a la nostra instal·lació.

Un escalat vertical, que és molt crucial en el rendiment global de `MongoDB`, està influenciat per certs factors, com ara: ús de memòria, nombre de connexions concurrents i `WiredTiger Cache`, entre d'altres. La memòria és un factor important que influeix molt en el rendiment de `MongoDB`. `MongoDB` utilitza `WiredTiger` com a motor d'emmagatzematge predeterminat i, per tant, conserva el 50% de (la memòria disponible -1) per a `WiredTiger`.

Per exemple, un servidor amb 8 GB de `RAM` tindrà un conservador de memòria de 0,5 * (8-1) per a `WiredTiger`. Per comprovar les estadístiques d'ús de la memòria cau i determinar si cal fer canvis, executeu l'ordre següent:

```
> db.serverStatus().wiredTiger.cache
{
  'application threads page read from disk to cache count': 6,
  'application threads page read from disk to cache time (usecs)': 46,
  'application threads page write from cache to disk count': 184,
  'application threads page write from cache to disk time (usecs)': 10501,
  'bytes allocated for updates': 65768,
  'bytes belonging to page images in the cache': 30285,
  'bytes belonging to the history store table in the cache': 571,
  'bytes currently in the cache': 104652,
  'bytes dirty in the cache cumulative': 2813442,
  'bytes not belonging to page images in the cache': 74366,
  'bytes read into cache': 28042,
  'bytes written from cache': 1283385,
  'cache overflow score': 0,
  'checkpoint blocked page eviction': 0,
  'checkpoint of history store file blocked non-history store page eviction': 0,
  'eviction calls to get a page': 2,
  'eviction calls to get a page found queue empty': 2,
  'eviction calls to get a page found queue empty after locking': 0,
  'eviction currently operating in aggressive mode': 0,
  'eviction empty score': 0,
  'eviction passes of a file': 0,
  'eviction server candidate queue empty when topping up': 0,
  'eviction server candidate queue not empty when topping up': 0,
  'eviction server evicting pages': 0,
  'eviction server slept, because we did not make progress with eviction': 0,
  'eviction server unable to reach eviction goal': 0,
  'eviction server waiting for a leaf page': 0,
  'eviction state': 64,
  'eviction walk target pages histogram - 0-9': 0,
  'eviction walk target pages histogram - 10-31': 0,
  'eviction walk target pages histogram - 128 and higher': 0,
  'eviction walk target pages histogram - 32-63': 0,
  'eviction walk target pages histogram - 64-128': 0,
  'eviction walk target pages reduced due to history store cache pressure': 0,
  'eviction walk target strategy both clean and dirty pages': 0,
  'eviction walk target strategy only clean pages': 0,
  'eviction walk target strategy only dirty pages': 0,
  'eviction walks abandoned': 0,
..............
```

Dels resultats anteriors, són de gran importància:

* `wiredTiger.cache.maximum` bytes configurat
* `wiredTiger.cache.bytes` actualment a la memòria cau
* `wiredTiger.cache.tracked` bytes bruts a la memòria cau
* `wiredTiger.cache.pages` llegit a la memòria cau
* `wiredTiger.cache.pages` escrites des de la memòria cau

Les mides actuals de l'anterior ens haurien de permetre prendre una decisió sobre si volem augmentar la mida del `WiredTiger` als nostres servidors. Una altra cosa igualment important és l'ús de bitllets de lectura i escriptura de concurrència de `WiredTiger`, que es pot comprovar de la següent manera:

```
> db.serverStatus().wiredTiger.concurrentTransactions
{
  write: { out: 0, available: 128, totalTickets: 128 },
  read: { out: 1, available: 127, totalTickets: 128 }
}
```

Si observeu que els números augmenten i tendeixen cap a aquest nombre disponible de nuclis, és possible que els vostres servidors s'apropin a la saturació de la `CPU`.

# Data-Sets <a name="datasets"></a>

[Foursquare](datasets/foursquare.zip)

[imbd](datasets/imdb.zip)

[edx](datasets/edx.zip)

[digg](datasets/digg.zip)

[persons](datasets/persons.json)

[products](datasets/products.json)

# Exercicis <a name="exercicis"></a>
1. Incorporar les dades del fitxer persons.json a una base de dades anomenada activitat1 i a la col·lecció `people`
```
./mongoimport db activitat1 collection people < /home/usuari/persons.json --jsonArray
```

2. Ens connectem al servidor mongo a la base de dades activitat1
```
./mongo localhost/activitat1
```

3. Mostrem tot el contingut de la col·lecció people
```
db.people.find()
```

4. Mostrem tot el contingut de la col·lecció people d'una manera més llegible.
```
db.people.find().pretty()
```

5. Mostrem les persones de 34 anys d'una manera llegible
```
db.people.find({age : 34}).pretty()
```

6. Mostrem les persones de 34 anys i que siguin actius.
```
db.people.find({age : 34, isActive : true}).pretty()
```

7. Mostrem el nom, l'edat i si són actius de les persones de 34 anys que siguin actius.
```
db.people.find( {age : 34, isActive : true}, {name : 1, age : 1})
```

8. Mostrem el nom, l'edat i si són actius de les persones de 34 anys que siguin actius però desactivant el camp _id de la projecció.
```
db.people.find( {age : 34, isActive : true}, {name : 1, age : 1, _id : 0})
```

9. Mostra una persona que compleixi els requeriments anteriors.
```
db.people.findOne( {age : 34, isActive : true}, {name : 1, age : 1, _id : 0})
```

10. Mostrem el nom i la edat de les persones que tenen més de 30 anys
```
db.people.find( {age : {$gt : 30}}, {name : 1, _id : 0})
```

11. Mostrem el nom i la edat de les persones que tenen 30 o més anys
```
db.people.find({age : { $gte : 30}},{name : 1, age : 1, _id : 0})
```

12. Mostrem el nom i la edat de les persones menors de 30 anys
```
db.people.find({age : { $lt : 30}},{name : 1, age : 1, _id : 0})
```

13. Mostrem el nom i la edat de les persones que no tenen 30 anys
```
db.people.find({age : { $ne : 30}},{name : 1, age : 1, _id : 0})
```

14. Mostrem el nom i la edat de les persones que tenen 25, 30 o 35 anys
```
db.people.find({age : {$in : [25,30,35]}},{name : 1, age : 1, _id : 0})
```

15. Busquem els documents el camp gender sigui "female" i el camp age sigui més gran que 20.
```
db.people.find({gender : "female", age : {$gt : 20}})
```

16. Busquem els documents el camp gender sigui "female" o el camp age sigui més gran que 20
```
db.people.find({$or : [{gender : "female"}, {age :{$gt : 20}}]},{})
```

17. Busquem els documents el camp gender sigui "female" i el camp age sigui més gran que 20 utilitzant l'operador $and.
```
db.people.find({$and : [{gender : "female"}, {age :{$gt : 20}}]},{})
```

18. Busca les persones a people en que la seva edat és més gran que 30, o el gènere és "female" i la seva edat més gran que 50.
```
db.people.find({$or: [{age : {$gt:30}}, {gender : "female"}]}, {})
```

19. Busquem les persones edat NO sigui més gran que 30 i el camp isActive NO sigui true.
```
db.people.find({$nor : [{age : {$gt : 30}}, {isActivate : true}]},{ name : 1, age:1,isActive:1})
```

20. Busca l'element "laborum" a l'array tags, retornant les persones en el que en aquest array existeixi aquest element
```
db.people.find({tags : "laborum"},{name : 1, _id : 0})
```

21. Volem trobar totes les persones que continguin en tags els valors laborum i sunt.
```
db.people.find({$and : [{tags : "laborum"}, {tags : "sunt"}]}, {name : 1})
db.people.find({tags:{$all:["laborum","sunt"]}},{name:1,age:1,tags:1})
```

22. Volem trobar totes les persones que continguin en tags alguns dels valors laborum, sunt, nisi
```
db.people.find({tags:{$in:["laborum","sunt","nisi"]}},{name:1,age:1,tags:1})
```

23. Volem trobar totes les persones que NO continguin a l'array tags alguns dels valors especificats: laborum, sunt i, nisi
```
db.people.find({tags:{$nin:["laborum","sunt","nisi"]}},{name:1,age:1,tags:1})
```

24. Retornar tots els documents on l'array tags té una mida de 3 elements
```
db.people.find({tags:{$size:3}},{name:1,tags:1})
```

25. Volem trobar totes les persones que continguin en tags alguns dels valors laborum, sunt, nisi
```
db.people.find({$or:[{tags:{$in:["laborum","sunt","nisi"]}}]},{name:1})
```

26. Mostrar els primers tres elements de l'array que compleixen la condició de contenir a tags els valors laborum i sunt anteriors
```
db.people.find({tags:{$in:["laborum","sunt"]}},{name:{$slice:3}})
```

27. Mostrar els primers tres elements últims de l'array que compleixen la condició de contenir a tags els valors laborum i sunt anteriors
```
db.people.find({tags:{$in:["laborum","sunt"]}},{name:{$slice:3} })
```

## D A T A B A S E: imdb | C O L L E C T I O N: people
1. Buscar las personas que sólo han actuado (no dirigido)
```
db.people.find({"hasActed": {$exists: true}, "hasDirected": {$exists: false}})
```

2. Buscar las personas que sólo han dirigido (no actuado)
```
db.people.find({"hasActed": {$exists: false}, "hasDirected": {$exists: true}})
```

3. Buscar las personas que han actuado y dirigido
```
db.people.find({"hasActed": {$exists: true}, "hasDirected": {$exists: true}})
```

4. Buscar las personas que ni han actuado ni dirigido
```
db.people.find({"hasActed": {$exists: false}, "hasDirected": {$exists: false}})
```

## D A T A B A S E: edx | C O L L E C T I O N: bios
1. Buscar las personas con premios otorgados en el año 2001
```
db.bios.find({"awards.year": 2001})
```

2. Buscar las personas que hayan obtenido el premio 'Turing Award'
```
db.bios.find({"awards.award": "Turing Award"})
```

3. Buscar las personas que haya obtenido un premio del tipo 'National Medal of'
```
db.bios.find({"awards.award": /^National Medal of/i})
db.bios.find({"awards.award": {$regex : "National Medal of *"}},{})
```

4. Buscar las personas con fecha de nacimiento de la que no conste su fecha de defunción
```
db.bios.find({"birthYear": {"$exists": true}, "deathYear": {"$exists": false}})
db.bios.find({"$and": [{"birthYear": {"$exists": true}},{"deathYear": {"$exists": false}}]})
```

5. Buscar las personas de la colección bios que destaquen en el terreno de OOP
```
db.bios.find({"contribs": "OOP"})
```

6. Buscar las personas de la colección bios que destaquen en el terreno de Java, Ruby o Python
```
db.bios.find({"contribs": {"$in": ["Java", "Ruby", "Python"]}})
db.bios.find({$or : [ {contribs: "Python"},{contribs : "Ruby"},{ contribs : "Java"}]},{})
```

7. Buscar las personas de la colección bios que destaquen en el terreno de OOP y Simula
```
db.bios.find({"contribs": {"$all": ["OOP", "Simula"]}})
db.bios.find({"$and": [{"contribs": "OOP"}, {"contribs": "Simula"}]})
```

8. Buscar las personas de la colección bios sin premios logrados
```
db.bios.find({"$or": [{"awards": {"$exists": false}}, {"awards": {"$size": 0}}]})
```

9. Buscar las personas de la colección bios con 1 premio conseguido
```
db.bios.find({"awards": {"$size": 1}})
```

10. Buscar las personas de la colección bios con 3 o más premios conseguidos
```
db.bios.find({"awards.2": {"$exists": true}})
db.bios.find({awards : {$exists : true}, $where : "this.awards.length >= 3"})
db.bios.find({$where:"if(this.awards && this.awards.length>2){return this;}"})
```

11. Buscar las personas de la colección bios con entre 2 y 4 premios conseguidos
```
db.bios.find({"$or": [{"awards": {"$size": 2}}, {"awards": {"$size": 3}}, {"awards": {"$size": 4}}]})
db.bios.find({"$and": [{"awards.1": {"$exists": true}}, {"awards.4": {"$exists": false}}]})
db.bios.find({"awards.1": {"$exists": true}, "awards.4": {"$exists": false}})
db.bios.find({awards : {$exists : true}, $where : "this.awards.length >=2 this.awards.length <=4"},{})
db.bios.find({$where:"if(this.awards && this.awards.length>1 && this.awards.length<5){return this;}"})
```

## D A T A B A S E: edx | C O L L E C T I O N: books
1. Buscar todos los libros con precio superior a 100 USD
```
db.books.find({$and : [{"price.currency" : "USD"}, {"price.msrp" : {$gt : 100}}]},{})
```

2. Buscar todos los libros publicados por "Martin Fowler"
```
db.books.find({"author": "Martin Fowler"})
```

3. Buscar los libros que tengan el tag 'programming', 'agile' y "java"
```
db.books.find({"tags": {"$all": ["programming", "agile", "java"]}})
```

4. Buscar aquellos libros que han sido escritos por Martin Fowler y Kent Beck (2)
```
db.books.find({"author": {"$all": ["Martin Fowler", "Kent Beck"]}})
```

5. Buscar los libros escritos por 3 autores
```
db.books.find({"author": {"$size": 3}})
db.books.find({author : {$exists:true}, $where : "this.author.length == 3"}, {}).pretty()
```

6. Buscar los libros escritos por mas de un autor
```
db.books.find({"author": {"$not": {"$size": 1}}})
db.books.find({"author.1": {"$exists": true}})
db.books.find({author : {$exists:true}, $where : "this.author.length > 1"}, {})
```

# Exemple de base de dades documental: MongoDB

## Objectius generals
* Conèixer les operacions bàsiques per tal de realitzar operacions CRUD amb MongoDB
* Poder crear programes en Java per tal d'accedir a la informació de MongoDB.

Segurament hagis sentit parlar de les bases de dades NoSQL (Not Only SQL). És una cosa que últimament està molt de moda en el món del desenvolupament. Gairebé tant com que la gent faci servir auriculars grans per al MP3.

Resumint les bases de dades `NoSQL`, difereixen en diversos punts a les bases de dades relacionals de tota la vida com són: que no fan servir `SQL` ja que no són bases de dades relacionals, que no es necessiten estructures fixes (taules, columnes etc.) i en general no suporten `ACID`.

En el nostre cas parlarem de bases de dades documentals. Tot i que aquesta primera presa de contacte es centrarà més en les bases de dades documentals, ho farem tenint en compte que en el futur farem servir `MongoDB`.

# BSON

`MongoDB` és un sistema gestor de base de dades orientat a documents. El que vol dir, en llenguatge d'estar per casa, que el que guardem a la base de dades són documents. `MongoDB` guarda els documents en `BSON`, que no és més que una implementació binària del conegut `JSON`. Per tant tots els documents guardats a la base de dades es poden tractar com faríem en `JavaScript`. De fet, ja anirem veient que per a realitzar consultes, a la consola de `MongoDB` utilitzarem `JavaScript`.

* Tutorial: http://www.tutorialspoint.com/json/index.htm

Question: What is the corresponding JSON for the following XML document?

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

## Data-Sets

[Foursquare](datasets/foursquare.zip)

[imbd](datasets/imdb.zip)

[edx](datasets/edx.zip)

[digg](datasets/digg.zip)

# Instal·lació de MongoDB 5 a Ubuntu 20.04

`MongoDB` és un dels populars servidors de bases de dades `NoSQL` utilitzats per desenvolupar aplicacions dinàmiques modernes. Fa ús de documents semblants a `JSON` i esquemes opcionals; objectes de dades emmagatzemats com a documents separats dins d'una col·lecció, a diferència de les files i columnes utilitzades a les bases de dades relacionals tradicionals.

`MongoDB` és altament escalable i flexible i ofereix consulta i indexació fàcils per als desenvolupadors. A causa de les seves capacitats d'escala horitzontal i d'equilibri de càrrega, `MongoDB` ha ofert als desenvolupadors els nivells més alts de flexibilitat i escalabilitat. `MongoDB Atlas` és un servei de bases de dades al núvol i és líder en el desenvolupament d'aplicacions modernes i pot implementar bases de dades al núvol totalment gestionades a Azure, AWS o Google Cloud.

`MongoDB` ofereix tant una edició comunitària que es pot descarregar i utilitzar gratuïtament com una edició Enterprise que forma part de la subscripció avançada de `MongoDB Enterprise`. Aquesta versió Enterprise inclou un suport complet per al vostre desplegament de MongoDB i ofereix funcions enfocades a l'empresa, com ara suport LDAP i Kerberos, xifratge en disc i auditoria.

## Característiques i avantatges de la base de dades MongoDB
* Ofereix una gran escalabilitat i flexibilitat; error automàtic i redundància de dades
* Ofereix un llenguatge de consulta expressiu que és fàcil d'aprendre i utilitzar
* Consultes ad-hoc per a analítiques en temps real
* Admet matrius i objectes imbricats com a valors i permet esquemes flexibles i dinàmics.
* És fàcil redactar consultes que permeten ordenar i filtrar, sense importar com estiguin imbricades, i admet l'agregació, la geolocalització, les sèries temporals, la cerca de gràfics i molt més.
* Admet la fragmentació que permet dividir grans conjunts de dades en múltiples col·leccions distribuïdes que, a continuació, faciliten les consultes.
* Admet diversos motors d'emmagatzematge

### Noves funcions de MongoDB 5.0

`MongoDB 5.0` és l'última versió publicada el 13 de juliol de 2021. Hi ha una sèrie de correccions fetes a partir de les versions anteriors i també algunes funcions addicionals que les podeu trobar totes a les notes de llançament oficials de MongoDB 5.0. Algunes de les característiques addicionals inclouen:

* Introducció de col·leccions de sèries temporals que emmagatzemen de manera eficient seqüències de mesures durant un període de temps.
* Obsoleta de l'operació de reducció de mapes.
* Introducció de nous operadors d'agregació com ara `$dateAdd`, `$dateDiff`, `$dateSubtract`, `$getField`, `$rand` i molts més. Consulteu la documentació.
* Introducció de l'etapa de pipeline $setWindowFields que permet realitzar operacions en un interval especificat de documents d'una col·lecció, coneguda com a finestra.
* Afegeix la capacitat de configurar filtres d'auditoria en temps d'execució.

# Instal·lació de MongoDB 5.0 on Ubuntu 20.04

Els passos següents ens guiaran sobre com podem instal·lar MongoDB 5.0 a Ubuntu 20.04

## Pas 1: importa la clau GPG de MongoDB

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

## Pas 2: afegiu el repositori MongoDB a Ubuntu 20.04

Per poder instal·lar `MongoDB` amb apt, hem d'afegir el repositori `MongoDB`.

```
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/5.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-5.0.list
```

## Pas 3: instal·leu MongoDB 5.0 a Ubuntu 20.04

Ja hem afegit MongoDB GPG i repositori, procedim a instal·lar MongoDB. Actualitzeu primer els paquets

```
sudo apt-get update
sudo apt-get install -y mongodb-org
```

Si voleu instal·lar una versió específica de MongoDB, podeu passar la versió tal com es mostra a continuació.

```
sudo apt-get install -y mongodb-org=5.0.2 mongodb-org-database=5.0.2 mongodb-org-server=5.0.2 mongodb-org-shell=5.0.2 mongodb-org-mongos=5.0.2 mongodb-org-tools=5.0.2
```

## Pas 4: inicieu MongoDB 5.0 a Ubuntu 20.04

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

## Pas 5: configureu MongoDB 5.0 a Ubuntu

El fitxer de configuració de `MongoDB` es troba a `/etc/mongod.conf`. Aquí trobareu les configuracions per a la ruta `db`, la ruta dels registres. Sempre podeu fer canvis a aquest fitxer en funció de les vostres necessitats d'instal·lació. Reinicieu el servei `mongod` cada vegada que feu un canvi al fitxer.

### Mongodb habilita l'autenticació de contrasenya

Activem l'autenticació de contrasenya de mongodb perquè els usuaris puguin iniciar sessió amb una contrasenya per llegir o editar la base de dades. Descomenteu `#Security` i afegiu el contingut com a continuació:

```
security:
  authorization: enabled
```

Després d'això, reinicieu el servei `mongod`.

```
sudo systemctl restart mongod
```

### MongoDB Habilita l'accés remot

Per defecte, `MongoDB` només es pot accedir localment. Si voleu poder accedir a la base de dades de forma remota, farem un petit canvi al fitxer de configuració per incloure l'adreça `IP` o el nom d'amfitrió del servidor `MongoDB`, tal com es mostra a continuació.

```
# network interfaces
net:
  port: 27017
  bindIp: 127.0.0.1,mongodb-server-ip/hostname
```

Deseu els canvis i reinicieu el servei `mongod`. També heu de permetre al tallafoc les adreces `IP` remotes de confiança si heu habilitat el vostre tallafoc.

```
sudo ufw allow from trusted-server-ip to any port 27017
```

## Pas 6: Creació d'usuaris i base de dades a MongoDB
### Per accedir a l'intèrpret d'ordres de MongoDB, executeu l'ordre mongosh al terminal tal com es mostra.

```
$ mongosh
Using MongoDB:		5.0.2
Using Mongosh:		1.0.5

For mongosh info see: https://docs.mongodb.com/mongodb-shell/
..........
>
```

### Per llistar les bases de dades existents

```
> show dbs
admin       135 kB
config      111 kB
local      73.7 kB
```

### Com crear un usuari i afegir un rol a MongoDB

Vegem com podem crear un usuari a `MongoDB` i donar-li rols d'administrador i permís per a totes les bases de dades.

```
> db.createUser({
        user: "rusben",
        pwd:  "123456789",
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

Per crear una nova base de dades a MongoDB, utilitzem l'ordre use. MongoDB no té l'ordre de creació. Com a exemple, creeu una nova base de dades anomenada "prova"

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

### MongoDB insertOne i insertMany

Tingueu en compte que `Collection.insert()` està obsolet i hauríeu d'utilitzar `insertOne`, `insertMany` o `bulkWrite`.

Ara, quan mostreu les bases de dades, també hauríeu d'aparèixer la vostra base de dades nova.

```
> show dbs
admin      0.000GB
config     0.000GB
employees  0.000GB
local      0.000GB
```

## Pas 7: Afinació de MongoDB

Un cop hàgiu instal·lat MongoDB, és important assegurar-vos que el seu rendiment estigui en configuracions òptimes. A mesura que la informació augmenta, la nostra instal·lació de MongoDB hauria de poder gestionar-la i processar-la com s'esperava. L'escala es pot produir tant horitzontalment com verticalment. L'escalat horitzontal significa l'addició de recursos del servidor com ara RAM i CPU, mentre que l'escalat vertical és la introducció de més servidors a la nostra instal·lació.

Un escalat vertical, que és molt crucial en el rendiment global de mongodb, està influenciat per certs factors, com ara: ús de memòria, nombre de connexions concurrents i WiredTiger Cache, entre d'altres. La memòria és un factor important que influeix molt en el rendiment de MongoDB. MongoDB utilitza WiredTiger com a motor d'emmagatzematge predeterminat i, per tant, conserva el 50% de (la memòria disponible -1) per a WiredTiger.

Per exemple, un servidor amb 8 GB de RAM tindrà un conservador de memòria de 0,5 * (8-1) per a WiredTiger. Per comprovar les estadístiques d'ús de la memòria cau i determinar si cal fer canvis, executeu l'ordre següent:

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

* wiredTiger.cache.maximum bytes configurat
* wiredTiger.cache.bytes actualment a la memòria cau
* wiredTiger.cache.tracked bytes bruts a la memòria cau
* wiredTiger.cache.pages llegit a la memòria cau
* wiredTiger.cache.pages escrites des de la memòria cau

Les mides actuals de l'anterior ens haurien de permetre prendre una decisió sobre si volem augmentar la mida del WiredTiger als nostres servidors. Una altra cosa igualment important és l'ús de bitllets de lectura i escriptura de concurrència de WiredTiger, que es pot comprovar de la següent manera:

```
> db.serverStatus().wiredTiger.concurrentTransactions
{
  write: { out: 0, available: 128, totalTickets: 128 },
  read: { out: 1, available: 127, totalTickets: 128 }
}
```

Si observeu que els números augmenten i tendeixen cap a aquest nombre disponible de nuclis, és possible que els vostres servidors s'apropin a la saturació de la CPU.

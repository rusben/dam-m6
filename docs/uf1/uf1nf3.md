

https://zetcode.com/java/dom/# Processament de fitxers XML

Sovint ens pot interessar emmagatzemar objectes sencers per poder-los recuperar en qualsevol moment. Aquí veurem bàsicament dues maneres diferents d'aconseguir fer-los persistents.

1. Podem seriar-los usant les eines específiques que `Java` té per convertir qualsevol objecte en una sèrie de bytes.
2. També podem convertir els objectes persistents en una jerarquia `XML` capaç d'emmagatzemar les dades bàsiques i les relacions establertes entre objectes.

En el primer cas, ja hem vist com fer servir la interfície `Serializable`. Recordem que per tal que un objecte pugui ser seriat cal que la seva classe i tot el seu contingut implementin la interfície `Serializable`. Es tracta d'una interfície sense mètodes, perquè l'únic objectiu de la interfície és actuar de marcador per indicar a la màquina virtual quines classes es poden seriar i quines no.

Ara veurem com convertir els objectes persistents en una jerarquia `XML` capaç d'emmagatzemar les dades bàsiques i les relacions establertes entre objectes. Quan es volen emmagatzemar dades que hagin de ser llegides per aplicacions executades en múltiples plataformes serà necessari recórrer a formats més estandarditzats, com ara els llenguatges de marques.

Els documents `XML` aconsegueixen estructurar la informació intercalant un seguit de marques anomenades etiquetes. En `XML`, una etiqueta pot contenir altres etiquetes o bé informació textual. D'aquesta manera, aconseguirem subdividir la informació estructurant-la de forma que pugui ser fàcilment interpretada.

El llenguatge `XML` (`eXtended Markup Language`) té molta utilitat:

* Representa la informació de forma neutra, independent del llenguatge de programació i del sistema operatiu emprat.
* És útil en el desenvolupament d'aplicacions.
* S'han dissenyat moltes tecnologies gràcies a les possibilitats ofertes per `XML`, un exemple d'elles són els serveis web.
* Des d'un punt de vista a "baix nivell", un document `XML` no és altra cosa que un fitxer de text. Realment res no impedeix utilitzar llibreries d'accés a fitxers, com les vistes en la secció anterior, per accedir i manipular fitxers `XML`.

No obstant això, des d'un punt de vista a "alt nivell" `XML` no és un fitxer de text qualsevol. Calen eines específiques (llibreries) per accedir i manipular aquest tipus d'arxius de manera potent, flexible i eficient. Així:

* Es redueix els temps de desenvolupament d'aplicacions.
* S'optimitzen els propis accessos a `XML`.
* Es pot manejar els documents `XML` de forma simple i sense carregar innecessàriament el sistema.


## Analitzadors sintàctics o parsers
Les eines que llegeixen el llenguatge `XML` i comproven si el document és vàlid sintàcticament s'anomenen analitzadors sintàctics o parsers. Un parser `XML` és un mòdul, biblioteca o programa encarregat de transformar el fitxer de text en un model intern que optimitza el seu accés. Per `XML` ha un gran nombre de parsers o analitzadors sintàctics disponibles per dos dels models més coneguts: `DOM` i `SAX`, encara que existeixen molts altres.


Una classificació de les eines per a l'accés a fitxers `XML` és la següent:

* Eines que validen els documents `XML`. Aquestes comproven que el document `XML` al qual es vol accedir està ben conformat (well-formed) segons la definició de `XML` i, a més, que és vàlid respecte a un esquema `XML` (`XML-Schema`). Un exemple d'aquest tipus d'eines és `JAXB` (`Java Architecture for XML Binding`).

* Eines que no validen els documents `XML`. Aquestes només comproven que el document està ben conformat segons la definició `XML`, però no necessiten d'un esquema associat per comprovar si és vàlid pel que fa a aquest esquema. Exemples d'aquest tipus d'eines són `DOM` i `SAX`.

A continuació es descriu l'accés a dades amb les tecnologies més esteses: `DOM`, `SAX` i `JAXB`.


https://zetcode.com/java/dom/

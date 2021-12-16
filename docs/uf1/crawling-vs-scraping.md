# Data scraping vs. data crawling

El `webscraping`, la recol·lecció web o l'extracció de dades web és una forma d'extreure informació que s'utilitza per extreure dades dels llocs web. El programari `webscraping` pot accedir directament a la `World Wide Web` mitjançant el protocol `http | https`  o un navegador web. Tot i que un usuari el pot fer manualment, el terme normalment es refereix a processos automatitzats implementats mitjançant un bot o un rastrejador web. És una forma de còpia en la qual es recullen dades específiques i es copien del web, normalment en una base de dades o full de càlcul local, per a la seva posterior recuperació o anàlisi.

El `webscraping` d'una pàgina web implica recuperar-la i extreure'n la informació. L'obtenció és la descàrrega d'una pàgina (cosa que fa un navegador quan un usuari visualitza una pàgina). Per tant, el `webcrawling` és un component principal del `webscraping`, per obtenir pàgines per a un processament posterior. Un cop obtingut, es pot fer l'extracció. El contingut d'una pàgina es pot analitzar, cercar, reformatejar, les seves dades copiar-se en un full de càlcul o carregar-se en una base de dades. Els webscrapers solen treure informació d'una pàgina per fer-ne ús amb un altre propòsit en un altre lloc. Un exemple seria trobar i copiar noms i números de telèfon, o empreses i les seves URL, o adreces de correu electrònic a una llista (scraping de contactes).

El `webscraping` s'utilitza per al `contact scraping` i com a component d'aplicacions que s'utilitzen per a la indexació web, la mineria web i la mineria de dades, el seguiment en línia del canvi de preus i la comparació de preus, el scraping de revisions de productes (per veure la competència), la recopilació de llistes de béns web scraping monitorització de dades meteorològiques, detecció de canvis de llocs web, `investigació`, seguiment de la presència i reputació en línia, mashup web i integració de dades web.

Les pàgines web es creen utilitzant llenguatges de marcatge basats en text (`HTML` i `XHTML`) i sovint contenen una gran quantitat de dades útils en forma de text. Tanmateix, la majoria de pàgines web estan dissenyades per a usuaris finals humans i no per facilitar-ne l'ús automatitzat. Com a resultat, s'han desenvolupat eines i programari especialitzats per facilitar el `scraping` de pàgines web.

Les formes més noves de web scraping impliquen la supervisió de les fonts de dades dels servidors web. Per exemple, `JSON` s'utilitza habitualment com a mecanisme d'emmagatzematge de transport entre el client i el servidor web.

Hi ha mètodes que utilitzen alguns llocs web per evitar el `webscraping`, com ara detectar i no permetre que els robots rastregin (vegin) les seves pàgines. En resposta, hi ha sistemes de `webscraping` que es basen en l'ús de tècniques d'anàlisi `DOM`, visió per ordinador i processament del llenguatge natural per simular la navegació humana per permetre la recopilació de contingut de la pàgina web per a l'anàlisi fora de línia.

## Història
Aquesta secció no cita cap font. Si us plau, ajudeu a millorar aquesta secció afegint cites a fonts fiables. El material sense font pot ser impugnat i eliminat. (Octubre de 2018) (Més informació sobre com i quan eliminar aquesta plantilla de missatge)

La història del `webscraping` es remunta gairebé a l'època en què va néixer la `World Wide Web`.

* Després del naixement de la `World Wide Web` l'any 1989, el juny de 1993 es va crear el primer robot web,  World Wide Web Wanderer, que només tenia la intenció de mesurar la mida de la web.
* El desembre de 1993, es va llançar el primer motor de cerca web basat en rastrejadors, JumpStation. Com que no hi havia tants llocs web disponibles al web, els motors de cerca en aquell moment solien confiar en els seus administradors de llocs web humans per recopilar i editar els enllaços en un format determinat. En comparació, JumpStation va donar un nou salt, sent el primer cercador de la WWW que es basava en un robot web.
* L'any 2000, va arribar el primer rastrejador d'API i API web. API significa Application Programming Interface. És una interfície que facilita molt el desenvolupament d'un programa proporcionant els blocs de construcció. L'any 2000, Salesforce i eBay van llançar la seva pròpia API, amb la qual es va permetre als programadors accedir i descarregar algunes de les dades disponibles per al públic. Des de llavors, molts llocs web ofereixen API web perquè la gent accedeixi a la seva base de dades pública.

## Tècniques

El web scraping és el procés d'extracció automàtica de dades o de recollida d'informació de la World Wide Web. És un camp amb desenvolupaments actius que comparteixen un objectiu comú amb la visió de la web semàntica, una iniciativa ambiciosa que encara requereix avenços en el processament de textos, la comprensió semàntica, la intel·ligència artificial i les interaccions home-ordinador. Les solucions actuals de raspat web van des d'ad-hoc, que requereixen esforç humà, fins a sistemes totalment automatitzats que són capaços de convertir llocs web sencers en informació estructurada, amb limitacions.

### Copiar i enganxar a mà

La forma més senzilla de raspat web és copiar i enganxar manualment dades d'una pàgina web en un fitxer de text o full de càlcul. De vegades, fins i tot la millor tecnologia de raspat web no pot substituir l'examen manual d'un humà i el copiar i enganxar, i de vegades aquesta pot ser l'única solució viable quan els llocs web per al raspat configuren explícitament barreres per evitar l'automatització de la màquina.
Concordança de patrons de text

Un enfocament senzill però potent per extreure informació de pàgines web es pot basar en l'ordre grep de UNIX o en les instal·lacions de concordança d'expressions regulars dels llenguatges de programació (per exemple, Perl o Python).

### Programació HTTP

Les pàgines web estàtiques i dinàmiques es poden recuperar enviant sol·licituds HTTP al servidor web remot mitjançant la programació de socket.

### Anàlisi HTML

Molts llocs web tenen grans col·leccions de pàgines generades de forma dinàmica a partir d'una font estructurada subjacent com una base de dades. Les dades de la mateixa categoria normalment es codifiquen en pàgines similars mitjançant un script o una plantilla comuns. En la mineria de dades, un programa que detecta aquestes plantilles en una font d'informació concreta, n'extreu el contingut i el tradueix a una forma relacional, s'anomena embolcall. Els algorismes de generació d'embolcalls assumeixen que les pàgines d'entrada d'un sistema d'inducció d'embolcalls s'ajusten a una plantilla comuna i que es poden identificar fàcilment en termes d'un esquema comú d'URL. A més, alguns llenguatges de consulta de dades semiestructurats, com XQuery i HTQL, es poden utilitzar per analitzar pàgines HTML i per recuperar i transformar el contingut de la pàgina.

### Anàlisi DOM
Més informació: Document Object Model

Mitjançant la inserció d'un navegador web complet, com ara Internet Explorer o el control del navegador Mozilla, els programes poden recuperar el contingut dinàmic generat pels scripts del costat del client. Aquests controls del navegador també analitzen pàgines web en un arbre DOM, en funció dels programes que poden recuperar parts de les pàgines. Es poden utilitzar idiomes com Xpath per analitzar l'arbre DOM resultant.
Agregació vertical

Hi ha diverses empreses que han desenvolupat plataformes verticals específiques de recol·lecció. Aquestes plataformes creen i supervisen una multitud de "bots" per a verticals específiques sense "home en el bucle" (sense implicació humana directa) i sense treball relacionat amb un lloc objectiu específic. La preparació implica establir la base de coneixement per a tota la vertical i després la plataforma crea els bots automàticament. La robustesa de la plataforma es mesura per la qualitat de la informació que recupera (normalment el nombre de camps) i la seva escalabilitat (la rapidesa amb què pot escalar fins a centenars o milers de llocs). Aquesta escalabilitat s'utilitza principalment per orientar-se a la cua llarga dels llocs que els agregadors comuns troben complicats o massa intensius per recollir contingut.

### Reconeixement d'anotacions semàntiques

Les pàgines que s'estan ratllant poden incloure metadades o marques i anotacions semàntiques, que es poden utilitzar per localitzar fragments de dades específics. Si les anotacions estan incrustades a les pàgines, com ho fa Microformat, aquesta tècnica es pot veure com un cas especial d'anàlisi DOM. En un altre cas, les anotacions, organitzades en una capa semàntica, s'emmagatzemen i es gestionen per separat de les pàgines web, de manera que els scrapers poden recuperar l'esquema de dades i les instruccions d'aquesta capa abans de raspar les pàgines.

### Anàlisi de pàgines web de visió per computador

Hi ha esforços que utilitzen l'aprenentatge automàtic i la visió per ordinador que intenten identificar i extreure informació de les pàgines web interpretant pàgines visualment com ho faria un ésser humà.

## Mètodes per evitar el web scrapping

L'administrador d'un lloc web pot utilitzar diverses mesures per aturar o frenar un bot. Algunes tècniques inclouen:

* Bloqueig d'una adreça IP manualment o en funció de criteris com ara la geolocalització i DNSRBL. Això també bloquejarà tota la navegació des d'aquesta adreça.
* Desactivació de qualsevol API de servei web que el sistema del lloc web pugui exposar.
* De vegades, els robots declaren qui són (mitjançant cadenes d'agent d'usuari) i es poden bloquejar amb aquesta base mitjançant robots.txt; "googlebot" n'és un exemple. Altres robots no fan distinció entre ells mateixos i un humà que utilitza un navegador.
* Els bots es poden bloquejar controlant l'excés de trànsit
* De vegades, els bots es poden bloquejar amb eines per verificar que es tracta d'una persona real que accedeix al lloc, com un CAPTCHA. Els robots de vegades es codifiquen per trencar explícitament patrons CAPTCHA específics o poden utilitzar serveis de tercers que utilitzen mà d'obra humana per llegir i respondre en temps real als reptes de CAPTCHA.
* Serveis comercials anti-bot: les empreses ofereixen serveis anti-bot i anti-scraping per a llocs web. Alguns tallafocs d'aplicacions web també tenen capacitats limitades de detecció de bots. Tanmateix, moltes d'aquestes solucions no són gaire efectives.[27]
* Localització de robots amb un honeypot o un altre mètode per identificar les adreces IP dels rastrejadors automatitzats.
* Ofuscament utilitzant sprites CSS per mostrar dades com ara números de telèfon o adreces de correu electrònic, a costa de l'accessibilitat per als usuaris de lectors de pantalla.
* Com que els robots es basen en la coherència del codi frontal d'un lloc web de destinació, afegir petites variacions a l'HTML/CSS que envolten dades importants i elements de navegació requeriria més implicació humana en la configuració inicial d'un bot i, si es fa de manera eficaç, pot fer que El lloc web de destinació és massa difícil de raspar a causa de la disminució de la capacitat d'automatitzar el procés de raspat.
* Els llocs web poden declarar si el rastreig està permès o no al fitxer robots.txt i permetre l'accés parcial, limitar la velocitat de rastreig, especificar el moment òptim per rastrejar i molt més.

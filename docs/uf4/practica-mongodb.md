# Nova empresa
Dues col·leccions: Teniu el fitxer amb les sentències SQL. Amb MongoDB heu de crear UNA col·lecció amb tots els documents (afegiu als documents de la segona taula la informació de la primera).

1) La informació dels camps és la següent:
```
    DEPT_NO - Número o codi del departament.
    DNOM - Nom del departament.
    LLOC - Ciutat on el departament està ubicat.
```

2) La informació dels camps és la següent:
```
    TREB_NO - Número o codi de l'empleat.
    COGNOM - Cognom de l'empleat.
    OFICI - Treball de l'empleat.
    DIR- Codi del cap de l'empleat.
    DATA_ENT - Data de contractació.
    SOU - sou mensual de l'empleat
    COMISIO - Comissió
    DEPT_NO - Número o codi del departament.
```

## Consultes a desenvolupar
Heu d'obtenir les següents informacions. Inseriu la consulta a sota de cadascuna de preguntes. La consulta ha de retornar el resultat de l'enunciat de cadascuna de les preguntes.

1. Obtenir totes les dades de tots els empleats.
2. Obtenir totes les dades de tots els departaments.
3. Obtenir totes les dades dels oficinistes.
4. El mateix que l'anterior, però ordenat pel cognom.
5. El mateix que l'anterior, però ordenat pel sou.
6. Aconsegueix el número (codi), cognom i sou dels empleats.
7. Llista els noms de tots els departaments.
8. El mateix que l'anterior, però ordenant-los per nom.
9. El mateix que l'anterior,però ordenant per la ciutat (no s'ha de seleccionar la ciutat en el resultat).
10. El mateix que l'anterior, però el resultat ha de mostrar ordenat per la ciutat en ordre invers.
11. Obtenir el cognom i ocupació de tots els empleats, ordenat per sou.
12. Obtenir el cognom i ocupació de tots els empleats, ordenats primer per la seva feina i després pel seu sou.
13. El mateix que l'anterior, però ordenant inversament per ocupació.
14. Aconsegueix els sous i les comissions dels empleats del departament 30.
15. El mateix que l'anterior, però ordenat per comissió.
16. Obteniu les comissions dels empleats de manera que no es repeteixin.
17. Aconsegueix els cognoms dels empleats i els seus sous.
18. Aconsegueix les comissions dels empleats i els seus números de departament.
19. Troba els cognoms dels empleats que el cognom tinguí com a màxim cinc caràcters.
20. Aconsegueix els nous sous dels empleats del departament 30, que resultarien de sumar al seu sou una gratificació de 1000. Mostra també els cognoms dels empleats.
21. El mateix que l'anterior, però mostrant també el seu sou original, i fes un camp nou, NOU SOU, per emmagatzemar el nou sou.
22. Troba els empleats que tenen una comissió superior a la meitat del seu sou.
23. Troba els empleats que no tenen comissió, o que la tinguin menor o igual que el 25% del seu sou.
24. Aconsegueix una llista de cognoms d'empleats i els seus sous, de manera que a la sortida aparegui sempre "Cognom:" i "Sou:" abans del respectiu valor del camp.
25. Trobar el codi, sou i comissió dels empleats el codi sigui més gran que 7500.
26. Aconsegueix el sou més alt de l'empresa, el total destinat a comissions i el nombre d'empleats.
27. Aconsegueix el sou, comissió i sou total (sou + comissió) dels empleats amb comissió, ordenant el resultat pel número de l'empleat.
28. Llista la mateixa informació, però per als empleats que no tenen comissió.
29. Mostra el cognom dels empleats que, tenint un sou superior a 1000, tinguin com a cap a l'empleat el codi és 7698.
30. Troba el conjunt complementari del resultat de l'exercici anterior.
31. Indica per a cada empleat el percentatge que suposa la seva comissió sobre el seu sou, ordenant el resultat pel cognom del mateix.
32. Trobeu els empleats del departament 20 que el seu cognom no conté la cadena AL.
33. Aconsegueix els empleats que no són supervisats per ningú altre.
34. Aconsegueix els cognoms dels departaments que no siguin Vendes ni Investigació. Ordena el resultat per la localitat del departament.
35. Desitgem conèixer el cognom dels empleats i el codi del departament dels oficinistes que no treballen en el departament 10, i el sou és superior a 800, ordenat per data de contractació.
36. Per als empleats que tinguin comissió, aconsegueix els seus cognoms i el quocient entre el seu sou i la seva comissió (excepte quan la comissió sigui zero), ordenant el resultat per cognom.
37. Llista tota la informació sobre els empleats que el cognom tingui exactament quatre caràcters.
38. El mateix, però per als empleats el cognom tingui almenys cinc lletres.
39. Troba les dades dels empleats que, o bé el seu cognom comença per A i el seu sou és superior a 1000, o bé reben comissió i treballen en el departament 30.
40. Troba el cognom, el sou i el sou total de tots els empleats, ordenant el resultat primer per sou i després pel sou total. En el cas que no tingui comissió, el sou total ha de reflectir només el sou.
41. Aconsegueix el cognom, sou i la comissió dels empleats que perceben un sou que està entre la meitat de la comissió i la pròpia comissió.
42. Aconsegueix el complementari de l'anterior.
43. Suposant que l'any vinent la pujada del sou total de cada empleat serà del 6%, i el següent el 7%. Troba els cognoms i el sou total actual, de l'any que ve i del següent, de cada empleat. Indiqui més amb SI o NO, si l'empleat té comissió.
44. Llista els cognoms i data de contractació d'aquells empleats que no són venedors.
45. Aconsegueix la informació disponible dels empleats el número un dels següents: 7844, 7900, 7521, 7521, 7782, 7934, 7678 i 7369, però que no sigui un dels següents: 7902, 7839, 7499 ni 7878.
46. Ordena els empleats pel seu codi de departament, i després de manera descendent pel seu número d'empleat.
47. Per als empleats que tinguin com a cap a un empleat amb codi més gran que el seu, llista els que reben de sou més de 1000 i menys de 2000, o que estan en el departament 30.
48. Troba les dades dels empleats que el seu sou és més gran que el de l'empleat de codi 7934, ordenant pel sou.
49. Troba el cognom l'últim empleat per ordre alfabètic.
50. Troba el sou més alt, el més baix, i la diferència entre ells.

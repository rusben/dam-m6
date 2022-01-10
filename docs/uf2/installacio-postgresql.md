# Procediment d'instal·lació de postgresql

```console
sudo apt install postgresql
```

## Connectem amb l'usuari postgres per a l'administració

```console
sudo -u postgres -i
```

## Entrem a postgres:

```console
psql
```

## Dins la consola de postgres creem una base de dades:

```console
CREATE DATABASE ddbb;
```

## Creem un usuari amb una contrassenya:

```console
CREATE USER usuario WITH PASSWORD 'password';
```

## Donem privilegis a l'usuari a la base de dades creada:

```console
GRANT ALL PRIVILEGES ON DATABASE "ddbb" to usuario;
```

## Modifiquem la configuració per a que la nostra base de dades pugui rebre connexions des de fora de la màquina.

```console
cat /etc/postgresql/12/main/postgresql.conf | grep listen_addresses
```

## Editem el fitxer de configuració postgresql.conf

```console
vim /etc/postgresql/12/main/postgresql.conf
```

La següent línia s'ha de canviar:

```console
#listen_addresses = 'localhost'		
```

I s'ha de substituir per (no ha d'estar comentada):

```console
listen_addresses = '*'
```

## Reiniciem el servei postgres:
```console
invoke-rc.d postgresql reload
```

Busquem el fitxer pg_hba.conf
```console
locate pg_hba.conf
/etc/postgresql/12/main/pg_hba.conf
```

# Editem el fitxer
```console
sudo vim /etc/postgresql/12/main/pg_hba.conf
```

Afegim la línia

```console
host all all all md5
```

## Reiniciem postgresql
```console
invoke-rc.d postgresql reload
```

## Connexió a la base de dades
Provem de connectar a la IP on està la nostra màquina:
```console
psql -h 192.168.22.126 -U usuario -d ddbb

Type "help" for help.
ddbb=>
```

# Matrioska

## Descrizione
Non sempre tutto é come sembra...

## Soluzione
Per risolvere la challenge é necessario l'utilizzo dei tool [binwalk](https://www.kali.org/tools/binwalk/) e *file*.
Lanciando il comando `binwalk Matrioska.jpg` é possibile notare che all'interno del file allegato alla challenge é
nascosto un file zip.
Lanciando il comando `unzip Matrioska.jpg` otteniamo il file *flag*. Analizzando il file comando `file flag` notiamo
che il file ottenuto é un archivio tar. Possiamo a questo punto estrarne il contenuto usando il comando `tar xvf flag`
otteniamo un nuovo file *flag*. 
É necessario effettaure piú volte il procedimento fino all'ottenimento del file flag.txt contenente la flag stessa.
In ordine a partire dall'immagine troveremo i file:
- zip
- tar
- tar
- zip
- zip

# Flag

**havceCTF{wh4t_a_W0nd3rFul_M47r1oSk4}**

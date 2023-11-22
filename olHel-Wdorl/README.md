# olHel Wdorl

## Description
Finalmente oggi è iniziato il corso di Programmazione in C ed il professore ci ha già assegnato un compito: creare il nostro primo "Hello World". Io ci sto provando da tantissimo tempo ma non riesco... puoi aiutarmi?

Trova il valore corretto di `v2` in modo che il programma stampi "Hello World".


La flag è nel formato `flag{0xABCD1234}`.

HINT: Utilizzare la rappresentazione binaria potrebbe esserti d'aiuto.

*Author: [@samuelevalperta](https://github.com/samuelevalperta)*


## Solution
Il programma è composto da 2 sole variabili e un ciclo while che stampa un carattere alla volta sulla base di alcuni calcoli.
La variabile `v1` è inizilizzata a `0x726F6C6564574820`, questi non sono altro che i valori ASCII di tutti i caratteri presenti nella stringa "Hello World", sistemati in ordine decrescente e salvati come intero senza segno di dimensione 64 bits.
'0x72' ad esempio equivale al carattere "r", `0x6F` al carattere "o", ecc...
Per aiutarci a comprendere il funzionamente del programma possiamo immaginare la variabile `v1` come un array di caratteri:
```c
char v1 = {' ', 'H', 'W', 'd', 'e', 'l', 'o', 'r'}
```
(Analizzando la memoria potremmo notare che la dichiarazione di `v1` sotto forma di array di char porterebbe allo stesso risulato della dichiarazione utilizzata nel programma).

La variabile `v2`, inizializzata a `0xBF342C370`, contiene invece gli indici nella tabella `v1` relativi alla posizione delle lettere da stampare.
Visualizzando il valore di `v2` in rappresentazione binaria otteniamo `101111110011010000101100001101110000`, anche questo, presi 3 bit alla volta, può essere rappresentato come un array di valori
```c
v2 = {0b101, 0b111, 0b110, 0b011, 0b010, 0b000, 0b101, 0b100, 0b001, 0b101, 0b110, 0b000}
```
tornando alla rappresentazione decimale otteniamo
```c
v2 = {5, 7, 6, 3, 2, 0, 5, 4, 1, 5, 6, 0}
```
(Questa la trattiamo unicamente come rappresentazione immaginaria poichè in C non è immediato dichiarare un array così definito).


A questo punto analizziamo il contenuto del ciclo `while`:
```c
putchar((v1 >> (((v2 >>= 3) & 7) << 3)) & 0xFF);
```

1. `(v2 >>= 3)` elimina i 3 bit meno significativi di `v2`, ovvero l'ultimo elemento dell'array immaginario.
Nel primo ciclo, subito dopo questa operazione l'array sarà rappresentabile come
```c
v3 = {5, 7, 6, 3, 2, 0, 5, 4, 1, 5, 6}
```

2. `(v3) & 7` equivale ad estrarre i 3 bits meno significativi di `v3`, ovvero l'ultimo elemento dell'array.
Sempre nel primo ciclo, subito dopo questa operazione il valore ottenuto sarà
```v4 = 6```

3. `v4 << 3` lo shift verso sinistra di 3 bits è equivalente ripetere 3 volte lo shift di 1 bit, e lo shift di 1 bit è equivalente alla moltiplicazione per 2. Allora
```v5 = v4 << 3 = v4 << 1 << 1 << 1 = v4 * 2 * 2 * 2 = v4 * 8```

4. `(v1 >> v5) & 0xFF` per gli stessi principi del punto 1 e del punto 2 permette di rimuovere i 48 bit meno significati, ovvero i primi 6 (poichè un carattere occupa 8 bits) caratteri dell'array `v1` e di selezionare, grazie a `& 0xFF`, quelli che ora sono diventati gli 8 bits meno significativi, ovvero il settimo carattere partendo dal fondo.
   
## Flag
flag{0x77E435B08}

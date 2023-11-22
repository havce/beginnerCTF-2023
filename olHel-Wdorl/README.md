# olHel Wdorl

## Descrizione
Oggi è finalmente cominciato il corso di Programmazione in C e il professore ci ha già assegnato un compito: creare il nostro primo 'Hello World'. Ci sto provando da ore ma ancora non sono riuscito... puoi aiutarmi?

Individua il valore corretto di **v2** affinché il programma possa stampare 'Hello World'.

Utilizza `nc beginner.havce.it 10100` per ottenere la flag.

HINT: Utilizzare la rappresentazione binaria potrebbe esserti d'aiuto.

*Author: [@samuelevalperta](https://github.com/samuelevalperta)*


## Soluzione
Il programma è composto da 2 sole variabili e da un ciclo while utilizzato per stampare alcuni caratteri.

La variabile **v1**: inizializzata a *0x726F6C6564574820*, questi non sono altro che i valori ASCII di tutti i caratteri presenti nella stringa "Hello World", sistemati in ordine decrescente e memorizzati come intero senza segno di dimensione 64 bit.
Ad esempio, *0x72* corrisponde al carattere '*r*', *0x6F* al carattere '*o*', e così via.

Per aiutarci a comprendere il funzionamente del programma possiamo immaginare la variabile **v1** come un array di caratteri:
```c
char v1 = {' ', 'H', 'W', 'd', 'e', 'l', 'o', 'r'}
```
(Analizzando la memoria potremmo notare che la dichiarazione di **v1** in questa maniera porterebbe allo stesso risulato della dichiarazione utilizzata nel programma).

La variabile **v2**: inizializzata a *0xBF342C370* contiene invece gli indici di accesso all'array **v1**.
Visualizzando il valore di **v2** in rappresentazione binaria otteniamo 
```
v2 = 101111110011010000101100001101110000
```
Ancora una volta possiamo, raggruppando 3 bit alla volta, rappresentare questo valore come array:
```c
v2 = {0b101, 0b111, 0b110, 0b011, 0b010, 0b000, 0b101, 0b100, 0b001, 0b101, 0b110, 0b000}
```
riconvertendo alla rappresentazione decimale otteniamo
```c
v2 = {5, 7, 6, 3, 2, 0, 5, 4, 1, 5, 6, 0}
```
(In questo caso la rappresentazione può essere solo immaginaria poichè in C non è immediato dichiarare un array le cui componenti siano sequenze di 3 bit).

Infine abbiamo il contenuto del ciclo **while**:
```c
putchar((v1 >> (((v2 >>= 3) & 7) << 3)) & 0xFF);
```
(Gli esempi utilizzati nei successivi punti rappresentano i reali valori durante il primo ciclo del while)
1. `v2 >>= 3`: Esegue uno shift a destra di 3 bit sulla variabile v2 e assegna il risultato a v2, andando quindi ad eliminare i 3 bit meno significativi.
```
v2 >> 3 = 101111110011010000101100001101110
```
2. `((v2) & 7)`: Effettua un'operazione AND con 7 per ottenere gli ultimi 3 bit di v2.
```
v2 % 7 = 110 (6)
```
3. `<< 3`: Esegue uno shift a sinistra di 3 bit, algebricamente è un'operazione pari alla moltiplicazione per 8.
```
v2 << 3 = 110000 (48)
```
4. `(v1 >> ...)) & 0xFF`: Questa operazione equivale ad utilizzare il risultato ottenuto precedentemente come indice di accesso all'array **v1**.
```
v1 = 01110010_01101111_01101100_01100101_01100100_01010111_01001000_00100000
v1 >> 48 = 01110010_01101111
01110010_01101111 & 0xFF = 01101111 (0x6F)
```
5. `putchar()`: Stampa il carattere ASCII associato all'argomento.
```
putchar(0x6F) stampa 'o'
```

Il ciclo while ripete questi passaggi finchè non vengono consumati tutti i bit di v2, a quel punto il programma termina.

A questo punto dobbiamo sistemare gli "indici" memorizzati in **v2** in modo che vengano stampati a video i caratteri nell'ordine corretto a formare "Hello World".

```
'H':1
'e':4
'l':5
'l':5
'o':6
' ':0
'W':2
'o':6
'r':7
'l':5
'd':3
```

Ricordando che gli indici vengono consumati dal fondo 
```
v2 = 011_101_111_110_010_000_110_101_101_100_001_000
```
Convertendo il numero ad esadecimale otteniamo
```
v2 = 0x77E435B08
```

## Soluzione #2
La challenge può essere risolta sfruttando la symbolic execution
```python
# Author: @devgianlu

def main():
   from z3 import *
   
   s = Solver()
   
   v2 = BitVec('v2', 64)
   v2_ = v2
   
   cc = [ord(c) for c in 'Hello World']
   v1 = 0x726F6C6564574820
   
   for c in cc:
       v2 = v2 >> 3
       s.add(((v1 >> ((v2 & 7) << 3)) & 0xFF) == c)
       
   v2 = v2 >> 3
   s.add(v2 == 0)
   
   assert s.check() == sat
   
   print(hex(s.model()[v2_].as_long()))

if __name__ == "__main__":
    main()
```

## Flag
havceCTF{oWdrlelloH-HelloWorld}

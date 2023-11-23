# Hello As A Service

## Descrizione
Ho trovato sul nostro server il binario di questo Hello As A Service. Ci sará qualche dato di troppo?

## Soluzione
Per risolvere questa challenge abbiamo due modi diversi.
Il primo approccio, il piú immediato, prevede di usare i comandi `strings` e `grep`. Lanciando infatti `strings haas | grep havceCTF`
otteniamo la flag inserito in chiaro all'interno del binario.
Una seconda possibile soluzione é quella di analizzare il binario, notiamo che é presente una struct contenente un stringa ed un intero.
Il modo in cui la struct viene salvata in memoria permette, tramite un *buffer overflow*(inserire 25 caratteri in seguito alla richiesta del nome), 
di sovrascrivere il valore dell'intero presente all'interno della struct. Quel valore viene poi utilizzato come controllo per effettuare
o meno la stampa della flag.

# Flag
havceCTF{Why_d1d_u_us3_fl4G_U_canN07_d0_th1s}

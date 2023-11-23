# Camel Case 1

## Descrizione

Ho trovato questa fantastica applicazione ma sembra che dia qualche errore, per qualsiasi problema l'applicazione consente di mandare una mail allo sviluppatore che la risolverà appena possibile

## Soluzione

Si tratta di un errore nella gestione dei puntatori nella funzione camel case, infatti basta semplicemente riempire il buffer e inserendo come ultimo carattere lo spazio permetterà alla funzione di saltare '\0' e stampare la flag contenuta nella struttura, ecco un esempio di soluzione:

```
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 
```

## Bandiera

```
havceCTF{L0_5P4Z10_CH3_54LT4_IL_T4PP0}
```

## Autore

```
sDibuon
```

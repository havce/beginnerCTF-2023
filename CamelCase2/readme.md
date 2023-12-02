# Camel Case 2

## Descrizione

Dopo aver attentamente studiato la mia prima applicazione sono riusciuto a sistemarla migliorandone la sicurezza, basta inserire la mia mail ma ora richiede l'autenticazione con sistemi avanzatissimi.
Buona fortuna !!

## Soluzione

Se nella vesione precedente era necessario sfruttare il salto della funzione `camelCase` per stampare i byte successivi all buffer, ora abbiamo un sistema di autenticazione con dei caratteri in blacklist, ma osservando bene la tabella ascii e le operazioni bitwise possiamo oltrepassare la blaklist dato che in alcuni casi con la giusta combinazione la fuznione `camelCase` modifica dei caratteri rendendo facile scrivere la mail corretta e stampare la flag.
Ecco un esempio di payload per la risoluzione:

```python
solve = " io^ { gia comino } `flag.com\n"
```

## Bandiera
```
havceCTF{1L_M10_NU0V0_5P0RT_3_B1TW153}
```

## Autore

```
sDibuon
```

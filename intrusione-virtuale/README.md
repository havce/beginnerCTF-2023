# Intrusione Virtuale

## Descrizione
Salve futuro Hacker di élite! Se pensavi di poter entrare nel nostro esclusivo club segreto senza passare attraverso il fuoco delle nostre Regex difensive, 
ti sei sbagliato di grosso! Abbiamo preparato per te una sfida di registrazione all'avanguardia, e le nostre Regex sono più taglienti di un codice malevolo.

Riuscirai a superare le barriere cibernetiche e a infiltrarti nel nostro sistema con stile da vero hacker? 
Solo coloro che padroneggiano l'arte delle Regex potranno ottenere il titolo di "Intruso Supremo"!

Per ottenere il titolo di 'Intruso Supremo' visita la pagina: [http://www.beginner.havce.it:8080](http://www.beginner.havce.it:8080).

HINT: Utilizzare la rappresentazione esadecimale potrebbe esserti d'aiuto.

*Author: [@benjamin](https://github.com/b3nj4m1no)*


## Soluzione
Come affermato nella descrizione, il primo obiettivo era superare le REGEX.


La prima REGEX filtra tutti i caratteri non compresi nella funzione `preg_match`, per esempio lo spazio ( ).
```php
if (!preg_match("/^[a-zA-Z0-9_\-'(),]+$/", $inp, $matches))
```

La seconda REGEX:
- Controlla se la stringa contiene almeno un numero.
- Controlla se la stringa contiene almeno una lettera.
- Controlla se la stringa contiene almeno uno degli underscore ( _ ) o dei trattini ( - ).
```php
if (!(preg_match("/[0-9]/", $inp, $matches) && preg_match("/[a-zA-Z]/", $inp, $matches) && preg_match("/[_-]/", $inp, $matches)))
```




## Flag
havceCTF{m4l3det7o_3va1}

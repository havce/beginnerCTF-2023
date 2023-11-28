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


La prima REGEX controlla se la password contiene caratteri non compresi nella funzione `preg_match`, per esempio lo spazio ( ).
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

La terza REGEX controlla se la password contiene il numero "1".
```php
if (preg_match("/[1]/", $inp, $matches))
```


Arrivati a questo punto dobbiamo porci un altro obiettivo, quello di superare l'ultimo controllo.
```php
if ($inp != "192014812")
```

Ovviamente visto che la stringa contiene il numero "1", la password "192014812" non è valida.
Osservando il codice però, possiamo osservare che prima di `strcmp` viene eseguita la funzione [`eval`](https://www.php.net/manual/en/function.eval.php).
```php
eval("\$input=$inp;");
```
Grazie al funzionamento interno della funzione `eval`, possiamo usarla a nostro favore, passando come parametro "192014812" codificato, per esempio in esadecimale.
Ricordiamoci che non possiamo passare direttamente il numero codificato, ma possiamo per esempio passare `192014822 - 10`.

```py
# Author: @benjamin

import requests

url = "http://www.beginner.havce.it:8080"

# 192145884 --> numero più grande --> hex(192145884) = 0xb73e9dc
# 192014812 --> numero
# 131072    --> differenza --> hex(131072) = 0x20000

payload = {
    "password": "0xb73e9dc-0x20000"
}

r = requests.post(url, data=payload)

print(r.text)
```

## Flag
havceCTF{m4l3det7o_3va1}

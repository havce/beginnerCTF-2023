# Intrusione Virtuale

## Descrizione
Benvenuto nel mondo cifrato di XOR! La tua missione è esplorare le profondità di questa dimensione crittografica e scoprire la chiave segreta utilizzata per cifrare il flag. 
Hai l'abilità e l'ingegno necessari per navigare attraverso xor-world?

Per ottenere il titolo di 'Intruso Supremo' visita la pagina: [http://www.beginner.havce.it:8080](http://www.beginner.havce.it:8080).

*Author: [@benjamin](https://github.com/b3nj4m1no)*


## Soluzione
Come affermato nella descrizione, l'obiettivo era trovare la key utilizzata nello XOR.
Sfruttando la proprietà principale dello XOR ( A ^ B = C --> C ^ B = A ) avendo il `ciphertext` e la `key`, potevamo risalire al `plaintext` ( la flag ).

Per trovare la key, potevamo sfruttare il flag format ( `codevinci{` ).
```
Il ciphertext e': b'\x01\n\n\x0f\x17\x04\x07\r\x0b\x1e\x08\x06\x00\n6\x08\r\x17\x03\x0b\x152\x05\x0b\x03\x0e1\x03\x122\x1a\x01=\x06\x01\x05\r2\x05\x07\t\x001\x18\x14\n\x06\x02\rD\x13'
Inserisci la chiave in ASCII: codevinci{
Il plaintext e': b'benjaminbekido@actjpv]anug_`{IynYcwlcQl|joU}bchad?p'
```
Come si poteva notare, la key restituita era benjamin...

Proviamo ad inserire `benjamin` come chiave...
```
Il ciphertext e': b'\x01\n\n\x0f\x17\x04\x07\r\x0b\x1e\x08\x06\x00\n6\x08\r\x17\x03\x0b\x152\x05\x0b\x03\x0e1\x03\x122\x1a\x01=\x06\x01\x05\r2\x05\x07\t\x001\x18\x14\n\x06\x02\rD\x13'
Inserisci la chiave in ASCII: benjamin
Il plaintext e': b'codevinci{flag_format_leak_is_so_cool_like_rugolo!}'
```

Oh let's go, andiamo a submittare la flag.

## Flag
codevinci{flag_format_leak_is_so_cool_like_rugolo!}

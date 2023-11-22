# Rich and Safe Algorithm

## Descrizione

Avevo bisogno di cifrare la mia flag e ho letto [questo documento](https://www.cemc.uwaterloo.ca/resources/real-world/RSA.pdf) su RSA. Ma i numeri mi sembravano piccoli, quindi li ho aumentati un po'...

## Soluzione

I parametri utilizzati sono vulnerabili. La sicurezza di RSA si basa sul fatto che l'esponenziale modulare superi il valore di N=p*q, e faccia quindi lavorare il modulo. Data la lunghezza in byte della flag, elevare il numero corrispondente alla terza non basta per superare N, per cui non è stato di fatto applicato il modulo. La soluzione è quindi applicare la radice cubica al testo cifrato e riconvertirlo in bytes. Un modo comodo per ottenere in fretta la radice cubica intera è sage:
```
sage: a = 4969780817983922092673987652432791688997809782418032321298298774113339780790762817905395977757784407739382999452027149363578132516516604014941788257054056401254385227822369396215005485843787204
....: 73672554442871477880884504251006325259836465396320862017125
sage: a.nth_root(3)
792098295051061726486227663594082319673634833736271409595206564346307408623518903165
```

Altrimenti anche librerie come [gmpy](https://code.google.com/archive/p/gmpy/) vanno bene (ad esempio per python).
Per riconvertire il numero in bytes, basta utilizzare la funzione ```bytes_to_long``` di ```PyCryptodome```:
```python
>>> from Crypto.Util.number import long_to_bytes
>>> long_to_bytes(792098295051061726486227663594082319673634833736271409595206564346307408623518903165)
b'havceCTF{little_big_big_little_idk}'
```

## Bandiera

```
havceCTF{little_big_big_little_idk}
```


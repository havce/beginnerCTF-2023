 # badAuth

## Descrizione
Ti trovi di fronte al velo delle ombre, una CTF dove la sfida BadAuth è avvolta in un mistero oscuro. Un binario, noto solo come il Sigillo Nefasto, ti è stato consegnato. La sua autenticazione è protetta da un'aura insidiosa. Il tuo compito è penetrare questa oscura barriera e scoprire il segreto celato dietro il velo.

HINT: Analizzare i pacchetti in entrata successivi all'esecuzione del binario potrebbe esserti di aiuto.

*Author: [@benjamin](https://github.com/b3nj4m1no)*

## Soluzione
La categoria della challenge era Network.
Nelle challenges di categoria Network, solitamente ci viene fornito un file .pcap, questa volta invece ci è stato fornito un eseguibile... Un eseguibile ?
Eseguiamo...
![eseguibile](https://i.postimg.cc/2S8PWfSr/eseguibile.png)
Possiamo notare che viene stampato un json... Quindi fa una richiesta ad un sito web?
Non abbiamo il file .pcap, ma possiamo crearlo!

![wireshark interface](https://i.postimg.cc/qB6BtyZj/wireshark.png)

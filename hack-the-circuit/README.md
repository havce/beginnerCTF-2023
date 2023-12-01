# Hack the circuit
## Descrizione
> The flag helped me generating the X signal. Could you retrieve it?
> HINT: The ASCII table is your friend...

*Author: [@Re_Sole](https://github.com/Re-Sole)*

## Soluzione

Per risolvere la chall bisogna costruire la funzione logica che "implementa" il circuito,
quindi trovare la sequenza di bit X che dia in output la sequenza di bit Y.
La soluzione è: `X: 0110100001100001011101100110001101100101010000110101010001000110011110110110010000110000011011100010011100110111010111110110011000110011001101000111001001011111011011000011000001100111001100010110001101011111011001110011010001110100001100110011010101111101`,
che decodificata in ASCII da la flag.

## Flag

`havceCTF{d0n'7_f34r_l0g1c_g4t35}`

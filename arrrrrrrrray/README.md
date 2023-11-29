# arrrrrrrrray
## Description
> Thar's a Jolly Roger hidden in the sea, in an island forgotten by every ole pirate, will ye be able t' discover the secret key?

## Solution
This is a simple pwn challenge, where the array index is an integer which 
can be negative and can be used to retrieve the hex encoded secret_key, since
it is stored inside a struct and it is exactly before the hints array.

## Flag

`havceCTF{dance_wit_Jack_Ketch_ye_flea-bitten_toothless_swab!}`

# nfcookie
## Description
> Get your very own Non-Fungible Cookie! Will you be able to get the golden cookie?

*Author: [@gm_simo](https://github.com/giammisimo)*

## Solution
The website uses the cookie `username` as a session cookie. 
This cookie is not signed and simply contains the base64 encoding of the current user's username.
It is then possible to change the content of the cookie (for example through the browser) to get access to any user account.
The user `admin`, holder of the Golden Cookie, has the flag as its personal quote.

## Flag
havceCTF{cH4rl13_4nd_7he_c00k1e_f4c70Ry}
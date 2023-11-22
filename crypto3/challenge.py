#!/usr/bin/python3

from Crypto.Util.number import getPrime, bytes_to_long

flag = b"redacted"
assert len(flag) == 35

p = getPrime(2048)
q = getPrime(2048)
N = p * q

ct = pow(bytes_to_long(flag), 3) % N

print(N)
print(ct)

import os
import math # HINT: look at https://docs.python.org/3.8/library/math.html#math.isqrt

def encrypt(flag : str):
    enc_flag = flag.encode()
    enc_flag = int.from_bytes(enc_flag, 'big')
    enc_flag **= 2
    enc_flag <<= 10
    enc_flag = enc_flag.to_bytes(105, 'big')
    return enc_flag


def decrypt(enc_flag : str):
    '''
        Hahaha, i deleted your function, good luck decrypting the flag!!!
    '''
    pass


def main():
    flag = os.getenv("FLAG")

    encrypted_flag = encrypt(flag)
    print(f"Encrypted flag: {encrypted_flag}")

    decrypted_flag = decrypt(encrypted_flag)
    print(f"Decrypted flag: {decrypted_flag}")


if(__name__=="__main__"):
    main()

# I ran the program before stealing your function, here is the output it gave me:
# Encrypted flag: b'\xaa=U.\xc1d\xe5\xc2\xb6\x10\x08H\x12AdhX\x17\xf5\xea^\xbd\xd6\xf2\x1f\xbcER~,\x90\x9d#\xbaYl\xa2HR\xf5\xeb\x84}e*Z^\x0b\x14\xa3\xb7\xa2\x80\xc5\x93\xf9\x1c\xea\x00\xe7\x1a\x9eee\x81| 2\xeb)\x07\x0e\x05\x86dX\xc5\t\x14*\xe6;\xfe\xde\xb3\x0c|\xad\xc4>\xdc\x94\xf7(3\x7f\xab\x8ccAN\x16,$\x00'
# Decrypted flag: havceCTF{██████████████████████████████████████████}

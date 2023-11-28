def decrypt(enc_flag : str):
    dec_flag = int.from_bytes(enc_flag, 'big')
    dec_flag >>= 10
    dec_flag = math.isqrt(dec_flag)
    dec_flag = dec_flag.to_bytes(105, 'big')
    dec_flag = dec_flag.decode()
    return dec_flag
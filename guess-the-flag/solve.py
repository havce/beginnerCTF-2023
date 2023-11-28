from pwn import *
import string

alphabet = string.ascii_letters + string.digits + "{}_"

# io = process(b"./guess-the-flag")
io = remote("localhost", 5000)

io.readuntil(b"the flag is ")
flag_len = int(io.readuntil(b" "))

flag = ""
for _ in range(flag_len):
    for choice in alphabet:
        guessed_flag = flag + choice

        io.sendlineafter(b"guess: ", guessed_flag.encode("utf-8"))

        io.readuntil(b"you guessed ")
        chars_guessed = int(io.readuntil(b" "))

        if(chars_guessed > len(flag)):
            flag = guessed_flag
            print(f"FLAG: {flag}")
            break

print(f"Submit the flag: {flag}")
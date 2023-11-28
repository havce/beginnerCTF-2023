#include <stdio.h>
#include <stdint.h>

int main()
{
    uint64_t v1= 0x726F6C6564574820;
    uint64_t v2 = 0xBF342C370;
    
    while (v2) {
        putchar((v1 >> (((v2 >>= 3) & 7) << 3)) & 0xFF);
    }

    return 0;
}

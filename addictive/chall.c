#include <stdio.h>

int check[] = {201, 215, 217, 200, 168, 151, 154, 193, 226, 151, 96, 148, 195, 201, 154, 146, 131, 128, 211, 220, 156, 168, 211, 214, 171, 167, 210, 216, 169, 165, 231, 209, 197, 151, 163, 167, 169, 211, 197, 210, 160, 155, 198, 194, 203, 155, 150, 206, 158, 165, 239};

int main() {
    printf("What's the flag?\n> ");

    char input[53];
    scanf("%52s", input);

    for (int i = 1; i < 52; ++i) {
        int c0 = input[i-1], c1 = input[i];
        if (c0 + c1 != check[i-1]) {
            printf("BAD! This is not the flag.\n");
            return -1;
        }
    }

    printf("CORRECT! This is indeed the flag.");
}
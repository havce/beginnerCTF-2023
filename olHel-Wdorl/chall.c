#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#include <stdlib.h>
#include <string.h>

#define MAX_INPUT_LEN 20
#define MAX_OUTPUT_LEN 22

void setup() {
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);
}

void printBanner() {
    printf("           /$$ /$$   /$$           /$$         /$$      /$$       /$$                     /$$\n"
           "          | $$| $$  | $$          | $$        | $$  /$ | $$      | $$                    | $$\n"
           "  /$$$$$$ | $$| $$  | $$  /$$$$$$ | $$        | $$ /$$$| $$  /$$$$$$$  /$$$$$$   /$$$$$$ | $$\n"
           " /$$__  $$| $$| $$$$$$$$ /$$__  $$| $$ /$$$$$$| $$/$$ $$ $$ /$$__  $$ /$$__  $$| $$__  $$| $$\n"
           "| $$  \\ $$| $$| $$__  $$| $$$$$$$$| $$|______/| $$$$_  $$$$| $$  | $$| $$  \\ $$| $$  \\ $$| $$\n"
           "| $$  | $$| $$| $$  | $$| $$_____/| $$        | $$$/ \\  $$$| $$  | $$| $$  | $$| $$  | $$| $$\n"
           "|  $$$$$$/| $$| $$  | $$|  $$$$$$$| $$        | $$/   \\  $$|  $$$$$$$|  $$$$$$/| $$  | $$| $$\n"
           " \\______/ |__/|__/  |__/ \\_______/|__/        |__/     \\__/ \\_______/ \\______/ |__/  |__/|__/\n\n");
}

void execute(uint64_t v2){
    uint64_t v1= 0x726F6C6564574820;
    while (v2) {
        putchar((v1 >> (((v2 >>= 3) & 7) << 3)) & 0xFF);
    }
    putchar(10);
}

int main() {

    setup();
    
    char *flag = getenv("FLAG");
    char inputString[MAX_INPUT_LEN];

    printBanner();
    printf("Inserisci il valore da assegnare a v2 (e.g., 0xBF342C370): ");

    if (fgets(inputString, sizeof(inputString), stdin) == NULL) {
        printf("Errore durante la lettura.\nSe ritieni non sia corretto contatta un amministratore.\n");
        return 1;
    }

    inputString[strcspn(inputString, "\n")] = '\0';

    uint64_t input;
    char *endptr;
    input = strtoull(inputString, &endptr, 16);

    if (*endptr != '\0') {
        printf("Errore: Input non valido.\nSe ritieni non sia corretto contatta un amministratore.\n");
        return 1;
    }
        
    printf("\n------------------------ Eseguo ------------------------\n");
    const char *codeString = 
    "#include <stdio.h>\n"
    "#include <stdint.h>\n\n"
    "int main() {\n"
    "    uint64_t v1 = 0x726F6C6564574820;\n"
    "    uint64_t v2 = 0x%lX;\n\n"
    "    while (v2) {\n"
    "        putchar((v1 >> (((v2 >>= 3) & 7) << 3)) & 0xFF);\n"
    "    }\n\n"
    "    return 0;\n"
    "}\n";
    printf(codeString, input);
    printf("--------------------------------------------------------\n");

    printf("Il programma ha prodotto questo risultato: ");
    execute(input);

    if(input == 0x77E435B08){
        printf("\nGrazie mille!! Finalmente posso consegnare il compito, sicuramente prendero' un gran voto!\nEccoti un regalo da parte mia %s.\n", flag);
    }

    return 0;
}


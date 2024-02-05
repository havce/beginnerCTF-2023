#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define BUFFERLENGHT 128

struct reportError {
	char messange[BUFFERLENGHT];
	char mailAddress[BUFFERLENGHT];
};


void camelCase( char * message, char * out )
{
    bool flag = false;
    while ( *message ) {
        if ( *message == ' ' ) {
            flag = true;
        } else {
            if ( flag )     *out = *message & 0xDF;
            else            *out = *message | 0x20;
            flag = false;
            out++;
        }
        message++;
    }
    *out = '\0';
}


void printflag()
{
    FILE * filePtr = fopen("flag.txt", "r");
    char flag[BUFFERLENGHT];

    if ( filePtr == NULL ) {
		printf("NON E' STATO POSSIBILE CARICARE LA FLAG !!\n");
		exit(1);
	}

    fgets(flag, BUFFERLENGHT, filePtr);

    printf("ecco la flag:\n%s", flag);

    fclose(filePtr);

}

bool controllCharacter( char * buffer, char * format )
{
    for ( int f = 0 ; format[f] ; f++ ) {
        for ( int b = 0 ; buffer[b] ; b++ ) {
            if ( buffer[b] == format[f] )
                return false;
        }
    }
    return true;
}


int main( int argc, char ** argv )
{
    struct reportError ADMIN = {{0},{"Io~[GiaComino]@flag.com*"}};
    char buffer[BUFFERLENGHT] = {0};

    printf("\t!! -- SONO RIUSCITO A SISTEMARE IL PROBLEMA -- !!\n"
            "  # ORA PER CONTATTARE L'AMMINISTRATORE DEVI SCRIVERE LA SUA MAIL:\n"
            "\t # -> \"Io~[GiaComino]@flag.com*\" \n"
            "  # RICORDA:\n"
            "\t-> Gli spazzi spariscono\n"
            "\t-> Le prime lettere diventano maiuscole\n"
            "\t-> Le altre minuscole\n"
            "\t-> LETTERE NON CONSENTITE ['@', '[', ']', '~', '*']\n"
            "messaggio: "
    );

    fgets(buffer, BUFFERLENGHT, stdin);

    if ( ! controllCharacter(ADMIN.messange, "@[]~*") ) {
        printf("\t\t# HAI PROVATO A FREGARMI !!!"
                "\n\n\t ### USCITA DI EMERGENZA IN CORSO ###\n");
        exit(1);
    }

    camelCase( buffer, ADMIN.messange );

    printf(" ## HAI SCRITTO : %s\n\n", ADMIN.messange);

    if ( strcmp(ADMIN.messange, ADMIN.mailAddress) == 0 ) {
        printflag();
    } else {
        printf("\n ## ERRORE: GLI INDIRIZZI MAIL NON CORRISPONDONO !!\n");
    }

    return 0;
}


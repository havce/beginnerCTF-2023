#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFFERLENGHT 128

struct reportError {
	char messange[BUFFERLENGHT];
	char mailAddress[BUFFERLENGHT];
};


void camelCase( char * message )
{
	while ( * message ) {
		if ( * message == ' ' ) {
			message++;
			putchar( * message & 0xDF );
		}else{
			putchar( * message );
		}
		message++;
	}
	puts("");
}

struct reportError initApp()
{
	struct reportError ADMIN = {{0},{0}};

	FILE * ptrFlag = fopen("flag.txt", "r");
	if ( ptrFlag == NULL ) {
		printf("NON E' STATO POSSIBILE CARICARE LA FLAG !!\n");
		exit(1);
	}

	fgets( ADMIN.mailAddress, BUFFERLENGHT, ptrFlag );
	fclose( ptrFlag );

	return ADMIN;
}


int main( int argc, char ** argv )
{
	struct reportError ADMIN = initApp();

	printf(" !! -- OPS, SEMBREREBBE CHE L'APPLICAZIONE ABBIA DEI PROBRELI  -- !! \n");
	printf("  # PROVA A CONTATTARE L'AMMINISTRATORE\n\t-> Ricorda gli spazzi spariscono\n\t-> E le prime lettere si sostituiscono\nmessaggio: ");

	fgets( ADMIN.messange, BUFFERLENGHT, stdin );

	printf("\n\n===> MESSAGGIO INVIATO:\n-> ");
	camelCase( ADMIN.messange );


	return 0;
}

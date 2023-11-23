#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFFERLENGHT 128

struct user {
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


int main( int argc, char ** argv )
{
	struct user ADMIN = {{0},{0}};

	FILE * ptrFlag = fopen("flag.txt", "r");
	if ( ptrFlag == NULL ) {
		printf("NON E' STATO POSSIBILE CARICARE LA FLAG !!\n");
		return 0;
	}
	fgets( ADMIN.mailAddress, BUFFERLENGHT, ptrFlag );
	fclose( ptrFlag );


	printf(" !! -- OPS, SEMBREREBBE CHE L'APPLICAZIONE ABBIA DEI PROBRELI"
	"  -- !! \n");
	
	printf("  # PROVA A CONTATTARE L'AMMINISTRATORE\n\t-> Ricorda gli spazz"
	"i spariscono\n\t-> E le prime lettere si sostituiscono\nmessaggio: ");


	fgets( ADMIN.messange, BUFFERLENGHT, stdin );


	printf("\n\n===> MESSAGGIO INVIATO:\n-> ");
	camelCase( ADMIN.messange );


	return 0;
}

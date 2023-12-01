#include <stdio.h>
#include <stdlib.h>
 
 void win(){
    system("cat flag.txt");
 }

int main(){    
    char nome[100];
    char cognome[100];

    puts("insersci il tuo nome: ");
    scanf("%50s", nome);
    puts("Inserisci il tuo cognome: ");
    scanf("%300s",cognome);
    printf("CIAO %s %s",nome,cognome);

}
#include <stdio.h>
#include <stdlib.h>

void win() { puts(getenv("FLAG")); }

void buffering() {
  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
}

int main() {
  buffering();
  
  char nome[100];
  char cognome[100];

  puts("insersci il tuo nome: ");
  scanf("%50s", nome);
  puts("Inserisci il tuo cognome: ");
  scanf("%300s", cognome);
  printf("CIAO %s %s", nome, cognome);
}

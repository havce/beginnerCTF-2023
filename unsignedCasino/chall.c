#include <stdio.h>
#include <stdlib.h>
#include <sys/random.h>

void win() { system("cat flag.txt"); }

void game_banner() {
  puts("------------------------");
  puts("BLACK JACK");
  puts("------------------------");

  puts("In blackjack, players aim to achieve a hand value as close to 21 as "
       "possible without exceeding it.");
  puts("Numbered cards hold their face value, face cards are worth 10, and "
       "an Ace can be valued at 1 or 11.");
  puts("The objective is to beat the dealer by having a higher hand without "
       "busting, and a natural");
  puts("blackjack (an Ace and a 10-value card) is the strongest hand.");
}

void banner() {
  puts("------------------------");
  puts("WELCOME TO FERRO'S CASINO");
  puts("------------------------");
  puts("insert 1 to play blackjack.");
  puts("insert any other number to quit.");
}

unsigned long blackjack(unsigned long money) {
  long balance = 0, bet = 0;
  int sumUser = 0, choice = 0, sumDeck = 0;

  game_banner();

  if (money > 1000) {
    system("cat flag.txt");
  }

  while (1) {
    printf("your current money are %lu\n", money);
    puts("what's your bet now?");
    scanf("%ld", &bet);

    // TODO(dev): check this limit
    if (bet > money + 10) {
      printf("NOT ENOUGH MONEY :| \n");
      continue;
    }
    break;
  }

  unsigned int seed = 0;
  if (getrandom(&seed, sizeof(seed), GRND_NONBLOCK) < 0) {
    puts("sky's falling, cannot initialize seed");
    exit(1);
  }

  srand(seed);

  int userCard = rand() % 10 + 1;
  int deckCard = rand() % 10 + 1;

  sumUser += userCard;
  printf("your first card is %d \n", userCard);
  printf("deck first card is %d \n", deckCard);

  for (;;) {
    printf("1. card 2.stay ");
    scanf("%d", &choice);

    switch (choice) {
    case 1:
      sumUser += rand() % 10 + 1;
      printf("this is the sum of your cards %d \n", sumUser);
      if (sumUser > 21) {
        printf("you lost :| \n");
        return money - bet;
      }
      continue;
    case 2:
      sumDeck = 21; // this is not how blackjack works but fck it, find the bug :)
      if (sumUser < sumDeck) {
        puts("you lost :|");
        return money - bet;
      }

      printf("you won, nice! now you have %ld", (long)money);
      return money + (bet * 2);
    default:
      puts("invalid choice!");
      continue;
    }
  }
}

int main() {
  int choice;
  unsigned long money = 10;
  while (1) {
    banner();
    scanf("%d", &choice);
    switch (choice) {
    case 1:
      money = blackjack(money);
      break;
    default:
      puts("bye bye");
      return 0;
    }
  }
}

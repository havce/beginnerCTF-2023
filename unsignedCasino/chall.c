#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void win(){
    system("cat flag.txt");
}
void bjbanner(){
    printf("------------------------ \n");
    printf("BLACK JACK\n");
    printf("------------------------\n");

    printf("In blackjack, players aim to achieve a hand value as close to 21 as possible without exceeding it. \n");
    printf("Numbered cards hold their face value, face cards are worth 10, and an Ace can be valued at 1 or 11. \n");
    printf("The objective is to beat the dealer by having a higher hand without busting, and a natural \n");
    printf("blackjack (an Ace and a 10-value card) is the strongest hand. \n");

}

void banner(){
    printf("------------------------ \n");
    printf("WELCOME TO FERRO'S CASINO \n");
    printf("------------------------\n");
    printf("insert 1 to play blackjack.\n");
    printf("insert any other number to quit.\n");

}


unsigned long blackjack(unsigned long money){
    long balance; 
    int sumUser = 0, choice=0, sumDeck = 0;
    long bet=0;
    bjbanner();
    if(money > 1000){
        system("cat flag.txt");
    }
    while(1){
        printf("your current money are %lu\n ", money);
        printf(" whats your bet now? \n" );
        scanf("%ld", &bet);
        if(bet>money+10){
            printf("NOT ENOUGH MONEY :| \n");
        }
        else{
            break;
        }
    }
    srand(time(NULL));
    int userCard = rand() % 10 + 1;
    int deckCard = rand() % 10 + 1;
    sumUser += userCard;
    printf("your first card is %d \n", userCard);
    printf("deck first card is %d \n", deckCard);
    while(1){
        printf("1. card 2.stay ");
        scanf("%d",&choice);
        if(choice < 0 || choice > 2){
            puts("wrong choice!");
            balance = money;
            return balance;
        }
        else if (choice == 1){
            int cardx = rand() % 10 + 1;
            sumUser += cardx;
            printf("this is the sum of your cards %d \n", sumUser);
            if(sumUser >21 ){
                printf(" You lost :| \n");
                balance = money-bet;
                break;
            }
        }
        else if(choice == 2 ){
            sumDeck = 21; // this is not how bj works but fuck it find the bug :) 
            if(sumUser < sumDeck){
                printf(" You lost :| \n");
                balance = money-bet;
                break;
            }
            else{
                balance = money + ( bet *2);
                printf("You won nice! now you have %d",money );
                return balance;
            }
            
        }
    }

   return balance;

}
int main(){
    int choice;
    unsigned long money = 10;
    while(1){
        banner();
        scanf("%d",&choice);
        if(choice == 1){
            money = blackjack(money);
        }
        else{
            printf("bye bye");
            return 0;
        }
    }
}

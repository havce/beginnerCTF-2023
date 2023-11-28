#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define DEBUG // TODO: remove before pushing to production

void setup();
void get_input(char *buf, int len);
int compare(char *buf1, char *buf2, int len);

int main(void)
{
    setup();

    char *flag = getenv("FLAG");
    char guess[strlen(flag) + 1];

#ifdef DEBUG
     printf("DEBUG => the flag is %ld characters long.\n", strlen(flag));
#endif

    while(1) {
        printf("Please insert here your flag guess: ");
        get_input(guess, strlen(flag));

        int matching_chars = compare(flag, guess, strlen(flag));

    #ifdef DEBUG
        printf("DEBUG => you guessed %d characters correctly.\n", matching_chars);
    #endif

        if(matching_chars == strlen(flag)) {
            printf("Congrats, go submit your flag!!\n");
            break;
        } else {
            printf("Bad luck, try again.\n");
        }
    }

    return 0;
}

/*
 * This function consumes ALL the input from stdin
 */
void get_input(char *buf, int len)
{
    int i = 0;
    int c;
    while ((c = getchar()) != '\n' && c != EOF) {
        // copy char only if it fits in the buffer
        if(i < len) {
            buf[i] = c;
            i++;
        }
    }
    // set string termination '\0' at the end of the buffer
    buf[len] = '\0';
}

/*
 *  If DEBUG macro is defined you'll get some useful information in the return value
 */
int compare(char *buf1, char *buf2, int len)
{
    int counter = 0;
    for(int i = 0; i < len; i++) {
        if(buf1[i] == buf2[i]) counter++;
        else                   break;
    }

#ifdef DEBUG
    return counter;
#else
    return -1;
#endif
}

void setup()
{
    setbuf(stdout, NULL);
    setbuf(stdin, NULL);
    setbuf(stderr, NULL);
}
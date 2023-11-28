#include <stdio.h>
#include <stdlib.h>
#include <sys/random.h>
#include <time.h>

// This function is needed only for connecting to the challenge.
// You can skip this function.
void bufferin() {
  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);
}

typedef struct {
  unsigned long secret_key;
  unsigned long hints[10];
} ChestLock;

ChestLock chest;

void init() {
  if (getrandom(&chest.secret_key, sizeof(chest.secret_key), GRND_NONBLOCK) <
      0) {
    puts("Thar was an error, help!");
    exit(1);
  }

  srand(time(NULL));
  // Arrrrrr, they be nah actual hints, hahahaaaa!
  for (int i = 0; i < 10; i++) {
    chest.hints[i] = rand() % 1337 + 1;
  }
}

int main() {
  bufferin();

  init();

  puts("Ahoy pirate, how're ye doin'?");
  puts("Ye 'ave come across me booty chest!");
  puts("Thar be a Jolly Roger hidden inside, waitin' fer ye.");
  puts("Ye first needs t' give us the secret arrr!");
  puts("");

  long choice = 0;
  long hint = 0;

  unsigned long user_key = 0;

  do {
    puts("'tis wha' ye can do:");
    puts("1. see hints");
    puts("2. open the chest");
    puts("3. walk the plank");

    scanf("%ld", &choice);
    switch (choice) {
    case 1:
      puts("Tell us which hint ye wants t' hear");
      scanf("%ld", &hint);
      if (hint >= 10) {
        puts("thar ain't any hint where ye're lookin' at!");
        continue;
      }
      printf("Hint[%ld] = %lx\n", hint, chest.hints[hint]);
      break;
    case 2:
      puts("So, I heard that ye wants t' open the holy booty.");
      printf("Tell me the secret, afore I open the chest: ");
      scanf("%lu", &user_key);
      if (user_key == chest.secret_key) {
        printf("Here's the Jolly Roger: %s", getenv("FLAG"));
        return 0;
      }

      puts("Ye're nah the real owner o' the secret loot, ye're a pirate! Walk the plank, pirate!");
      exit(1);
    default:
      puts("fair winds t' ye!");
      return 0;
    }
  } while (choice != 3);
}

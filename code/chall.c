#include <seccomp.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <unistd.h>

void setup() {
  setbuf(stdin, NULL);
  setbuf(stdout, NULL);
  setbuf(stderr, NULL);
}

int main() {
  int rc = -1;

  void *code = mmap(0, 0x10000, PROT_EXEC | PROT_READ | PROT_WRITE,
                    MAP_ANONYMOUS | MAP_PRIVATE, -1, 0);

  setup();

  puts("Immetti il tuo shellcode. La flag è dentro /flag.txt. Non tutto lo "
       "shellcode che immetti è valido!");

  read(0, code, 0x10000);

  scmp_filter_ctx ctx;
  ctx = seccomp_init(SCMP_ACT_KILL);

  rc = seccomp_rule_add(ctx, SCMP_ACT_ALLOW, SCMP_SYS(open), 0);
  if (rc < 0) {
    goto out;
  }

  rc = seccomp_rule_add(ctx, SCMP_ACT_ALLOW, SCMP_SYS(mmap), 0);
  if (rc < 0) {
    goto out;
  }

  rc = seccomp_rule_add(ctx, SCMP_ACT_ALLOW, SCMP_SYS(write), 0);
  if (rc < 0) {
    goto out;
  }

  rc = seccomp_load(ctx);
  if (rc < 0) {
    goto out;
  }

  // Execute the code.
  ((void (*)(void))(code))();
out:
  seccomp_release(ctx);
  return -rc;
}

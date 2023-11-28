#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUF_LEN 128

struct Report {
  char message[BUF_LEN];
  char details[BUF_LEN];
};

void camelCase(char *message) {
  while (*message) {
    if (*message == ' ') {
      message++;
      putchar(*message & 0xDF);
    } else {
      putchar(*message);
    }
    message++;
  }
  puts("");
}

// This function isn't needed for solving the chal. You can skip this function.
void buffering() {
  setvbuf(stdin, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);
  setvbuf(stdout, NULL, _IONBF, 0);
}

void fillReportDetails(struct Report *report) {
  strncpy(report->details, getenv("FLAG"), BUF_LEN);
}

int main(int argc, char **argv) {
  struct Report report;

  buffering();

  // The application crashed: fill the details of the report.
  fillReportDetails(&report);

  puts("!! -- Ops, it looks like the application has encountered a problem -- "
       "!! ");
  puts("# Contact the administrator and report the problem.");
  puts("Before sending it we will format your message.");
  puts("\t-> Remember that the whitespaces disappear.");
  puts("\t-> The first letter will be replaced with the uppercase");
  printf("Enter your message: ");

  fgets(report.message, BUF_LEN, stdin);

  puts("");
  puts("===> Report sent!");
  puts("Your report-> ");
  camelCase(report.message);

  return 0;
}

#include <stdio.h>

// non ho voglia di mettere il \n  ognivolta che stampo una stringa
void println(char *msg) {
    printf("%s\n", msg);
}

void f() {
    println("I'm useless <3");
}

void g() {
    char msg[20] = "Hello, World!";
    println(msg);
}

void havce_fortissimi_e_bellissimi(int b) {
    if(b)
        println("Havce best team ever!1!1!");
    else
        println("Just adding some fucking strings :)");
}

void print_flag() {
    println("havceCTF{Why_d1d_u_us3_fl4G_U_canN07_d0_th1s}");
}

void hello(char* name) {
    printf("Hello %s\n", name);
}

struct smth{
    char name[24];
    int flag;
};

int main() {
    struct smth data;
    data.flag = 0;
    println("Welcome to our wonderful Hello As A Service!!!");
    printf("What is your name?");
    scanf("%s", data.name);
    hello(data.name);
    if(data.flag)
        print_flag();
    else
        havce_fortissimi_e_bellissimi(1);
}
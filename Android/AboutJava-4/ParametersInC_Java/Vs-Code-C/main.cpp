#include <stdio.h>

// int main() {
int main(int argc, char** argv) {
// int main(int argc, char* argv[]) {
    printf("Hello, world!\n");

    printf("argc: %d\n", argc);
    for (int i = 0; i < argc; i++)
    {
        printf("%d: %s\n", i, argv[i]);
    }
    
    return 0;
}
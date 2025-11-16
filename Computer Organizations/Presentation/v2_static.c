#include <stdio.h>
#include <stdlib.h>

#define MAX_CARDS 100
#define MAX_ROUNDS 100

// 檢查並移除 pair
void removePairs(int cards[], int *count) {
    int i, j;
    int found = 1;
    
    // 持續檢查直到沒有 pair 可以移除
    while (found && *count > 1) {
        found = 0;
        for (i = 0; i < *count - 1; i++) {
            // 只有數字牌 (>0) 可以配對
            if (cards[i] > 0) {
                for (j = i + 1; j < *count; j++) {
                    if (cards[j] > 0 && cards[i] == cards[j]) {
                        // 找到 pair，移除這兩張牌
                        // 先移除後面的牌
                        for (int k = j; k < *count - 1; k++) {
                            cards[k] = cards[k + 1];
                        }
                        (*count)--;
                        
                        // 再移除前面的牌
                        for (int k = i; k < *count - 1; k++) {
                            cards[k] = cards[k + 1];
                        }
                        (*count)--;
                        
                        found = 1;
                        break;
                    }
                }
            }
            if (found) break;
        }
    }
}

int main() {
    int cardsA[MAX_CARDS], cardsB[MAX_CARDS];
    int countA = 0, countB = 0;
    int round;
    
    // 讀取玩家A的手牌
    while (scanf("%d", &cardsA[countA]) == 1) {
        countA++;
        char c = getchar();
        if (c == '\n') break;
    }
    
    // 讀取玩家B的手牌
    while (scanf("%d", &cardsB[countB]) == 1) {
        countB++;
        char c = getchar();
        if (c == '\n' || c == EOF) break;
    }
    
    // 初始移除已有的 pairs
    removePairs(cardsA, &countA);
    removePairs(cardsB, &countB);
    
    // 遊戲開始
    for (round = 0; round < MAX_ROUNDS; round++) {
        // 檢查遊戲是否結束
        if (countA == 0) {
            printf("A Win\n");
            return 0;
        }
        if (countB == 0) {
            printf("B Win\n");
            return 0;
        }
        
        // 玩家A從玩家B抽牌 (index=0)
        if (countB > 0) {
            cardsA[countA] = cardsB[0];
            countA++;
            
            // 移除玩家B的第一張牌
            for (int i = 0; i < countB - 1; i++) {
                cardsB[i] = cardsB[i + 1];
            }
            countB--;
            
            // 玩家A檢查並移除 pairs
            removePairs(cardsA, &countA);
        }
        
        // 檢查遊戲是否結束
        if (countA == 0) {
            printf("A Win\n");
            return 0;
        }
        if (countB == 0) {
            printf("B Win\n");
            return 0;
        }
        
        // 玩家B從玩家A抽牌 (index=0)
        if (countA > 0) {
            cardsB[countB] = cardsA[0];
            countB++;
            
            // 移除玩家A的第一張牌
            for (int i = 0; i < countA - 1; i++) {
                cardsA[i] = cardsA[i + 1];
            }
            countA--;
            
            // 玩家B檢查並移除 pairs
            removePairs(cardsB, &countB);
        }
    }
    
    // 100回合後仍無勝負
    printf("Draw\n");
    return 0;
}

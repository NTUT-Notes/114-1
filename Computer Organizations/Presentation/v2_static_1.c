#include <stdio.h>
#include <stdlib.h>

#define MAX_CARDS 100
#define MAX_ROUNDS 100

// 檢查並移除 pair

// 將陣列中指定位置的元素向前移動（移除該元素）
void shiftLeft(int cards[], int *count, int index) {
    for (int k = index; k < *count - 1; k++) {
        cards[k] = cards[k + 1];
    }
    (*count)--;
}

// 尋找從 start 開始第一個與 value 相同且大於 0 的牌的位置
// 如果找不到返回 -1
int findMatchingCard(int cards[], int count, int start, int value) {
    for (int j = start; j < count; j++) {
        if (cards[j] > 0 && cards[j] == value) {
            return j;
        }
    }
    return -1;
}

// 檢查指定位置的牌是否為有效數字牌（>0）
int isValidCard(int card) {
    return card > 0;
}

// 嘗試從指定位置開始尋找並移除一對 pair
// 如果找到並移除返回 1，否則返回 0
int tryRemovePairFrom(int cards[], int *count, int startIndex) {
    if (startIndex >= *count - 1) {
        return 0;
    }
    
    if (!isValidCard(cards[startIndex])) {
        return 0;
    }
    
    int value = cards[startIndex];
    int matchIndex = findMatchingCard(cards, *count, startIndex + 1, value);
    
    if (matchIndex != -1) {
        // 找到配對，先移除後面的牌（index 較大的）
        shiftLeft(cards, count, matchIndex);
        // 再移除前面的牌（index 較小的）
        shiftLeft(cards, count, startIndex);
        return 1;
    }
    
    return 0;
}

// 掃描整副牌，嘗試移除一對 pair
// 如果找到並移除返回 1，否則返回 0
int scanAndRemoveOnePair(int cards[], int *count) {
    for (int i = 0; i < *count - 1; i++) {
        if (tryRemovePairFrom(cards, count, i)) {
            return 1;
        }
    }
    return 0;
}

// 檢查並移除所有 pair
void removePairs(int cards[], int *count) {
    // 持續檢查直到沒有 pair 可以移除
    while (*count > 1 && scanAndRemoveOnePair(cards, count)) {
        // 繼續尋找下一對 pair
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
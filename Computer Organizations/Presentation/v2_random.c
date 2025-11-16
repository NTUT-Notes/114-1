#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_CARDS 5
#define MAX_ROUNDS 100

// 玩家結構
typedef struct {
    int cards[MAX_CARDS];
    int card_count;
} Player;

// 初始化玩家手牌
void init_player(Player *p, int cards[], int count) {
    p->card_count = count;
    for (int i = 0; i < count; i++) {
        p->cards[i] = cards[i];
    }
}

// 顯示玩家手牌（除錯用）
void print_cards(Player *p, char name) {
    printf("玩家%c手牌: ", name);
    for (int i = 0; i < p->card_count; i++) {
        printf("%d ", p->cards[i]);
    }
    printf("(共%d張)\n", p->card_count);
}

// 從對手手上隨機抽牌
void draw_card(Player *from, Player *to) {
    if (from->card_count == 0) return;
    
    int idx = rand() % from->card_count;
    int drawn_card = from->cards[idx];
    
    // 從 from 移除該牌
    for (int i = idx; i < from->card_count - 1; i++) {
        from->cards[i] = from->cards[i + 1];
    }
    from->card_count--;
    
    // 加入 to 的手牌
    to->cards[to->card_count] = drawn_card;
    to->card_count++;
}

// 檢查並丟出 pair
void discard_pairs(Player *p) {
    for (int i = 0; i < p->card_count; i++) {
        if (p->cards[i] <= 0) continue; // 跳過無效牌和鬼牌
        
        for (int j = i + 1; j < p->card_count; j++) {
            if (p->cards[j] <= 0) continue;
            
            // 找到兩張相同數字的牌
            if (p->cards[i] == p->cards[j]) {
                // 標記為無效牌 (0)
                p->cards[i] = 0;
                p->cards[j] = 0;
                
                // 壓縮陣列，移除無效牌
                int write_idx = 0;
                for (int k = 0; k < p->card_count; k++) {
                    if (p->cards[k] != 0) {
                        p->cards[write_idx++] = p->cards[k];
                    }
                }
                p->card_count = write_idx;
                
                // 重新開始檢查（因為陣列已改變）
                discard_pairs(p);
                return;
            }
        }
    }
}

// 檢查是否有玩家獲勝
int check_winner(Player *a, Player *b) {
    if (a->card_count == 0) return 1; // A Win
    if (b->card_count == 0) return 2; // B Win
    return 0; // 繼續遊戲
}

int main() {
    srand(time(NULL));
    
    Player player_a, player_b;
    
    // 初始化玩家手牌（可自行修改）
    int cards_a[] = {1, 2, -1, 4, 5};
    int cards_b[] = {1, 2, -1, 6, 7};
    
    init_player(&player_a, cards_a, 5);
    init_player(&player_b, cards_b, 5);
    
    int round = 0;
    
    while (round < MAX_ROUNDS) {
        round++;
        
        // 玩家A抽牌
        draw_card(&player_b, &player_a);
        discard_pairs(&player_a);
        
        int result = check_winner(&player_a, &player_b);
        if (result == 1) {
            printf("A Win\n");
            return 0;
        } else if (result == 2) {
            printf("B Win\n");
            return 0;
        }
        
        // 玩家B抽牌
        draw_card(&player_a, &player_b);
        discard_pairs(&player_b);
        
        result = check_winner(&player_a, &player_b);
        if (result == 1) {
            printf("A Win\n");
            return 0;
        } else if (result == 2) {
            printf("B Win\n");
            return 0;
        }
    }
    
    printf("Draw\n");
    return 0;
}
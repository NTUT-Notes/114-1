.data
cardsA:     .space 400      # 100 integers * 4 bytes
cardsB:     .space 400      # 100 integers * 4 bytes
countA:     .word 0
countB:     .word 0
MAX_ROUNDS: .word 100

msgAWin:    .asciiz "A Win\n"
msgBWin:    .asciiz "B Win\n"
msgDraw:    .asciiz "Draw\n"
promptA:    .asciiz "Enter cards for player A (space-separated, end with -999):\n"
promptB:    .asciiz "Enter cards for player B (space-separated, end with -999):\n"

.text
.globl main

main:
    # 提示並讀取玩家A的手牌
    li $v0, 4
    la $a0, promptA
    syscall
    
    la $s0, cardsA          # $s0 = cardsA 陣列位址
    li $s1, 0               # $s1 = countA = 0
    
read_A_loop:
    li $v0, 5               # syscall: read integer
    syscall
    move $t0, $v0           # $t0 = 讀入的數字
    
    # 檢查是否為結束標記 -999
    li $t7, -999
    beq $t0, $t7, read_A_end
    
    # 儲存到 cardsA[countA]
    sll $t1, $s1, 2         # $t1 = countA * 4
    add $t1, $t1, $s0       # $t1 = &cardsA[countA]
    sw $t0, 0($t1)          # cardsA[countA] = 數字
    addi $s1, $s1, 1        # countA++
    
    blt $s1, 100, read_A_loop   # 最多100張牌
    
read_A_end:
    sw $s1, countA          # 儲存 countA
    
    # 提示並讀取玩家B的手牌
    li $v0, 4
    la $a0, promptB
    syscall
    
    la $s2, cardsB          # $s2 = cardsB 陣列位址
    li $s3, 0               # $s3 = countB = 0
    
read_B_loop:
    li $v0, 5               # syscall: read integer
    syscall
    move $t0, $v0           # $t0 = 讀入的數字
    
    # 檢查是否為結束標記 -999
    li $t7, -999
    beq $t0, $t7, read_B_end
    
    # 儲存到 cardsB[countB]
    sll $t1, $s3, 2         # $t1 = countB * 4
    add $t1, $t1, $s2       # $t1 = &cardsB[countB]
    sw $t0, 0($t1)          # cardsB[countB] = 數字
    addi $s3, $s3, 1        # countB++
    
    blt $s3, 100, read_B_loop
    
read_B_end:
    sw $s3, countB          # 儲存 countB
    
    # 初始移除配對
    la $a0, cardsA
    la $a1, countA
    jal removeAllPairs
    
    la $a0, cardsB
    la $a1, countB
    jal removeAllPairs
    
    # 遊戲主迴圈
    li $t9, 0               # $t9 = round counter
    
game_loop:
    lw $t0, MAX_ROUNDS
    bge $t9, $t0, game_draw # if round >= 100, draw
    
    # === 玩家A的回合：從玩家B抽第一張牌 ===
    lw $s3, countB
    beqz $s3, check_win_after_A # if countB == 0, check win
    
    # 取得 cardsB[0]
    la $s2, cardsB
    lw $t0, 0($s2)          # $t0 = cardsB[0]
    
    # 加到 cardsA 的尾端
    lw $s1, countA
    la $s0, cardsA
    sll $t1, $s1, 2
    add $t1, $t1, $s0
    sw $t0, 0($t1)          # cardsA[countA] = cardsB[0]
    addi $s1, $s1, 1
    sw $s1, countA          # countA++
    
    # 移除 cardsB[0]（將所有元素往前移）
    lw $s3, countB
    la $s2, cardsB
    li $t2, 0               # i = 0
shift_B_loop1:
    addi $t3, $s3, -1
    bge $t2, $t3, shift_B_end1  # if i >= countB-1, end
    
    sll $t4, $t2, 2
    add $t5, $s2, $t4       # $t5 = &cardsB[i]
    lw $t6, 4($t5)          # $t6 = cardsB[i+1]
    sw $t6, 0($t5)          # cardsB[i] = cardsB[i+1]
    
    addi $t2, $t2, 1
    j shift_B_loop1
    
shift_B_end1:
    addi $s3, $s3, -1
    sw $s3, countB          # countB--
    
    # 移除 A 的配對
    la $a0, cardsA
    la $a1, countA
    jal removeAllPairs
    
check_win_after_A:
    # 檢查遊戲是否結束
    lw $s1, countA
    beqz $s1, a_win         # if countA == 0, A wins
    lw $s3, countB
    beqz $s3, b_win         # if countB == 0, B wins
    
    # === 玩家B的回合：從玩家A抽第一張牌 ===
    lw $s1, countA
    beqz $s1, check_win_after_B
    
    # 取得 cardsA[0]
    la $s0, cardsA
    lw $t0, 0($s0)          # $t0 = cardsA[0]
    
    # 加到 cardsB 的尾端
    lw $s3, countB
    la $s2, cardsB
    sll $t1, $s3, 2
    add $t1, $t1, $s2
    sw $t0, 0($t1)          # cardsB[countB] = cardsA[0]
    addi $s3, $s3, 1
    sw $s3, countB          # countB++
    
    # 移除 cardsA[0]
    lw $s1, countA
    la $s0, cardsA
    li $t2, 0
shift_A_loop1:
    addi $t3, $s1, -1
    bge $t2, $t3, shift_A_end1
    
    sll $t4, $t2, 2
    add $t5, $s0, $t4
    lw $t6, 4($t5)
    sw $t6, 0($t5)
    
    addi $t2, $t2, 1
    j shift_A_loop1
    
shift_A_end1:
    addi $s1, $s1, -1
    sw $s1, countA
    
    # 移除 B 的配對
    la $a0, cardsB
    la $a1, countB
    jal removeAllPairs
    
check_win_after_B:
    lw $s1, countA
    beqz $s1, a_win
    lw $s3, countB
    beqz $s3, b_win
    
    # 下一回合
    addi $t9, $t9, 1
    j game_loop

a_win:
    li $v0, 4
    la $a0, msgAWin
    syscall
    j exit

b_win:
    li $v0, 4
    la $a0, msgBWin
    syscall
    j exit

game_draw:
    li $v0, 4
    la $a0, msgDraw
    syscall
    j exit

exit:
    li $v0, 10
    syscall

# ===== removeAllPairs 函數 =====
# $a0 = cards 陣列位址
# $a1 = count 變數位址
removeAllPairs:
    addi $sp, $sp, -8
    sw $ra, 4($sp)
    sw $s4, 0($sp)
    
removeAllPairs_loop:
    move $s4, $a0           # 保存參數
    lw $t0, 0($a1)          # $t0 = count
    ble $t0, 1, removeAllPairs_end  # if count <= 1, end
    
    jal removePair
    bnez $v0, removeAllPairs_loop   # if found pair, continue
    
removeAllPairs_end:
    lw $s4, 0($sp)
    lw $ra, 4($sp)
    addi $sp, $sp, 8
    jr $ra

# ===== removePair 函數 =====
# $a0 = cards 陣列位址
# $a1 = count 變數位址
# 返回值 $v0: 1 if pair removed, 0 otherwise
removePair:
    addi $sp, $sp, -4
    sw $ra, 0($sp)
    
    lw $t0, 0($a1)          # $t0 = count
    li $t1, 0               # $t1 = i
    
removePair_outer:
    addi $t2, $t0, -1
    bge $t1, $t2, removePair_not_found  # if i >= count-1
    
    # 取得 cards[i]
    sll $t3, $t1, 2
    add $t4, $a0, $t3
    lw $t5, 0($t4)          # $t5 = cards[i]
    
    # 檢查是否 > 0（不是鬼牌）
    blez $t5, removePair_outer_next
    
    # 內層迴圈找配對
    addi $t6, $t1, 1        # $t6 = j = i+1
    
removePair_inner:
    bge $t6, $t0, removePair_outer_next  # if j >= count
    
    # 取得 cards[j]
    sll $t7, $t6, 2
    add $t8, $a0, $t7
    lw $t9, 0($t8)          # $t9 = cards[j]
    
    # 檢查是否配對
    blez $t9, removePair_inner_next
    bne $t5, $t9, removePair_inner_next
    
    # 找到配對！移除 cards[j]
    move $s5, $a0           # 保存 cards
    move $s6, $a1           # 保存 count 位址
    move $s7, $t1           # 保存 i
    
    # 移除 j
    move $t2, $t6           # k = j
remove_j_loop:
    lw $t0, 0($s6)
    addi $t3, $t0, -1
    bge $t2, $t3, remove_j_end
    
    sll $t4, $t2, 2
    add $t8, $s5, $t4
    lw $t9, 4($t8)
    sw $t9, 0($t8)
    
    addi $t2, $t2, 1
    j remove_j_loop
    
remove_j_end:
    lw $t0, 0($s6)
    addi $t0, $t0, -1
    sw $t0, 0($s6)          # count--
    
    # 移除 i
    move $t2, $s7           # k = i
remove_i_loop:
    lw $t0, 0($s6)
    addi $t3, $t0, -1
    bge $t2, $t3, remove_i_end
    
    sll $t4, $t2, 2
    add $t8, $s5, $t4
    lw $t9, 4($t8)
    sw $t9, 0($t8)
    
    addi $t2, $t2, 1
    j remove_i_loop
    
remove_i_end:
    lw $t0, 0($s6)
    addi $t0, $t0, -1
    sw $t0, 0($s6)          # count--
    
    li $v0, 1               # 返回 1 (found)
    lw $ra, 0($sp)
    addi $sp, $sp, 4
    jr $ra
    
removePair_inner_next:
    addi $t6, $t6, 1
    j removePair_inner
    
removePair_outer_next:
    addi $t1, $t1, 1
    j removePair_outer
    
removePair_not_found:
    li $v0, 0               # 返回 0 (not found)
    lw $ra, 0($sp)
    addi $sp, $sp, 4
    jr $ra
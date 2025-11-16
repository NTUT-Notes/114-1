.data
cardsA: .space 400          # 100 * 4 bytes for player A's cards
cardsB: .space 400          # 100 * 4 bytes for player B's cards
countA: .word 0             # number of cards for player A
countB: .word 0             # number of cards for player B
msgAWin: .asciiz "A Win\n"
msgBWin: .asciiz "B Win\n"
msgDraw: .asciiz "Draw\n"

.text
.globl main

main:
    # Read player A's cards
    la $t0, cardsA          # address of cardsA
    la $t1, countA
    lw $t2, 0($t1)          # countA = 0
    
read_A:
    li $v0, 5               # read integer
    syscall
    move $t3, $v0           # store the input
    
    sll $t4, $t2, 2         # offset = countA * 4
    add $t5, $t0, $t4       # address = cardsA + offset
    sw $t3, 0($t5)          # cardsA[countA] = input
    addi $t2, $t2, 1        # countA++
    
    li $v0, 12              # read char
    syscall
    move $t6, $v0
    beq $t6, 10, done_read_A  # if '\n', done reading A
    j read_A
    
done_read_A:
    sw $t2, 0($t1)          # save countA
    
    # Read player B's cards
    la $t0, cardsB          # address of cardsB
    la $t1, countB
    lw $t2, 0($t1)          # countB = 0
    
read_B:
    li $v0, 5               # read integer
    syscall
    move $t3, $v0           # store the input
    
    sll $t4, $t2, 2         # offset = countB * 4
    add $t5, $t0, $t4       # address = cardsB + offset
    sw $t3, 0($t5)          # cardsB[countB] = input
    addi $t2, $t2, 1        # countB++
    
    li $v0, 12              # read char
    syscall
    move $t6, $v0
    beq $t6, 10, done_read_B  # if '\n', done reading B
    beq $t6, -1, done_read_B  # if EOF, done reading B
    j read_B
    
done_read_B:
    sw $t2, 0($t1)          # save countB
    
    # Remove initial pairs for A
    la $a0, cardsA
    la $a1, countA
    jal removePairs
    
    # Remove initial pairs for B
    la $a0, cardsB
    la $a1, countB
    jal removePairs
    
    # Game loop (max 100 rounds)
    li $s0, 0               # round counter
    
game_loop:
    bge $s0, 100, draw_game  # if round >= 100, draw
    
    # Check if A wins (countA == 0)
    la $t0, countA
    lw $t1, 0($t0)
    beqz $t1, a_wins
    
    # Check if B wins (countB == 0)
    la $t0, countB
    lw $t1, 0($t0)
    beqz $t1, b_wins
    
    # Player A draws from B (index 0)
    la $t0, countB
    lw $t1, 0($t0)
    beqz $t1, skip_a_draw   # if countB == 0, skip
    
    # Get cardsB[0]
    la $t2, cardsB
    lw $t3, 0($t2)          # t3 = cardsB[0]
    
    # Add to cardsA[countA]
    la $t4, cardsA
    la $t5, countA
    lw $t6, 0($t5)          # t6 = countA
    sll $t7, $t6, 2
    add $t7, $t4, $t7
    sw $t3, 0($t7)          # cardsA[countA] = cardsB[0]
    addi $t6, $t6, 1
    sw $t6, 0($t5)          # countA++
    
    # Remove cardsB[0] by shifting
    la $t2, cardsB
    la $t0, countB
    lw $t1, 0($t0)
    addi $t1, $t1, -1       # countB--
    move $t4, $zero         # i = 0
shift_b_loop:
    bge $t4, $t1, done_shift_b
    sll $t5, $t4, 2
    add $t6, $t2, $t5
    lw $t7, 4($t6)          # cardsB[i+1]
    sw $t7, 0($t6)          # cardsB[i] = cardsB[i+1]
    addi $t4, $t4, 1
    j shift_b_loop
done_shift_b:
    sw $t1, 0($t0)          # save countB
    
    # Remove pairs for A
    la $a0, cardsA
    la $a1, countA
    jal removePairs
    
skip_a_draw:
    # Check if A wins
    la $t0, countA
    lw $t1, 0($t0)
    beqz $t1, a_wins
    
    # Check if B wins
    la $t0, countB
    lw $t1, 0($t0)
    beqz $t1, b_wins
    
    # Player B draws from A (index 0)
    la $t0, countA
    lw $t1, 0($t0)
    beqz $t1, skip_b_draw   # if countA == 0, skip
    
    # Get cardsA[0]
    la $t2, cardsA
    lw $t3, 0($t2)          # t3 = cardsA[0]
    
    # Add to cardsB[countB]
    la $t4, cardsB
    la $t5, countB
    lw $t6, 0($t5)          # t6 = countB
    sll $t7, $t6, 2
    add $t7, $t4, $t7
    sw $t3, 0($t7)          # cardsB[countB] = cardsA[0]
    addi $t6, $t6, 1
    sw $t6, 0($t5)          # countB++
    
    # Remove cardsA[0] by shifting
    la $t2, cardsA
    la $t0, countA
    lw $t1, 0($t0)
    addi $t1, $t1, -1       # countA--
    move $t4, $zero         # i = 0
shift_a_loop:
    bge $t4, $t1, done_shift_a
    sll $t5, $t4, 2
    add $t6, $t2, $t5
    lw $t7, 4($t6)          # cardsA[i+1]
    sw $t7, 0($t6)          # cardsA[i] = cardsA[i+1]
    addi $t4, $t4, 1
    j shift_a_loop
done_shift_a:
    sw $t1, 0($t0)          # save countA
    
    # Remove pairs for B
    la $a0, cardsB
    la $a1, countB
    jal removePairs
    
skip_b_draw:
    addi $s0, $s0, 1        # round++
    j game_loop
    
a_wins:
    li $v0, 4
    la $a0, msgAWin
    syscall
    j exit
    
b_wins:
    li $v0, 4
    la $a0, msgBWin
    syscall
    j exit
    
draw_game:
    li $v0, 4
    la $a0, msgDraw
    syscall
    
exit:
    li $v0, 10
    syscall

# removePairs function
# $a0 = address of cards array
# $a1 = address of count
removePairs:
    addi $sp, $sp, -20
    sw $ra, 16($sp)
    sw $s0, 12($sp)
    sw $s1, 8($sp)
    sw $s2, 4($sp)
    sw $s3, 0($sp)
    
    move $s0, $a0           # cards address
    move $s1, $a1           # count address
    
remove_outer:
    li $s2, 0               # found = 0
    lw $t0, 0($s1)          # load count
    ble $t0, 1, remove_done # if count <= 1, done
    
    li $t1, 0               # i = 0
remove_loop_i:
    lw $t0, 0($s1)
    addi $t2, $t0, -1
    bge $t1, $t2, check_found  # if i >= count-1
    
    # Check if cards[i] > 0
    sll $t3, $t1, 2
    add $t4, $s0, $t3
    lw $t5, 0($t4)          # cards[i]
    ble $t5, $zero, remove_next_i
    
    addi $t6, $t1, 1        # j = i + 1
remove_loop_j:
    lw $t0, 0($s1)
    bge $t6, $t0, remove_next_i  # if j >= count
    
    # Check if cards[j] > 0 and cards[i] == cards[j]
    sll $t3, $t6, 2
    add $t4, $s0, $t3
    lw $t7, 0($t4)          # cards[j]
    ble $t7, $zero, remove_next_j
    bne $t5, $t7, remove_next_j
    
    # Found pair! Remove cards[j] first
    lw $t0, 0($s1)          # count
    move $t8, $t6           # k = j
remove_shift_j:
    addi $t9, $t0, -1
    bge $t8, $t9, done_remove_j
    sll $t3, $t8, 2
    add $t4, $s0, $t3
    lw $s3, 4($t4)
    sw $s3, 0($t4)
    addi $t8, $t8, 1
    j remove_shift_j
done_remove_j:
    addi $t0, $t0, -1
    sw $t0, 0($s1)
    
    # Remove cards[i]
    lw $t0, 0($s1)
    move $t8, $t1           # k = i
remove_shift_i:
    addi $t9, $t0, -1
    bge $t8, $t9, done_remove_i
    sll $t3, $t8, 2
    add $t4, $s0, $t3
    lw $s3, 4($t4)
    sw $s3, 0($t4)
    addi $t8, $t8, 1
    j remove_shift_i
done_remove_i:
    addi $t0, $t0, -1
    sw $t0, 0($s1)
    
    li $s2, 1               # found = 1
    j check_found
    
remove_next_j:
    addi $t6, $t6, 1
    j remove_loop_j
    
remove_next_i:
    addi $t1, $t1, 1
    j remove_loop_i
    
check_found:
    bnez $s2, remove_outer  # if found, continue outer loop
    
remove_done:
    lw $s3, 0($sp)
    lw $s2, 4($sp)
    lw $s1, 8($sp)
    lw $s0, 12($sp)
    lw $ra, 16($sp)
    addi $sp, $sp, 20
    jr $ra
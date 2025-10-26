.data 
  data: .space 80
  newline: .asciiz "\n"

.text
main:
  li $t0, 0 # answer ($t0) = 0
  li $t1, 0 # num ($t1) = 0
  li $t2, 0 # i ($t2) = 0
  
  main_loop_1:
    bge $t2, 20, main_loop_1_end
    
    sll $t3, $t2, 2 # $t3 = i * 4
    la  $t4, data
    add $t3, $t3, $t4 # $t3 = *data[i]
    sw $zero, 0($t3) #  data[i] = 0
    
    addi $t2, $t2, 1
    
    j main_loop_1
  main_loop_1_end:
  
  li $v0, 5
  syscall
  move $t1, $v0 # scanf("%d", &num)
  
  # TODO
  move $a0, $t1
  la $a1, data
  jal fib
  # TODO
  
  move $a0, $v0
  li $v0, 1
  syscall # print
  
  li $v0, 4
  la $a0, newline
  syscall # \n
  
  li $v0, 10
  syscall
  
fib:
  # $a0: n, $a1: dp
  
  sll $t0, $a0, 2 # t0 = n * 4
  add $t0, $a1, $t0 # t0 = *dp[n]
  lw  $t0, 0($t0)   # t0 = dp[n]
  
  beq $t0, $zero, fib_condition_1_false
  fib_condition_1_true:
    li $v0, 1
    move $a0, $t0
    syscall
    
    li $v0, 4
    la $a0, newline
    syscall # printf("%d\n", dp[n]); 
    
    move $v0, $t0
    jr $ra
    
  fib_condition_1_false:
  
  beq $a0, 1 fib_condition_2_true # n == 1
  beq $a0, 2 fib_condition_2_true # n == 2
  
  j fib_condition_2_false
  fib_condition_2_true:
    sll $t0, $a0, 2 # t0 = n * 4
    add $t0, $a1, $t0 # t0 = *dp[n]
    li $v0, 1
    sw $v0, 0($t0) # dp[n] = 1
    
    jr $ra
  fib_condition_2_false:
  
    addi $sp, $sp, -12
    sw $a0, 0($sp)
    sw $a1, 4($sp)
    sw $ra, 8($sp)
    
    subi $a0, $a0, 1 # a0: n-1
    move $a1, $a1    # a1: dp
    jal fib # fib(n-1, dp)
    
    lw $a0, 0($sp)
    lw $a1, 4($sp)
    lw $ra, 8($sp)
    addi $sp, $sp, 12
  
    move $t0, $v0 # t0 = fib(n-1, dp)
    
    addi $sp, $sp, -16
    sw $a0, 0($sp)
    sw $a1, 4($sp)
    sw $t0, 8($sp)
    sw $ra, 12($sp)
    
    subi $a0, $a0, 2 # a0: n-2
    move $a1, $a1    # a1: dp
    jal fib # fib(n-2, dp)

    lw $a0, 0($sp)
    lw $a1, 4($sp)
    lw $t0, 8($sp)
    lw $ra, 12($sp)
    addi $sp, $sp, 16
    
    move $t1, $v0 # t1 = fib(n-2, dp)
    
    add $t1, $t0, $t1 # $t1 = fib(n-1, dp) + fib(n-2, dp)
    
    sll $t2, $a0, 2 # t2 = n * 4
    add $t2, $t2, $a1 # t2 = * dp[n]
    
    sw $t1, 0($t2) # dp[n] = fib(n-1, dp) + fib(n-2, dp)
    
    and $t2, $t1, 1  # $t2 = dp[n] mod 2
    
    # li $t2, 2
    # div $t1, $t2       # dp[n] / 2
    # mfhi $t2           # $t2 = dp[n] mod 2
    
    beq $t2, 0, fib_condition_3_false # (dp[n] % 2 == 1)
    fib_condition_3_true:
      li $v0, 1
      move $a0, $t1
      syscall
      
      li $v0, 4
      la $a0, newline
      syscall # printf("%d\n", dp[n]);
    
    fib_condition_3_false:
    
    move $v0, $t1
    jr $ra

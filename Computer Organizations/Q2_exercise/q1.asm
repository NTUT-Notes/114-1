.text
main:
  addiu $sp, $sp, -80
  move $t0, $sp # $t0 = char srcStr[80];
  
  addiu $sp, $sp, -80
  move $t1, $sp # $t1 = char decStr[80];
  
  li $v0, 8
  la $a0, ($t0)
  li $a1, 80
  syscall # scanf("%s",srcStr);
  
  addiu $sp, $sp, -8
  sw $t0, 0($sp)
  sw $t1, 4($sp)
  
  move $a0, $t0
  move $a1, $t1
  jal UpperToLower
  
  lw $t0, 0($sp)
  lw $t1, 4($sp)
  addiu $sp, $sp, 8
  
  li $v0, 4
  la $a0, ($t1)
  syscall # printf("%s", desStr)
  
  li $v0, 10
  syscall

UpperToLower:
  # $a0: srcStr[] $a1: desStr[]
  li $t0, 0 # i($t0) = 0
  
  UpperToLower_loop_1:
    add $t1, $a0, $t0 # $t1 = *srcStr[i]
    lb $t1, 0($t1)    # $t1 = srcStr[i]
    beq $t1, $zero, UpperToLower_loop_1_end
    
    blt $t1, 65, UpperToLower_conditional_1_false # False if $t1 < 65
    bgt $t1, 90, UpperToLower_conditional_1_false # False if $t1 > 90
    addiu $t1, $t1, 32
    
    UpperToLower_conditional_1_false:
 
    add $t2, $a1, $t0 # $t2= *decStr[i]
    sb $t1, 0($t2) # decStr[i] = temp
    
    addi $t0, $t0, 1 # i++
    j UpperToLower_loop_1
  UpperToLower_loop_1_end:
  add $t1, $a1, $t0 # $t1= *decStr[i]
  lb $zero, 0($t1) # decStr[i] = \0
  
  jr $ra
.text
main:
  addi $sp, $sp, -400 # $sp = *x
  move $a1, $sp       # $a1 = $sp = *x
  
  li $t0, 1
  
  li $t1, 1           # $t1 (i) = 1
  sw $t1, 0($a1)      # x[0] = 1

  main_loop_1:
    sll $t2, $t1, 2
    add $t2, $a1, $t2
    sw $zero, 0($t2)

    addi $t1, $t1, 1  # i++
    
    li $t3, 100
    blt $t1, $t3, main_loop_1
  
  li $v0, 5
  syscall
  move $a0, $v0  # scanf("%d", &n)
  
  addi $sp, $sp, -8 # Allocate space for n($a0) and x($a1)
  sw   $a0, 0($sp)
  sw   $a1, 4($sp)
  
  jal fact
  
  lw   $a0, 0($sp)
  lw   $a1, 4($sp)
  
  jal print
  
  break 0

fact:
  # $a0: n, $a1: *x

  li $t0, 0 # t0 = 0
  li $t1, 2 # t1 = 2
  
  blt $a0, $t1, fact_Branch_1_True
  j fact_Branch_1_False  
  
fact_Branch_1_True:
  sll $t2, $a0, 2
  add $t2, $t2, $a1

  li $v0, 1 
  
  sw $v0, 0($t2) 
  
  jr $ra
  
fact_Branch_1_False:
  addi $sp, $sp, -32 # Allocate stack frame
  sw $a0, 0($sp)     # Store n
  sw $a1, 4($sp)     # Store *x
  sw $ra, 8($sp)     # Store return address

  subi $a0, $a0, 1
  jal fact           # fact(n - 1, x)
  
  # Restore data from stack 
  lw $a0, 0($sp)     # Restore n
  lw $a1, 4($sp)     # Restore *x
  sw $v0, 12($sp)    # Store return value of fact(n - 1, x)
  
  subi $a0, $a0, 2
  jal fact         # fact(n - 2, x)ww

  lw $a0, 0($sp)  # Restore n
  lw $a1, 4($sp)  # Restore *x
  lw $ra, 8($sp)  # Restore return address
  lw $t0, 12($sp) # Restore return value of fact(n - 1, x)
  
  add $v0, $v0, $t0
  
  sll $t7, $a0, 2
  add $t7, $t7, $a1
  
  sw $v0, 0($t7)
  
  addi $sp, $sp, 32  # Free the stack frame
  
  jr $ra

print:
  # $a0: size, $a1: *x
  li $t0, 0     # i ($t0) = 0
  move $t1, $a0 # $a0 save, do not cobber 
  move $t2, $a1 # $a1 save, do not cobber
   
  print_loop:
    sll $t3, $t0, 2   # t3 = i*4
    add $t3, $a1, $t3 # t3 = x[i]
  
    li $v0, 1
    lw $a0, 0($t3)
    syscall     # printf("%d", x[i])
    
    li $v0, 4
    la $a0, comma
    syscall     # printf(",")
    
    addi $t0, $t0, 1
    blt $t0, $t1, print_loop

  jr $ra

.data
  comma: .asciiz  ","

.text
main:
  addi $sp, $sp, -20
  move $t0, $sp      # !! t0 = array[5]
  
  li $t2, 0 # i ($t2) = 0 (can cobber after loop)
  li $t3, 5 # Comapre value (can cobber after loop)
  
  loop_main_1:
    li $v0, 5
    syscall
    
    sll $t4, $t2, 2
    add $t4, $t4, $t0 # t4 = array[i]
    
    sw $v0, 0($t4) # array[i] = input()
    
    addi $t2, $t2, 1
    blt $t2, $t3, loop_main_1
  
  # End of loop_main_1: $t2, $t3, $t4 is now free3
  
  move $a0, $t0
  li $a1, 5
  
  # [OK] TODO: Process store event
  addi $sp, $sp, -4
  sw $t0, 0($sp)
  
  jal selectionSort
  # [OK] TODO: Process revert event
  lw $t0, 0($sp)
  addi $sp, $sp, 4
  
  li $t2, 0 # i ($t2) = 0 (can cobber after loop)
  li $t3, 5 # Comapre value (can cobber after loop)
  
  loop_main_2:
    sll $t4, $t2, 2
    add $t4, $t4, $t0 # t4 = array[i]
    
    li $v0, 1
    lw $a0, 0($t4)
    syscall
    
    li $v0, 4
    la $a0, newLine
    syscall
        
    addi $t2, $t2, 1
    blt $t2, $t3, loop_main_2
  
  break 0
  
selectionSort:
  # $a0: array[], $a1: n
  
  li $t1, 0        # i ($t1) = 0
  subi $t2, $t1, 1 # t2: Compare value (Can cobber after loop)
  
  selectionSort_loop_1:
    move $t3, $t1 # min_idx ($t3) = i ($t1)
    
    addi $t4, $t1, 1 # j ($t4) = i + 1 (Can cobber after loop)
    selectionSort_loop_2:
      sll $t5, $t4, 2
      add $t5, $t5, $a0
      lw  $t5, 0($t5)   # t5 = array[j] (Can cobber after loop)
      
      sll $t6, $t3, 2
      add $t6, $t6, $a0
      lw  $t6, 0($t6)   # t6 = array[min_idx]) (Can cobber after loop)
      
      blt $t5, $t6, selectionSort_branch_True
      j selectionSort_branch_False
      
      selectionSort_branch_True:
        move $t3, $t4
      
      selectionSort_branch_False:
      
    addi $t4, $t4, 1 # j++
    blt $t4, $a1, selectionSort_loop_2
    
    # End of selectionSort_loop_2: $t4, t5, t6 now free
    sll $t4, $t3, 2
    add $t4, $t4, $a0 # $t4 = *array[min_idx]
    
    lw $t5, 0($t4) # temp ($t5) = array[min_idx]
    
    sll $t6, $t1, 2
    add $t6, $t6, $a0 # $t6 = *array[i]
    
    lw $t7, 0($t6)    # $t7 = array[i]
    sw $t7, 0($t4)    # array[min_idx] = array[i];
    
    sw $t5, 0($t6)
    
    # End of operation: $t4, t5, t6, t7 now free
    
  addi $t1, $t1, 1 # i++
  subi $t4, $a1, 1 # $t4 = n - 1
  blt $t1, $t4, selectionSort_loop_1
  
  jr $ra
  
.data
  newLine: .asciiz "\n"
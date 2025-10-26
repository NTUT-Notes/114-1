.data 
  array :.space 24
  newline: .asciiz "\n"

.text
main:
  li $t0, 0 # i ($t0) = 0
  
  main_loop_1:
    bge $t0, 6, main_loop_1_end # Exit on i >= 6
    
    li $v0, 5
    syscall # scanf("%d", );
    
    la $t1, array
    sll $t2, $t0, 2 # $t2 = $v0 * 4
    add $t1, $t1, $t2 # $t1 = array[i]
    
    sw $v0, 0($t1) # array[i] = $v0
    
    addiu $t0, $t0, 1
    
    j main_loop_1
  main_loop_1_end:
  
  # TODO
  la $a0, array
  li $a1, 6
  jal insertionSortP
  # TODO
  
  li $t0, 0 # i ($t0) = 0
  
  main_loop_2:
    bge $t0, 6, main_loop_2_end # Exit on i >= 6
   
    la $t1, array
    sll $t2, $t0, 2 # $t2 = $v0 * 4
    add $t1, $t1, $t2 # $t1 = array[i]
    
    li $v0, 1
    lw $a0, 0($t1)
    syscall # printf("%d", ...);
    
    li $v0, 4
    la $a0, newline
    syscall
    
    addiu $t0, $t0, 1
    
    j main_loop_2
  main_loop_2_end:
  
  # TODO
  la $a0, array
  li $a1, 6
  jal insertionSort
  # TODO
    
  li $t0, 0 # i ($t0) = 0
  
  main_loop_3:
    bge $t0, 6, main_loop_3_end # Exit on i >= 6
   
    la $t1, array
    sll $t2, $t0, 2 # $t2 = $v0 * 4
    add $t1, $t1, $t2 # $t1 = array[i]
    
    li $v0, 1
    lw $a0, 0($t1)
    syscall # printf("%d", ...);
    
    li $v0, 4
    la $a0, newline
    syscall
    
    addiu $t0, $t0, 1
    
    j main_loop_3
  main_loop_3_end:
  
  li $v0, 10
  syscall
  
insertionSortP:
# $a0: array, $a1: length
  move $t0, $a0
  addiu $t0, $t0, 4 # p ($t0) = array + 1
  
  insertionSortP_loop_1:
    sll $t1, $a1, 2   # $t1 = length * 4
    add $t1, $a0, $t1 # $t1 = array + length
    
    bge $t0, $t1, insertionSortP_loop_1_end
    
    lw $t2, 0($t0) # current ($t2) = *p;
    move $t3, $t0  # int *pos ($t3) = p
    
    insertionSortP_loop_2:
      ble $t3, $a0, insertionSortP_loop_2_end # pos > array
      
      addi $t4, $t3, -4
      lw $t4, 0($t4) # $t4 = *(pos - 1)
      bge $t4, $t2, insertionSortP_loop_2_end # *(pos- 1) < current
      
      sw $t4, 0($t3) # *pos = *(pos - 1);
      addi $t3, $t3, -4 # pos--
      
      j insertionSortP_loop_2
    
    insertionSortP_loop_2_end:
    
    sw $t2, 0($t3) # *pos = current;
    
    addiu $t0, $t0, 4 # p++
    
    j insertionSortP_loop_1
  insertionSortP_loop_1_end:
  
  jr $ra

insertionSort:
# $a0: array, $a1: length
  li $t0, 1 # i ($t0) = 1
  
  insertionSort_loop_1:
    bge $t0, $a1, insertionSort_loop_1_end
    
    sll $t1, $t0, 2 # $t1 = i * 4
    add $t1, $t1, $a0 # $t1 = *array[i]
    lw $t1, 0($t1)    # current $t1 = array[i] 
    
    subi $t2, $t0, 1 # j ($t2) = i-1
    
    insertionSort_loop_2:
      blt $t2, 0, insertionSort_loop_2_end # j >= 0
      
      sll $t3, $t2, 2 # t3 = j*4
      add $t3, $t3, $a0 # $t3 = *array[j]
      lw $t3, 0($t3) # $t3 = array[j]
      
      ble $t3, $t1, insertionSort_loop_2_end
      
      sll $t5, $t2, 2 # t5 = j * 4
      add $t5, $t5, $a0, # t5 = *array[j]
      addi $t5, $t5, 4 # $t5= *array[j+1] 
      
      sw $t3, 0($t5) # array[j + 1] = array[j]
      
      addi $t2, $t2, -1
      j insertionSort_loop_2
      
    insertionSort_loop_2_end:
    
    sll $t3, $t2, 2 # $t3 = j*4
    addi $t3, $t3, 4 # $t3 = j*4 + 4
    add $t3, $a0, $t3 # $t3 = * array[j+1]
    sw $t1, 0($t3) # array[j + 1] = current
    
    addi $t0, $t0, 1 # i++
    j insertionSort_loop_1
  insertionSort_loop_1_end:
  
  jr $ra
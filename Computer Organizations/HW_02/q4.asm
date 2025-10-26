.text

main:
  addi $sp, $sp, -36
  move $t0, $sp # $t0 = int A[3][3]
  
  addi $sp, $sp, -36
  move $t1, $sp # $t1 = int transposeOfA1[3][3];
  
  
  addi $sp, $sp, -36
  move $t2, $sp # $t2 = int transposeOfA2[3][3];
  
  # TODO: Handle memory store
  addi $sp, $sp, -12
  sw   $t0, 0($sp)
  sw   $t1, 4($sp)
  sw   $t2, 8($sp)
  
  move $a0, $t0
  jal inputMatrix
  # TODO: Handle memory restore
  lw   $t0, 0($sp)
  lw   $t1, 4($sp)
  lw   $t2, 8($sp)
  addi $sp, $sp, 12
  
  move $a0, $t0
  move $a1, $t1
  li   $a2, 3
  
  # TODO: Handle memory store
  addi $sp, $sp, -12
  sw   $t0, 0($sp)
  sw   $t1, 4($sp)
  sw   $t2, 8($sp)
  jal transposeMatrixA1
  # TODO: Handle memory restore
  lw   $t0, 0($sp)
  lw   $t1, 4($sp)
  lw   $t2, 8($sp)
  addi $sp, $sp, 12
  
  move $a0, $t0
  move $a1, $t2
  li   $a2, 3
  
  # TODO: Handle memory store
  addi $sp, $sp, -12
  sw   $t0, 0($sp)
  sw   $t1, 4($sp)
  sw   $t2, 8($sp)
  jal transposeMatrixA2
  
  # TODO: Handle memory restore
  lw   $t0, 0($sp)
  lw   $t1, 4($sp)
  lw   $t2, 8($sp)
  addi $sp, $sp, 12
  
  move $a0, $t1
  
  # TODO: Handle memory store
  addi $sp, $sp, -12
  sw   $t0, 0($sp)
  sw   $t1, 4($sp)
  sw   $t2, 8($sp)
  jal outputMatrix
  # TODO: Handle memory restore
  lw   $t0, 0($sp)
  lw   $t1, 4($sp)
  lw   $t2, 8($sp)
  addi $sp, $sp, 12
  
  move $a0, $t2
  
  # TODO: Handle memory store
  addi $sp, $sp, -12
  sw   $t0, 0($sp)
  sw   $t1, 4($sp)
  sw   $t2, 8($sp)
  jal outputMatrix
  # TODO: Handle memory restore
  lw   $t0, 0($sp)
  lw   $t1, 4($sp)
  lw   $t2, 8($sp)
  addi $sp, $sp, 12
  
  break 0
  
transposeMatrixA1:
  # $a0: int A[3][3], $a1: int T[3][3], $a2: int size
  
  li $t0, 0 # i ($t0) = 0
  transposeMatrixA1_loop_1:
    li $t1, 0 # j ($t1) = 0
    transposeMatrixA1_loop_2:
      mul $t2, $t1, $a2 # $t2 = j * 3
      add $t2, $t2, $t0 # $t2 = j * 3 + i
      sll $t2, $t2, 2   # $t2 = ( j * 3 + i ) * 4
      add $t2, $t2, $a1 # $t2 = *T[j][i]
      
      mul $t3, $t0, $a2 # $t3 = i * 3
      add $t3, $t3, $t1 # $t3 = i * 3 + j
      sll $t3, $t3, 2   # $t3 = ( i * 3 + j ) * 4
      add $t3, $t3, $a0 # $t3 = *A[i][j]
      lw  $t3, 0($t3)   # $t3 = A[i][j]
      
      sw $t3, 0($t2)   # T[j][i] = A[i][j];
      
    addi $t1, $t1, 1 # j++
    blt $t1, $a2, transposeMatrixA1_loop_2
    
  addi $t0, $t0, 1 # i++
  blt $t0, $a2, transposeMatrixA1_loop_1
    
  jr $ra

transposeMatrixA2: 
  # $a0: int *B, $a1: int *T, $a2: int size
  move $t0, $a0 # ptrB ($t0) = B
  move $t1, $a1 # ptrT ($t1) = T
  li   $t2, 1   # i ($t2) = 1
  
  transposeMatrixA2_loop_1:
    lw $t3, 0($t0) # $t3 = *ptrB
    sw $t3, 0($t1) #  *ptrT =  *ptrB
    
    blt $t2, $a2, transposeMatrixA2_branch_1_true
    
    j transposeMatrixA2_branch_1_false
    
    transposeMatrixA2_branch_1_true:
      sll $t4, $a2, 2   # $t4 = size * 4
      add $t1, $t1, $t4 # ptrT += size;
      addi $t2, $t2, 1  # i++;
      
      j transposeMatrixA2_branch_1_end
      
    transposeMatrixA2_branch_1_false:
      subi $t4, $a2, 1  # $t4 = size - 1
      mul $t4, $t4, $a2 # $t4 = size * (size - 1)
      subi $t4, $t4, 1  # $t4 = size * (size - 1) - 1
      sll $t4, $t4, 2   # $t4 = ( size * (size - 1) - 1 ) * 4
      
      sub $t1, $t1, $t4 # ptrT -= (size * (size - 1) - 1);
      li $t2, 1         # i ($t2) = 1
      
    transposeMatrixA2_branch_1_end:
  
  mul $t4, $a2, $a2 # $t4 = size * size
  sll $t4, $t4, 2   # $t4 = (size * size) * 4
  add $t4, $t4, $a0 # $t4 = B + (size * size)
  
  addi $t0, $t0, 4  # prtB ++ (jump 4 byte = 1 element)
  blt $t0, $t4, transposeMatrixA2_loop_1
  
  jr $ra

outputMatrix:
  # $a0: A[3][3]
  
  move $t7, $a0 # $t7 = A[3][3]
  li $t0, 3 # Reference value (Cobber after loop)
  
  li $t1, 0 # i ($t1) = 0
  outputMatrix_loop_1:
    li $t2, 0 # j ($t2) = 0
    outputMatrix_loop_2:
      mul $a0, $t1, $t0  # $a0 = i * 3 
      add  $a0, $a0, $t2 # $a0 = i * 3 + j
      sll  $a0, $a0, 2        # $a0 = (i * 3 + j) * 4
      add  $a0, $a0, $t7 # $a0 = *A[i][j]
      
      li $v0, 1
      lw $a0, 0($a0)   # $a0 = A[i][j]
      syscall          # print(A[i][j])
      
      li $v0, 4
      la $a0, space
      syscall          # print(" ")
      
    addi $t2, $t2, 1
    blt $t2, $t0, outputMatrix_loop_2
    # End of outputMatrix_loop_2
  
    li $v0, 4
    la $a0, newline
    syscall          # print("\n")

  addi $t1, $t1, 1
  blt $t1, $t0, outputMatrix_loop_1
  # End of outputMatrix_loop_1
  
  jr $ra
  
inputMatrix:
  # $a0: A[3][3]
  move $t7, $a0
  
  li $t2, 3 # Reference value (Cobber after loop)
  
  li $t0, 0 # i ($t0) = 0 (Cobber after loop)
  inputMatrix_loop_1:
    li $t1, 0 # j ($t1) = 0 (Cobber after loop)
    inputMatrix_loop_2:
      mul $t3, $t0, $t2 # $t3 = i * 3
      add $t3, $t3, $t1 # $t3 = i * 3 + j
      
      li $v0, 5
      syscall
      
      sll $t3, $t3, 2
      add $t3, $a0, $t3 # t3 = A[i][j]
      
      sw $v0, 0($t3)
      
      addi $t1, $t1, 1
    blt $t1, $t2, inputMatrix_loop_2
    # End of inputMatrix_loop_2, $t1, $t3 now free
  
  addi $t0, $t0, 1
  blt $t0, $t2, inputMatrix_loop_1
  
  jr $ra
      
.data
  space: .asciiz " "
  newline: .asciiz "\n"

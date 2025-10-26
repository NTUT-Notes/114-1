.data
  A: .space 36
  flipOfA1: .space 36
  flipOfA2: .space 36
  
  space: .asciiz " "
  newline: .asciiz "\n"
  
.text
main:
  la $t0, A # int* ptrA ($t0) = &A[0][0]
  la $t1, flipOfA2 # int* ptrTA2 ($t1) = &flipOfA2[0][0];
  
  addi $sp, $sp, -8
  sw $t0, 0($sp)
  sw $t1, 4($sp)
  
  la $a0, A
  jal inputMatrix
  
  lw $t0, 0($sp)
  lw $t1, 4($sp)
  addi $sp, $sp, 8

  ###############################
  addi $sp, $sp, -8
  sw $t0, 0($sp)
  sw $t1, 4($sp)
  
  la $a0, A
  la $a1, flipOfA1
  li $a2, 3
  jal flipMatrixA1
  lw $t0, 0($sp)
  lw $t1, 4($sp)
  addi $sp, $sp, 8
  
  la $a0, flipOfA1
  jal outputMatrix
  
  ###############################
  addi $sp, $sp, -8
  sw $t0, 0($sp)
  sw $t1, 4($sp)
  
  la $a0, A
  la $a1, flipOfA2
  li $a2, 3
  jal flipMatrixA2
  lw $t0, 0($sp)
  lw $t1, 4($sp)
  addi $sp, $sp, 8

  la $a0, flipOfA2
  jal outputMatrix
  
  li $v0, 10
  syscall

inputMatrix:
  # $a0: int A[3][3].
  
  li $t0, 0 # i ($t0) = 0
  inputMatrix_loop_1:
    bge $t0, 3, inputMatrix_loop_1_end # i < 3
  
    li $t1, 0 # j ($t1) = 0
    inputMatrix_loop_2:
      bge $t1, 3, inputMatrix_loop_2_end # j < 3
      
      li $v0, 5
      syscall
      
      mul $t2, $t0, 3   # $t2 = i * 3
      add $t2, $t2, $t1 # $t2 = i * 3 + j
      sll $t2, $t2, 2   # $t2 = (i * 3 + j) * 4
      add $t2, $t2, $a0 # $t2 = *A[(i * 3 + j)]
      
      sw $v0, 0($t2)

      addi $t1, $t1, 1 # j++
      j inputMatrix_loop_2
    inputMatrix_loop_2_end:
    
    addi $t0, $t0, 1 # i++
    j inputMatrix_loop_1
  inputMatrix_loop_1_end:
  
  jr $ra

flipMatrixA1:
  # $a0 A[3][3], $a1: T[3][3], $a2: size
  
  li $t0, 0 # i ($t0) = 0
  flipMatrixA1_loop_1:
    bge $t0, $a2, flipMatrixA1_loop_1_end # i < 3
  
    li $t1, 0 # j ($t1) = 0
    flipMatrixA1_loop_2:
      bge $t1, $a2, flipMatrixA1_loop_2_end # j < 3
      
      mul $t2, $t0, 3   # $t2 = i * 3
      add $t2, $t2, $t1 # $t2 = i * 3 + j
      sll $t2, $t2, 2   # $t2 = (i * 3 + j) * 4
      add $t2, $t2, $a1 # $t2 = *T[i][j]
      
      mul $t3, $t0, 3   # $t3 = i * 3
      add $t3, $t3, $a2 # $t3 = i * 3 + size
      subi $t3, $t3, 1  # $t3 = i * 3 + size - 1
      sub $t3, $t3, $t1 # $t3 = i * 3 + size - 1 - j
      sll $t3, $t3, 2   # $t3 = ( i * 3 + size - 1 - j) * 4
      add $t3, $t3, $a0 # $t3 = * A[i][size-1-j]
      lw $t3, 0($t3)    # $t3 = A[i][size-1-j]
      
      sw $t3, 0($t2)    # T[i][j] = A[i][size-1-j]

      addi $t1, $t1, 1 # j++
      j flipMatrixA1_loop_2
    flipMatrixA1_loop_2_end:
    
    addi $t0, $t0, 1 # i++
    j flipMatrixA1_loop_1
  flipMatrixA1_loop_1_end:

  jr $ra

flipMatrixA2:
  # $a0: int *B, $a1: int *T, $a2: int size
  move $t0, $a0 # *ptrB ($t0) = B
  subi $t1, $a2, 1 # $t1 = size -1 
  sll $t1, $t1, 2  # $t1 = $t1 * 4
  add $t1, $t1, $a1 # *ptrT ($t1) = T
  li $t2, 0     # i     ($t2) = 0

  flipMatrixA2_loop_1:
    mul $t3, $a2, $a2 # $t3 = size * size
    sll $t3, $t3, 2   # $t3 = size * size * 4
    add $t3, $t3, $a0 # $t3 = B + size * size
    bge $t0, $t3, flipMatrixA2_loop_1_end # ptrB < B + size * size
    
    lw $t3, 0($t0) # $t3 = *ptrB
    sw $t3, 0($t1) # *ptrT = $t3
    
    blt $t2, $a2, flipMatrixA2_branch_true # i<size
    j flipMatrixA2_branch_false
    
    flipMatrixA2_branch_true:
      subi $t1, $t1, 4 # ptrT -= 1
      addi $t2, $t2, 1 # i++
      
      beq $t2, $a2, flipMatrixA2_branch_2_true # i==size
      j flipMatrixA2_branch_2_false
      flipMatrixA2_branch_2_true:
        sll $t4, $a2, 1 # size * 2
        sll $t4, $t4, 2 # size * 2 * 4
        add $t1, $t4, $t1 # ptrT += 2*size
        
        li $t2, 0 # i = 0
      
      flipMatrixA2_branch_2_false:
    flipMatrixA2_branch_false:
    
    addi $t0, $t0, 4 # ptrB ++
    j flipMatrixA2_loop_1
  flipMatrixA2_loop_1_end:
  
  jr $ra
  
outputMatrix:
  # $a0: A[3][3]
  move $t7, $a0
  
  li $t0, 0 # i ($t0) = 0
  outputMatrix_loop_1:
    bge $t0, 3, outputMatrix_loop_1_end # i < 3
  
    li $t1, 0 # j ($t1) = 0
    outputMatrix_loop_2:
      bge $t1, 3, outputMatrix_loop_2_end # j < 3
      
      mul $t2, $t0, 3   # $t2 = i * 3
      add $t2, $t2, $t1 # $t2 = i * 3 + j
      sll $t2, $t2, 2   # $t2 = (i * 3 + j) * 4
      add $t2, $t2, $t7 # $t2 = *A[(i * 3 + j)]
      lw $t2, 0($t2)    # $t2 = A[i][j]
      
      li $v0, 1
      move $a0, $t2
      syscall # print num
      
      li $v0, 4
      la $a0, space
      syscall # print space

      addi $t1, $t1, 1 # j++
      j outputMatrix_loop_2
    outputMatrix_loop_2_end:
    
      li $v0, 4
      la $a0, newline
      syscall # print space
    
    addi $t0, $t0, 1 # i++
    j outputMatrix_loop_1
  outputMatrix_loop_1_end:
  
  jr $ra
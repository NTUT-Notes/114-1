.text
main:
  jal printNewline
  
  li $t1, -1

  li $v0, 5
  syscall
  move $a0, $v0 # scanf("%d", $s0) Height
  
  beq $a0, $t1, exit
  
  li $v0, 5
  syscall
  move $a1, $v0 # scanf("%d", $s0) Weight
  
  jal calculateBMI
  move $t7, $v0 # t0 = calculateBMI(Height, Weight)
  
  # t0: use for the referance number
  li   $t0, 18
  slt  $t1, $t7, $t0 # If  BMI < 18 -> BMI <= 17
  bne  $t1, $0, loadUnderweight
  
  li   $t0, 24
  sgt  $t1, $t7, $t0 # If BMI > 24 -> BMD >= 25
  bne  $t1, $0, loadOverweight
  
  move $a0, $t7
  li $v0, 1
  syscall
  
  j main

loadOverweight:
  la $a0, overweight
  j print

loadUnderweight:
  la $a0, underweight
  j print

print:
  li $v0, 4
  syscall
  j main

exit:
  li $v0, 10
  syscall
  
calculateBMI:
  # $a0: Height, $a1: Weight
  
  # $t0 = $a1 * 10000
  li   $t0, 10000
  mult $a1, $t0
  mflo $t0
  
  # $t1 = $a0 * $a0
  mult $a0, $a0
  mflo $t1
   
   # v0 = $v0 / $t1
  div $t0, $t1
  mflo $v0

  jr $ra
  
printNewline:
  la $a0, newline
  li $v0, 4
  syscall
  
  jr $ra

.data
  underweight: 
    .asciiz "underweight"
  overweight:
    .asciiz "overweight"
  newline:
    .asciiz "\n"

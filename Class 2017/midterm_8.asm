.data
inp_req_1:	.asciiz	"Nhap so luong sinh vien: "
inp_req_2:	.asciiz "Nhap ten sinh vien: "
inp_req_3:	.asciiz "Nhap diem sinh vien: "
out_req_1:	.asciiz "Sinh vien: "
out_req_2:	.asciiz "Diem: "
out_req_3:	.asciiz "---------------\n"
new_line:	.asciiz "\n"
msg:		.asciiz "Bad input\n"
num_student:	.word 0
names:		.word 0
marks:		.word 0
maxStr:		.word 20
maxInt:		.word 10

.text
main:
	jal	getInput				# Call getInput Function
	jal	sortArray				# Call sort Function
	j	finish					# Print result and exit
	
getInput:
	add	$t9, $zero, $ra				# Store return address
	la	$a0, inp_req_1				# 
	jal	printString				# Call print syscall
	jal	read_int				# Call read syscall
	sw	$v0, num_student			# Store number of student
	
	jal	malloc					# Allocate dynamic memory
	sw	$v0, marks				# Store that address in marks
	jal	malloc					# Allocate dynamic memory
	sw	$v0, names				# Store that address in names

	add	$t0, $0, $0				# Init cnt = 0
	lw	$t1, num_student			# Load address of number of sutdent
	lw	$s0, names				# Load address of names
	lw	$s1, marks				# Load address of marks
loop:
	la	$a0, inp_req_2
	jal	printString				# Call print syscall
	jal	malloc_str				# Call malloc for string
	sw	$v0, 0($s0)				# Store in names[i]
	add	$a0, $v0, $0				# Argument 1
	lw	$a1, maxStr				# Argument 2
	jal	read_str				# Call read syscall
	
	la	$a0, inp_req_3				#
	jal	printString				# Call print syscall
	jal	read_int				# Call read syscall
	sw	$v0, 0($s1)				# Store in marks[j]
	
	add	$s0, $s0, 4				# i = i+1
	add	$s1, $s1, 4				# j = j+1
	add	$t0, $t0, 1				# cnt = cnt + 1
	bne	$t0, $t1, loop				# if cnt == num of student : break
	
	jr	$t9
	
sortArray:
	lw	$t0, marks				# Load address of marks
	lw	$t1, num_student			# Load number of student
	sub	$t1, $t1, 1				# n = n-1
	li	$t3, 4					# Get size of word
	mult	$t1, $t3				# Calculate total size 
	mflo	$t1					#
	add	$t0, $t0, $t1				# Get final address
outterLoop:             
	add	$t1, $0, $0				# Init i = 0
	lw	$a0, marks				# Load address of marks
	lw	$a1, names				# Load address of names
innerLoop:
	lw	$t2, 0($a0)				# get marks [i]
	lw	$t3, 4($a0)				# get marks [i+1]
	lw	$t6, 0($a1)				# get names [i]
	lw	$t7, 4($a1)				# get names [i+1]
	slt	$t5, $t2, $t3				# if marks[i] < marks[i+1]: swap
	beq	$t5, $0, continue			#
	add	$t1, $0, 1				#
	sw	$t2, 4($a0)				#
	sw	$t3, 0($a0)				#
	sw	$t6, 4($a1)				#
	sw	$t7, 0($a1)				#
continue:
	addi	$a0, $a0, 4				# i = i+1
	addi	$a1, $a1, 4				#
	bne	$a0, $t0, innerLoop			#
	bne	$t1, $0, outterLoop			#
	jr	$ra

printString:
	li	$v0, 4
	syscall
	jr	$ra

printInt:
	li	$v0, 1
	syscall
	jr	$ra

read_int:
	li	$v0, 5
	syscall
	lw	$t7, maxInt
	bgt	$v0, $t7, re_input			# If get a bad input -> re enter it
	jr	$ra
re_input:
	la	$a0, msg				
   	li	$v0, 4
   	syscall
   	j	read_int

read_str:
	li	$v0, 8
	syscall
	jr	$ra

malloc:
	lw	$v0, num_student
	li	$t0, 4
	mult	$v0, $t0
	mflo	$a0
	li	$v0, 9
	syscall
	jr	$ra

malloc_str:
	lw	$a0, maxStr
	li	$v0, 9
	syscall
	jr	$ra

finish:
	lw	$s0, names
	lw	$s1, marks
	lw	$t9, num_student
	add	$t0, $0, $0
loop2:
	la	$a0, out_req_3
	jal	printString
	la	$a0, out_req_1
	jal	printString
	lw	$a0, 0($s0)
	jal	printString
	la	$a0, out_req_2
	jal	printString
	lw	$a0, 0($s1)
	jal	printInt
	la	$a0, new_line
	jal	printString
	
	add	$s0, $s0, 4
	add	$s1, $s1, 4
	add	$t0, $t0, 1
	bne	$t0, $t9, loop2

 .ktext 0x80000180						# Exception handler
	move	$k0, $v0
	move	$k1, $a0
	la	$a0, msg
   	li	$v0, 4
   	syscall
   
   	add	$t8, $t8, 1
	move	$v0, $k0
	move	$a0, $k1
	mfc0	$k0, $14
	addi	$k0, $k0, 4
	mtc0	$k0, $14
	eret

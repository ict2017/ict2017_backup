# $t0 dia chi cua array
# $t1 so luong phan tu cua array
# $t2 bien i de chay vong lap
.data
space:	.asciiz " "		# String dau cach.
line:	.asciiz	"\n"		# String xuong dong.
array:	.word	0 : 100		# array chua cac phan tu can sort.
size:	.word	5		# so phan tu cua array.
Input_N:	.asciiz	"Input number of values to be sorted (0 < N < 100): "
Input_value:	.asciiz	"Input value #"
Unsorted:	.asciiz "Unsorted:"
Sort_done:	.asciiz "Sorted:"

.text
main:

	li	$v0, 4			# 4 = print_string syscall.
	la	$a0, Input_N		# luu dia chi cua String Input_N vao $a0
	syscall				# chay system call.
read_N:
	li	$v0, 5			# 5 = read_int syscall.
	syscall				# chay system call.
	la	$t0, size		# luu dia chi cua size vao $t0.
	sw	$v0, 0($t0)		# ghi gia tri tra ve o $v0 vao size.
	
read_values:
	la	$t0, array		# luu array vao $t0.
	lw	$t1, size		# luu size vao $t1.
	li	$t2, 0			# i = 0
read_values_loop:
	bge	$t2, $t1, read_values_end	# while ($t2 < $t1)
	li	$v0, 4			# 4 = print_string syscall.
	la	$a0, Input_value	# luu dia chi cua String Input_value vao thanh ghi $a0.
	syscall				# chay system call.
	li	$v0, 1			# 1 = print_int syscall.
	addi	$a0, $t2, 1		# luu (i + 1) vao thanh ghi $a0.
	syscall				# chay system call.
	li	$v0, 4			# 4 = print_string syscall.
	la	$a0, space		# luu dia chi space vao $a0.
	syscall				# chay system call.

	li	$v0, 5			# 5 = read_int syscall.
	syscall				# chay system call.
	sw	$v0, 0($t0)		# ghi gia tri tra ve vao array.

	addi	$t0, $t0, 4		# tang con tro len 4. tro den phan tu tiep theo cua array
	addi	$t2, $t2, 1		# tang i them 1(i++).
	j	read_values_loop	# nhay ve read_values_loop, tiep tuc vong lap.
read_values_end:
	li	$v0, 4			# 4 = print_string syscall.
	la	$a0, Unsorted		# luu dia chi cua String Unsorted vao $a0
	syscall				# chay system call.
	li	$v0, 4			# 4 = print_string syscall.
	la	$a0, line		# luu dia chi String line vao $a0.
	syscall				# chay system call.
	jal	print			# nhay toi print (chay function print).

	
insertion_sort:
	la	$t0, array		# luu array vao $t0.
	lw	$t1, size		# luu size cua array vao $t1.
	li	$t2, 1			# i = 1.
outer_loop:
	la	$t0, array		# luu array vao $t0.
	bge	$t2, $t1, outer_loop_end	# while ($t2 < $t1).
	move	$t3, $t2		# copy $t2 vao $t3.
inner_loop:
	la	$t0, array		# luu array vao $t0.
	mul	$t5, $t3, 4		# Nhan $t3 voi 4, ghi vao thanh ghi $t5
	add	$t0, $t0, $t5		# cong dia chi cua array voi $5 de tro den phan tu array[$t3]
	ble	$t3, $zero, inner_loop_end	# while (t3 > 0).
	lw	$t7, 0($t0)		# luu array[$t3] vao $t7.
	lw	$t6, -4($t0)		# luu array[$t3 - 1] vao $t6.
	bge	$t7, $t6, inner_loop_end	# while (array[$t3] < array[$t3 - 1]).
	lw	$t4, 0($t0)		# luu array[$t3] vao $t4.
	sw	$t6, 0($t0)		# ghi $t6 vao array[$t3].
	sw	$t4, -4($t0)		# ghi $t4 vao array[$t3 - 1]
	subi	$t3, $t3, 1		# $t3--
	j	inner_loop		# nhay ve inner_loop.
inner_loop_end:
	addi	$t2, $t2, 1		# tang i them 1 (i++).
	j	outer_loop		# nhay ve outer_loop.
outer_loop_end:
	li	$v0, 4			# 4 = print_string syscall.
	la	$a0, Sort_done	# luu dia chi String Sort_done vao $a0.
	syscall				# chay system call.
	li	$v0, 4			# 4 = print_string syscall.
	la	$a0, line		# luu dia chi String line vao $a0.
	syscall				# chay system call.
	jal	print			# nhay toi print (chay function print).
exit:
	li	$v0, 10			# 10 = exit syscall.
	syscall				# chay system call.


print:
print_loop_prep:
	la	$t0, array		# luu array vao $t0.
	lw	$t1, size		# luu size vao $t1.
	li	$t2, 0			# i = 0
print_loop:
	bge	$t2, $t1, print_end	# while ($t2 < $t1)
	li	$v0, 1			# 1 = print_int syscall.
	lw	$a0, 0($t0)		# luu array[$t2] vao $a0
	syscall				# chay system call.
	li	$v0, 4			# 4 = print_string syscall.
	la	$a0, space		# luu dia chi space vao $a0.
	syscall				# chay system call.
	addi	$t0, $t0, 4		# tang con tro len 4, tro den phan tu tiep theo cua array
	addi	$t2, $t2, 1		# tang i them 1.(i++)
	j	print_loop		# nhay ve print_loop, tiep tuc vong lap 
print_end:
	li	$v0, 4			# 4 = print_string syscall.
	la	$a0, line		# luu dia chi String line vao $a0.
	syscall				# chay system call.
	jr	$ra			# tro lai vi tri co lenh jal

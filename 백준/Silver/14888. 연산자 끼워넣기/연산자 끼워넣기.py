import sys
input = sys.stdin.readline
from itertools import permutations

n = int(input())
nums = list(map(int, input().split()))
oper = list(map(int, input().split()))

ops = []  # 연산자 박스
for i in range(4):
    if i == 0:
        for j in range(oper[0]):
            ops.append('+')
    elif i == 1:
        for j in range(oper[1]):
            ops.append('-')
    elif i == 2:
        for j in range(oper[2]):
            ops.append('×')
    elif i == 3:
        for j in range(oper[3]):
            ops.append('÷')


max_res = -1234567899
min_res = 1234567899

for perm in permutations(ops):
    #print('perm = ' , perm)
    res = nums[0]
    idx = 1
    for i in range(n-1):  # 연산자 개수만큼
        if perm[i] == '+':
            res += nums[idx]
        elif perm[i] == '-':
            res -= nums[idx]
        elif perm[i] == '×':
            res *= nums[idx]
        elif perm[i] == '÷' and res < 0:
            res = -((-res) // nums[idx])
        elif perm[i] == '÷' and res >= 0:
            res = res // nums[idx]
        idx += 1
    if max_res < res:
        max_res = res
    if min_res > res:
        min_res = res

print(max_res)
print(min_res)


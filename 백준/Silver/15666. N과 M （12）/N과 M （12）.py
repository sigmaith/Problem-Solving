import sys
input = sys.stdin.readline
from itertools import product

n, m = map(int, input().split())
arr = list(map(int, input().split()))
s = set()  # 집합은 immutable tuple 요소만 추가 가능
for pro in product(arr, repeat=m):  # tuple
    #tmp = sorted(pro)  # 고른 수열은 비내림차순이어야 하므로 sorted로 오름차순 list반환
    #tmp = tuple(tmp)  # 집합에 추가해야하므로 tuple로 변환
    flag = 0
    for i in range(1, len(pro)):
        if pro[i] < pro[i-1]:
            flag = 1
            break
    if not flag:
        s.add(pro)

s = sorted(s)  # tuple 집합 -> tuple 리스트
for element in s:
    for ele in element:
        print(ele, end=' ')
    print('')


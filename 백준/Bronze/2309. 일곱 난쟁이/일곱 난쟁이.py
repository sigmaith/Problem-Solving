import sys
input = sys.stdin.readline
from itertools import combinations

ninedwarfs = [int(input()) for _ in range(9)]

for comb in combinations(ninedwarfs, 7):
    if sum(comb) == 100:
        print(*sorted(comb), sep='\n')
        break

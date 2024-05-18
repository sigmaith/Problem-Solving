# 1976
import sys
input = sys.stdin.readline


def find_set(i):
    if trees[i][0] != i:
        trees[i][0] = find_set(trees[i][0])
    return trees[i][0]


def union(anc1, anc2):
    if trees[anc1][1] > trees[anc2][1]:
        trees[anc2][0] = anc1
    else:
        if trees[anc1][1] == trees[anc2][1]:
            trees[anc2][1] += 1
        trees[anc1][0] = anc2


n = int(input())  # num of city
m = int(input())  # num of travel city

korea_map = [list(map(int, input().split())) for _ in range(n)]
travel = list(map(int, input().split()))

trees = [[i, 0] for i in range(n)]  # ancestor, rank

for i in range(n):
    for j in range(i + 1, n):
        if korea_map[i][j]:
            x_anc = find_set(i)
            y_anc = find_set(j)
            if x_anc != y_anc:
                union(x_anc, y_anc)  # union

# print(trees) ## 디버깅

x_anc = find_set(travel[0] - 1)
flag = 0
for i in range(1, len(travel)):
    y_anc = find_set(travel[i] - 1)
    if x_anc != y_anc:
        flag = 1
        print("NO")
        break

if not flag:
    print("YES")

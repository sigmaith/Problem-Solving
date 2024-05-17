import sys
from math import sqrt
input = sys.stdin.readline

def get_dist(i, j):
    x2 = stars[i][0] - stars[j][0]
    x2 *= x2
    y2 = stars[i][1] - stars[j][1]
    y2 *= y2
    return round(sqrt(x2+y2), 2)

def find_set(x):
    if x != trees[x][0]:
        trees[x][0] = find_set(trees[x][0])
    return trees[x][0]

def union(x_anc, y_anc):
    if trees[x_anc][1] > trees[y_anc][1]:
        trees[y_anc][0] = x_anc
    else:
        if trees[x_anc][1] == trees[y_anc][1]:
            trees[y_anc][1] += 1
        trees[x_anc][0] = y_anc

n = int(input())  # num of stars
stars = [list(map(float, input().split())) for _ in range(n)]
# print(stars)

dist = []
for i in range(n):
    for j in range(i+1, n):
        dist.append([i, j, get_dist(i, j)])

dist.sort(key=lambda x: x[2])
# print(dist)

trees = [[i, 0] for i in range(n)]  # ancestor and rank
cnt = 0
min_cost = 0
for ele in dist:
    x, y, d = ele[0], ele[1], ele[2]
    x_anc = find_set(x)
    y_anc = find_set(y)
    if x_anc != y_anc:
        union(x_anc, y_anc)
        cnt += 1
        min_cost += d
    if cnt == n-1:
        break

print(min_cost)



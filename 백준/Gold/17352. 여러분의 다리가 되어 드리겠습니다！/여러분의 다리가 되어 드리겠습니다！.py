import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**5)

n = int(input())
p = [0] * (n+1)


def make_set(x):
    p[x] = x


def find_set(idx):
    if idx != p[idx]:
        p[idx] = find_set(p[idx])
    return p[idx]


def union(x, y):
    p[find_set(y)] = find_set(x)

for i in range(1, n+1):
    make_set(i)

for i in range(n - 2):
    v1, v2 = map(int, input().split())
    if find_set(v1) != find_set(v2):  # 조상이 다른지 확인하기
        union(v1, v2)

for j in range(2, n+1):
        if find_set(1) != find_set(j):
            print(1, j)
            break

import sys
input = sys.stdin.readline

def find_set(x):
    if x != trees[x][0]:
        trees[x][0] = find_set(trees[x][0])
    return trees[x][0]

def union(x, y):
    if trees[x][1] > trees[y][1]:
        trees[y][0] = x
    else:
        trees[x][0] = y
        if trees[x][1] == trees[y][1]:
            trees[y][1] += 1


n = int(input())  # 컴퓨터 개수
m = int(input())  # 연결할 수 있는 선의 수

Edge = [list(map(int, input().split())) for _ in range(m)]
Edge.sort(key=lambda x: x[2])
# print(Edge)

trees = [[i, 0] for i in range(n+1)] # 조상과 계층

cnt = 0  # n -1 개가 되면 break. 모든 원소가 연결될 때 n-1개의 간선만 필요
final_cost = 0
for ele in Edge:
    x, y, cost = ele[0], ele[1], ele[2]
    x_anc = find_set(x)
    y_anc = find_set(y)
    if x_anc != y_anc:
        union(x_anc, y_anc)
        cnt += 1
        final_cost += cost
    if cnt == n-1:
        break

print(final_cost)



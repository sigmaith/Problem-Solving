import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**5)

n = int(input())
tree = [[] for _ in range(n+1)]
visited = [0] * (n+1)
answer = [0] * (n+1)

for _ in range(n-1):
    x, y = map(int, input().split())
    tree[x].append(y)
    tree[y].append(x)

def dfs(present):
    if not visited[present]:
        visited[present] = 1
        for connect in tree[present]:
            if not visited[connect]:
                answer[connect] = present
                dfs(connect)

dfs(1)
for i in range(2, n+1):
    print(answer[i])


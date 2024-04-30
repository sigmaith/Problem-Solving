v = int(input())
e = int(input())

adj_list = [[] for _ in range(v + 1)]
for _ in range(e):
    start, end = map(int, input().split())
    adj_list[start].append(end)
    adj_list[end].append(start)

stack = [1]
visited = []

count = 0

while stack:
    current = stack.pop()
    if current not in visited:
        visited.append(current)
        count += 1

    for destination in adj_list[current]:
        if destination not in visited:
            stack.append(destination)

print(count-1)
import sys
input = sys.stdin.readline
import heapq

n = int(input())
lessons = []
for _ in range(n):
    s, t = map(int, input().split())
    lessons.append([s, t])

lessons = sorted(lessons, key=lambda x: x[0])
classroom = []
heapq.heappush(classroom, lessons[0][1])  # 끝나는 시간

for idx in range(1, len(lessons)):
    if classroom[0] <= lessons[idx][0]:
        heapq.heappop(classroom)
        heapq.heappush(classroom, lessons[idx][1])
    else:
        heapq.heappush(classroom, lessons[idx][1])  # 끝나는 시간
    idx += 1

print(len(classroom))



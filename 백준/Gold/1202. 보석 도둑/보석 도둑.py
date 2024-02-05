import heapq
import sys
input = sys.stdin.readline

n, k = map(int, input().rstrip().split())
jewels = []
bags = []

for _ in range(n):
    weight, price = map(int, input().rstrip().split())
    heapq.heappush(jewels, [weight, price])

for _ in range(k):
    weight = int(input().rstrip())
    heapq.heappush(bags, weight)

total = 0
heap = []
while bags:
    bag = heapq.heappop(bags)
    while jewels and bag >= jewels[0][0]:  # 가방보다 무게 작은 보석들 추려냄
        heapq.heappush(heap, -jewels[0][1])  # 그것들을 heapify
        heapq.heappop(jewels)
    if heap:
        total +=  -1 * heapq.heappop(heap)
    elif not jewels: break

print(total)
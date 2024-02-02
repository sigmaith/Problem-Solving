import sys
from itertools import combinations

input = sys.stdin.readline

n, m = map(int, input().split())
city = [list(map(int, input().split())) for _ in range(n)]

house, chick = [], []
for i in range(n):
    for j in range(n):
        if city[i][j] == 1:
            house.append((i, j))
        elif city[i][j] == 2:
            chick.append((i, j))

# 각 치킨집과 집 사이의 맨해튼 거리 미리 계산
dist_c = [[abs(ci[0] - hi[0]) + abs(ci[1] - hi[1]) for hi in house] for ci in chick]

min_dist_sum = float('inf')
# 가능한 모든 치킨집 조합에 대해 반복
for chicks in combinations(range(len(chick)), m):
    # 현재 조합에서 각 집의 최소 치킨 거리를 저장할 리스트
    dist_sum = 0
    for hi in range(len(house)):
        # 현재 집의 모든 가능한 치킨집과의 거리 중 최소값 찾기
        dist_sum += min(dist_c[ci][hi] for ci in chicks)
    # 모든 집의 치킨 거리의 합이 현재까지의 최소값보다 작으면 업데이트
    min_dist_sum = min(min_dist_sum, dist_sum)

print(min_dist_sum)

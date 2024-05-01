import sys
input = sys.stdin.readline

n, m = map(int, input().split())
trees = list(map(int, input().split()))

l, r = 0, max(trees)
mid = (l+r) // 2
result_cut = mid

paper = 0
for i in range(n):
    if trees[i] > mid:
        paper += trees[i] - mid
result = paper

while True:
    if result == m or l > r:
        break
    elif paper < m:
        r = mid - 1
        mid = (l+r) // 2
        paper = 0
        for i in range(n):
            if trees[i] > mid:
                paper += trees[i] - mid
    elif paper > m:
        l = mid + 1
        mid = (l+r) // 2
        paper = 0
        for i in range(n):
            if trees[i] > mid:
                paper += trees[i] - mid
    if m <= paper < result or result < m:
        result = paper
        result_cut = mid

print(result_cut)

# 나무 절단기 예측 횟수 * 100만개 나무 선회 (O(N))
# log(10억) = 30 정도
# 30 * 100만 = 3000만 (ok!)

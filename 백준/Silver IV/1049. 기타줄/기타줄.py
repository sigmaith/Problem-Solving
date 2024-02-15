import sys
input = sys.stdin.readline

n, m = map(int, input().split())  # al least n, m brands
#package, only one
guitar = [list(map(int, input().split())) for _ in range(m)]
for i in range(m):
    if guitar[i][1] * 6 < guitar[i][0]:
        guitar[i][0] = guitar[i][1] * 6
guitar.sort()
#print(guitar)
pack = guitar[0][0]
guitar = sorted(guitar, key=lambda x: x[1])
one = guitar[0][1]
#print(guitar)

cost = 0
while n > 0:
    if n >= 6:
        cost += pack
        n -= 6
    elif n < 6:
        if n * one > pack:
            cost += pack
            n -= 6
        else:
            cost += n * one
            n -= n

print(cost)
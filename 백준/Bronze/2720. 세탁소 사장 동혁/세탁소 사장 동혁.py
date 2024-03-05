import sys
input = sys.stdin.readline

t = int(input())
quat = 25
dime = 10
nick = 5
penn = 1

for i in range(t):
    q = 0
    d = 0
    n = 0
    p = 0
    x = int(input())
    #print(x)
    while x >= quat:
        x -= quat
        q += 1
    while x >= dime:
        x -= dime
        d += 1
    while x >= nick:
        x -= nick
        n += 1
    while x >= penn:
        x -= penn
        p += 1
    #print(x)
    print(q, d, n, p)


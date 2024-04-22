t = int(input())
for _ in range(t):
    h, w, n = map(int, input().split())
    if (n%h==0):
        res = h*100+(n//h)
    else:
        res = (n%h)*100+ (n//h+1)
    print(res)
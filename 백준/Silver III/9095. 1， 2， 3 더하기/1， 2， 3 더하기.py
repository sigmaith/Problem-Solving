n = int(input())
a = [0, 1, 2, 4]

i = len(a)-1
for _ in range(n):
    x = int(input())
    if i < x:
        while i < x:
            a.append(a[i] + a[i-1] + a[i-2])
            i += 1
    print(a[x])

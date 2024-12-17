n, m = map(int, input().split())
print("0 1\n1 2")
for i in range(n - m - 1):
    print(i + 2, i + 3)
for i in range(m - 2):
    print(1, i + n - m + 2)
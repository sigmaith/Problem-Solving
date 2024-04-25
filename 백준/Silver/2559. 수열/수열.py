n, k = map(int, input().split())
l = list(map(int, input().split()))


max_value = sum(l[0:k])
summ = max_value

for i in range(k, len(l)):
    summation = summ + l[i] - l[i-k]
    if summation > max_value:
        max_value = summation
    summ = summation
print(max_value)

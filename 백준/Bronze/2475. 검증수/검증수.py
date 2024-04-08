a, b, c, d, e = map(int, input().split())
res = a*a + b*b + c*c + d*d + e*e
res = res % 10
print(res)
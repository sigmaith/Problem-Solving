t = int(input())
a = 300
b = 60
c = 10
num_a = 0
num_b = 0
num_c = 0

num = 0
if t >= a:
    num_a = t//a
    t = t % a
if t >= b:
    num_b = t//b
    t = t % b
if t >= c:
    num_c = t//c
    t = t % c
if t == 0:
    print(num_a, num_b, num_c)
else:
    print(-1)
import sys
input = sys.stdin.readline

hamburger = []
juice = []

for _ in range(3):
    x = int(input())
    hamburger.append(x)

for _ in range(2):
    y = int(input())
    juice.append(y)

min_cost = 100000000 

for i in range(3):
    for j in range(2):
        if min_cost > hamburger[i]+juice[j]-50:
            min_cost = hamburger[i]+juice[j]-50
            
print(min_cost)
    

s = set()
for _ in range(10):
    x = int(input())
    s.add(x%42)
    
print(len(s))
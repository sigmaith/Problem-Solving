s = input()
for num in range(ord('a'), ord('z')+1):
    l = 0
    for c in s:
        if c == chr(num):
            l += 1
    print(l, end=' ')
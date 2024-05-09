meter = int(input())
dist = 0
minute = 0
while True:
    if dist >= meter:
        print(minute)
        break
    else:
        dist += 5
        minute += 1
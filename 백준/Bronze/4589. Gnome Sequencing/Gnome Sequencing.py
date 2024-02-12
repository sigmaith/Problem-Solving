import sys
input = sys.stdin.readline

n = int(input())
print("Gnomes:")
for _ in range(n):
    gnomes = list(map(int, input().split()))
    gnomes_copy = gnomes.copy()
    if gnomes[0] < gnomes[1]:
        gnomes_copy.sort()
        if gnomes_copy == gnomes:
            print("Ordered")
        else:
            print("Unordered")
    elif gnomes[0] > gnomes[1]:
        gnomes_copy.sort(reverse=True)
        if gnomes_copy == gnomes:
            print("Ordered")
        else:
            print("Unordered")

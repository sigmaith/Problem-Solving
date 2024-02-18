array = [int(input()) for _ in range(9)]
array_s = sorted(array)
print(array_s[-1])
for i in range(9):
    if array[i] == array_s[-1]:
        print(i+1)
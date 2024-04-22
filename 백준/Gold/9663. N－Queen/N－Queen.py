def nqueens(row):
    if row == n:
        return 1

    result = 0
    for c in range(n):
        if c in col or row + c in pos or row - c in neg:
            continue

        else:
            col.append(c)
            pos.append(row + c)
            neg.append(row - c)
            result += nqueens(row + 1)
            col.pop()
            pos.pop()
            neg.pop()
    return result


col = []
pos = []
neg = []
n = int(input())
print(nqueens(0))

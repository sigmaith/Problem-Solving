import sys
input = sys.stdin.readline

n = int(input())
A = list(map(int, input().split()))
dp_table = [[[0] * 2000 for _ in range(n)] for _ in range(3)]

def findmax(idx, num, ch):  # idx: traverse A, num: latest element of bitonic sequence, ch: 0(증가) 1(감소) 2(끝)
    if idx == n:
        return 0

    if dp_table[ch][idx][num] == 0:
        result_1 = result_2 = result_3 = 0
        if ch == 0:
            if A[idx] > num:
                result_1 = max(0 + findmax(idx+1, num, ch),1+findmax(idx+1, A[idx], ch))
            elif A[idx] == num:
                result_2 = 0 + findmax(idx+1, num,ch)
            elif A[idx] < num:
                result_3 = max(0 + findmax(idx+1, num, 0), 1+findmax(idx+1, A[idx], 1))
        elif ch == 1:
            if A[idx] < num:
                result_1 = max(0 + findmax(idx+1, num, ch), 1+findmax(idx+1, A[idx], ch))
            elif A[idx] == num:
                result_2 = 0 + findmax(idx + 1, num, ch)
            elif A[idx] > num:
                result_3 = 0 + findmax(idx + 1, num, ch)
        #elif ch == 2:
            #return 0
        dp_table[ch][idx][num] = max(result_1, result_2, result_3)
    return dp_table[ch][idx][num]


print(findmax(0, 0, 0))
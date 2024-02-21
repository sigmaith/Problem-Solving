import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))
dp_table = [[0] * 10001 for _ in range(N)] #dp_table에는 find(idx,num)값을 다시 연산하지 않도록 값을 기록

# 지금 이 수를 선택을 할지 말지 결정하는 로직이 필요
# 재귀의 단계단계에서는 현재 수를 선택 or 선택 x

def find(idx, num):  # idx: traverse A, num: before number(선택한 수열의 가장 이전 최신값)
    if idx == N:
        return 0
    else:
        if dp_table[idx][num] == 0:
            if A[idx]>num:
                result = max(0 + find(idx + 1, num), 1 + find(idx + 1, A[idx]))  #선택 안하고 넘기기 / 선택하고 넘기기
            else:
                result = 0 + find(idx+1, num)
            dp_table[idx][num] = result
        return dp_table[idx][num]


print(find(0, 0))

import sys
input = sys.stdin.readline
from bisect import bisect_left

def lengthofLIS(nums):
    piles = []

    for num in nums:
        idx = bisect_left(piles, num)
        if idx == len(piles):
            piles.append(num)
        else:
            piles[idx] = num

    return len(piles)


def main():
    n = int(input())
    array = list(map(int, input().split()))

    result = lengthofLIS(array)
    print(result)


if __name__ == "__main__":
    main()
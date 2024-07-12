def nqueens(row):
    # 기저 조건: 모든 행에 퀸을 배치했으면 하나의 유효한 해를 찾은 것이므로 1 반환
    if row == n:
        return 1

    # 가능한 해의 수를 저장할 변수
    result = 0

    # 현재 행의 모든 열을 검사
    for c in range(n):
        # 만약 현재 열이나 대각선에 이미 퀸이 배치되어 있다면 건너뜀
        if c in col or row + c in pos or row - c in neg:
            continue

        # 퀸을 배치할 수 있는 경우
        else:
            col.append(c)        # 현재 열에 퀸 배치
            pos.append(row + c)  # 정 대각선에 퀸 배치
            neg.append(row - c)  # 역 대각선에 퀸 배치
            # 다음 행으로 재귀 호출하여 나머지 퀸 배치를 시도하고, 그 결과를 결과에 추가
            result += nqueens(row + 1)
            # 백트래킹: 현재 위치에서 퀸을 제거하고 다음 가능성 검토
            col.pop()
            pos.pop()
            neg.pop()

    # 이 행에 대한 모든 열과 대각선을 검토한 후 결과 반환
    return result

# 퀸이 배치된 열을 추적하는 리스트
col = []
# 퀸이 배치된 정 대각선을 추적하는 리스트
pos = []
# 퀸이 배치된 역 대각선을 추적하는 리스트
neg = []
# 체스판의 크기 입력 받음
n = int(input())
# 백트래킹 시작 및 결과 출력
print(nqueens(0))

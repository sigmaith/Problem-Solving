import sys
input = sys.stdin.readline

def find_set(x):
    if trees[x][0] != x:
        trees[x][0] = find_set(trees[x][0])
    return trees[x][0]

def union(x, y):
    if trees[x][1] > trees[y][1]:
        trees[y][0] = x
    else:
        if trees[x][1] == trees[y][1]:
            trees[y][1] += 1
        trees[x][0] = y

n, m = map(int, input().split())  # 사람의 수, 파티의 수
trees = [[i, 0] for i in range(n+1)]  # 1 base index (조상과 rank)

truth = list(map(int, input().split()))  # 진실을 아는 사람, 사람 번호
for i in range(2, len(truth)):
    x_anc = find_set(truth[1])
    y_anc = find_set(truth[i])
    if x_anc != y_anc:
        union(x_anc, y_anc)  # 진실을 아는 모임

party = []
for _ in range(m):
    people = list(map(int, input().split()))  # 파티에 온 사람 수, 숫자

    flag = 0 if truth[0] == 0 else people[1] in truth[1:]  # 진실을 아는가?
    for i in range(2, people[0]+1):

        if truth[0] != 0 and people[i] in truth[1:]:
            flag = 1
        x_anc = find_set(people[1])
        y_anc = find_set(people[i])
        if x_anc != y_anc:
            union(x_anc, y_anc)  # 파티 네트워크 형성

    if not flag:
        party.append(people)

#print("party", party)
#print("trees", trees)

if truth[0] != 0:
    result = 0
    for people in party:
        flag = 0
        for i in range(1, len(people)):
            for j in range(1, len(truth)):
                if find_set(people[i]) == find_set(truth[j]):
                    flag = 1
                    break
        if not flag:
            result += 1
    print(result)
else:
    print(len(party))


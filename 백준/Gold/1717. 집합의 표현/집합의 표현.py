import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**3)

n, m = map(int, input().split())
arr = []
depth = [0 for _ in range(n+1)]
for i in range(n+1):
    arr.append(i)
#print(arr)

def findset(idx):
    if arr[idx] == idx:
        return idx
    else:
        arr[idx] = findset(arr[idx])
        return arr[idx]

for _ in range(m):
    exp, a, b = map(int, input().split())  # 0은 합, 1은 같은 집합에 있는지 확인
    if exp:
        if findset(a) == findset(b):
            print("YES")
        else:
            print("NO")
    else:
        #print("a의 대표노드: ", findset(a))
        #print("b의 대표노드: ", findset(b))
        a_anc = findset(a)
        b_anc = findset(b)
        if depth[a_anc] > depth[b_anc]:
            arr[b_anc] = a_anc

        elif depth[a_anc] < depth[b_anc]:
            arr[a_anc] = b_anc

        elif depth[a_anc] == depth[b_anc]:
            arr[b_anc] = a_anc
            depth[b_anc] += 1


        # a, b의 대표 노드를 찾는다
        # a의 대표노드나 b의 대표노드에 서로의 대표노드를 저장한다.(연결)

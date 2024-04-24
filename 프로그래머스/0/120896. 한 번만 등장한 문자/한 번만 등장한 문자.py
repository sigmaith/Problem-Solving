def solution(s):
    answer = ''
    l = []
    for i in range(0, len(s)):
        flag = 1
        for j in range(0, len(s)):
            if i != j and s[i] == s[j]:
                flag = 0
        if flag:
            l.append(s[i])
    l.sort()
    for c in l:
        answer += c
    
    return answer
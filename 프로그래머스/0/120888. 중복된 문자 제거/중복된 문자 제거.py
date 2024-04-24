

def solution(my_string):
    answer = my_string[0]
    for i in range(1,len(my_string)):
        flag = 1
        for j in range(0, i):
            if my_string[j] == my_string[i]:
                flag = 0
        if flag:
            answer += my_string[i]
    return answer
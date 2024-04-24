def solution(s):
    i = 0
    answer = 0
    while i < len(s):
        if s[i:i+4] == 'zero':
            answer = answer*10 + 0
            i += 4
        elif s[i:i+3] == 'one':
            answer = answer*10 + 1
            i += 3
        elif s[i:i+3] == 'two':
            answer = answer*10 + 2
            i += 3
        elif s[i:i+5] == 'three':
            answer = answer*10 + 3
            i += 5
        elif s[i:i+4] == 'four':
            answer = answer*10 + 4
            i += 4
        elif s[i:i+4] == 'five':
            answer = answer*10 + 5
            i += 4
        elif s[i:i+3] == 'six':
            answer = answer*10 + 6
            i += 3
        elif s[i:i+5] == 'seven':
            answer = answer*10 + 7
            i += 5
        elif s[i:i+5] == 'eight':
            answer = answer*10 + 8
            i += 5
        elif s[i:i+4] == 'nine':
            answer = answer*10 + 9
            i += 4
        elif s[i] == '0':
            answer = answer*10 + 0
            i += 1
        elif s[i] == '1':
            answer = answer*10 + 1
            i += 1
        elif s[i] == '2':
            answer = answer*10 + 2
            i += 1
        elif s[i] == '3':
            answer = answer*10 + 3
            i += 1
        elif s[i] == '4':
            answer = answer*10 + 4
            i += 1
        elif s[i] == '5':
            answer = answer*10 + 5
            i += 1
        elif s[i] == '6':
            answer = answer*10 + 6
            i += 1
        elif s[i] == '7':
            answer = answer*10 + 7
            i += 1
        elif s[i] == '8':
            answer = answer*10 + 8
            i += 1
        elif s[i] == '9':
            answer = answer*10 + 9
            i += 1
    return answer
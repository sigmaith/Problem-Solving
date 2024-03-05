import sys
input = sys.stdin.readline

s = input().rstrip()
existing = s[0]
invert = 0
for i in range(len(s)):
    if s[i] != existing:
        #print(s[i])
        #print('invert',invert)
        invert += 1
        existing = s[i]

#print('invert', invert)
if s[0] == s[-1] and invert != 0:
    print(invert//2)
else:
    print((invert+1)//2)
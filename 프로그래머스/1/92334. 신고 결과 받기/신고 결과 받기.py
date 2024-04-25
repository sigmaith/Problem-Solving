def solution(id_list, report, k):
    dict1 = {}
    #dict2 = {}
    answer = {}
    
    for name in id_list:
        dict1[name] = set()
        answer[name] = 0
    
    for repo in report:
        repo = repo.split()
        dict1[repo[1]].add(repo[0])

    #print("dict1", dict1) #신고 받은 사람 기준
    #print("dict2", dict2) #신고 한사람 기준
    
    
    for person in dict1:
        if len(dict1[person]) >= k:
            for p in dict1[person]:
                answer[p] += 1
    #print("dict1", dict1) #신고 받은 사람 기준

    ans = []
    for x in answer:
        ans.append(answer[x])
    
    return ans
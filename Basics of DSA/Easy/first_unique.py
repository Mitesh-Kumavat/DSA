# Leetcode : 
def firstUniqChar(s: str):
    map = {}
    find = ''
    for i in s:
        if i not in map:
            map[i] = 1
        else:
            map[i] += 1
    
    for i in map:
        if map[i] == 1:
            find = i
            break
            
    for i in range(0, len(s)):
        if s[i] == find:
            return i
        
    return -1
            
d = firstUniqChar("aabb")
print(d)
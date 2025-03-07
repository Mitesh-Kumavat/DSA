# Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
# Leetcode: https://leetcode.com/problems/add-digits/
# 
# Input: num = 38
# Output: 2
# Explanation: The process is
# 38 --> 3 + 8 --> 11
# 11 --> 1 + 1 --> 2 
# Since 2 has only one digit, return it.

def sum_of_digit(n: int) -> int:
    sum = 0
    while(n > 0):
        last = n%10
        sum += last
        n = n//10
    return sum

def add_digits(num: int) -> int:
    while(num > 9):
        num = sum_of_digit(num)
    return num
    
n =34234  
print(add_digits(n))
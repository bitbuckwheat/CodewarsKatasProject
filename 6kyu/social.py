# https://www.codewars.com/kata/58cfa5bd1c694fe474000146
import pdb


def socialist_distribution(popul, m):
    p = popul.copy()
    if len(p)*m > sum(p):
        return []

    while min(p) < m:
        p[p.index(max(p))] -= 1
        p[p.index(min(p))] += 1
    return p


print(socialist_distribution([8, 39, 18, 43, 44, 15, 19, -8, -8], 15))
# [15, 24, 18, 24, 25, 15, 19, 15, 15]
# print(socialist_distribution([2, 3, 5, 15, 75], 5))
# # [5,5,5,15,70]
# print(socialist_distribution([2, 3, 5, 15, 75], 20))
# # [20,20,20,20,20]
# print(socialist_distribution([2, 3, 5, 45, 45], 5))
# # [5,5,5,42,43]
# print(socialist_distribution([2, 3, 5, 45, 45], 30))
# # []
# print(socialist_distribution([24, 48, 22, 19, 37], 30))
# # [30,30,30,30,30]

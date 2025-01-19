# https://www.codewars.com/kata/59590976838112bfea0000fa
#
#
from itertools import cycle


def beggars(values, n):
    res = [0 for i in range(n)]
    for i in zip(values, cycle([j for j in range(n)])):
        res[i[1]] += i[0]
    print(res)
    print('___')
    return res


beggars([1, 2, 3, 4, 5], 1)
# 1, [15]
beggars([1, 2, 3, 4, 5], 2)
# 2, [9,6]
beggars([1, 2, 3, 4, 5], 3)
# 3, [5,7,3]
beggars([1, 2, 3, 4, 5], 6)
# 6, [1,2,3,4,5,0]
beggars([1, 2, 3, 4, 5], 0)
# 0, []

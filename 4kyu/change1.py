# https://www.codewars.com/kata/541af676b589989aed0009e7
#
#
from itertools import product


def count_change(money, coins):
    res = 0
    m = [list(range(0, i*(money // i + 1), i)) for i in coins]
    for i in m:
        if money != 0 and money in i:
            i.pop()
            res += 1
    print('m:', m)
    # print('min:', min(*m))
    z = list(product(*m))
    # z = list(filter(lambda i: sum(i) == money, product(*m)))
    print('z:', len(z), z)
    for item in z:
        # print(item)
        if sum(item) == money:
            res += 1
    # print(res)
    return res


print(count_change(6, [1, 2]))
# 4
print(count_change(4, [1, 2]))
# count_change(4, [1, 2])
# 1+1+1+1, 1+1+2, 2+2
print(count_change(10, [5, 2, 3]))
# 4
print(count_change(11, [5, 7]))
# 0
print(count_change(0, [1, 2]))
# 1
# print(event) or something_else(and):

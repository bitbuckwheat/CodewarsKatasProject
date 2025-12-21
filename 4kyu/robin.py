# https://www.codewars.com/kata/561c20edc71c01139000017c
#
from itertools import product, combinations
import pdb


def build_matches_table(teams: int) -> list[list[(int, int)]]:
    t = range(1, teams+1)
    zz = list(combinations(t, 2))
    # qq = []
    # for ii in t:
    #     for jj in t:
    #         if ii != jj and (ii, jj) not in qq:
    #             qq.append((jj, ii))
    # print('zz:', zz)
    # print('qq:', qq)
    res = [[] for i in range(teams-1)]
    ss = [[] for i in range(teams-1)]
    # res = [[]]*(teams-1)
    # ss = [[]]*(teams-1)
    breakpoint()

    r_len = len(zz)/(teams-1)
    for ii in range(teams-1):
        while len(res[ii]) < r_len:
            for jj in zz[1::2]:
                if jj[0] in ss[ii] or jj[1] in ss[ii]:
                    continue
                else:
                    res[ii].append(jj)
                    ss[ii].extend(jj)
                    zz.pop(zz.index(jj))
                    break
            for jj in zz[::2]:
                if jj[0] in ss[ii] or jj[1] in ss[ii]:
                    continue
                else:
                    res[ii].append(jj)
                    ss[ii].extend(jj)
                    zz.pop(zz.index(jj))
                    break

    # for ii in qq[::2]:
    #     for jj in range(teams-1):
    #         if ii[0] in ss[jj] or ii[1] in ss[jj]:
    #             continue
    #         else:
    #             res[jj].append(ii)
    #             ss[jj].extend(ii)
    #             break
    # for ii in qq[1::2]:
    #     for jj in range(teams-1):
    #         if ii[0] in ss[jj] or ii[1] in ss[jj]:
    #             continue
    #         else:
    #             res[jj].append(ii)
    #             ss[jj].extend(ii)
    #             break
    # print(res)
    print('___')
    return res


# print(build_matches_table(4))
print(build_matches_table(6))

# [
#   [(1, 2), (3, 4)],  # first round:  1 vs 2, 3 vs 4
#   [(1, 3), (2, 4)],  # second round: 1 vs 3, 2 vs 4
#   [(1, 4), (2, 3)]   # third round:  1 vs 4, 2 vs 3
# ]

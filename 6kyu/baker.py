# https://www.codewars.com/kata/5267e5827526ea15d8000708
#
#
from math import ceil


def get_missing_ingredients(recipe, added):
    res = {}
    added_filtered = {}
    for i in added:
        if i in recipe:
            added_filtered[i] = added[i]
    if added_filtered == {}:
        return recipe
    cakes = ceil(max([added_filtered[i]/recipe[i] for i in added_filtered]))
    for i in recipe:
        if i in added_filtered:
            amount = recipe[i] * cakes - added_filtered[i]
        else:
            amount = recipe[i] * cakes
        if amount:
            res[i] = amount
    print(res)
    return res


recipe = {"flour": 200, "eggs": 1, "sugar": 100}
get_missing_ingredients(recipe, {"flour": 100})
# {"flour": 100, "eggs": 1, "sugar": 100})
get_missing_ingredients(recipe, {"flour": 200, "sugar": 100})
# {"eggs": 1})

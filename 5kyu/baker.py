# https://www.codewars.com/kata/525c65e51bf619685c000059
#

def cakes(recipe, available):
    print('pass')
    cakes = []
    for i in recipe:
        if i not in available or recipe[i] > available[i]:
            return 0
        else:
            cakes.append(available[i]//recipe[i])
    print(cakes)
    return min(cakes)


recipe = {"flour": 500, "sugar": 200, "eggs": 1}
available = {"flour": 1200, "sugar": 1200, "eggs": 5, "milk": 200}
print(cakes(recipe, available))
# 2

recipe = {"apples": 3, "flour": 300, "sugar": 150, "milk": 100, "oil": 100}
available = {"sugar": 500, "flour": 2000, "milk": 2000}
print(cakes(recipe, available))
# 0

recipe = {"apples": 3, "flour": 300, "sugar": 150, "milk": 100, "oil": 100}
available = {}
print(cakes(recipe, available))
# 0

recipe = {"flour": 500, "sugar": 200, "eggs": 1}
available = {"flour": 400, "sugar": 1200, "eggs": 5, "milk": 200}
print(cakes(recipe, available))
# 2

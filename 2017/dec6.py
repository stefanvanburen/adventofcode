#!/usr/bin/env python


PART1SOL = 12841
PART2SOL = 8038


# 12840 - too low
# 12841 - just right
def part1(x):
    seen = []
    count = 0
    while True:
        m = redistrib(x)
        count += 1
        if m in seen:
            return count
        seen.append(m)


# 8038
def part2(x):
    seen = []
    count = 0
    while True:
        m = redistrib(x)
        count += 1
        if m in seen:
            idx = seen.index(m)
            return len(seen) - idx
        seen.append(m)


def redistrib(x):
    i = max_index(x)
    num = x[i]
    x[i] = 0
    i += 1
    while num > 0:
        x[i % len(x)] += 1
        i += 1
        num -= 1
    state = str(x[:])
    return state


def max_index(z):
    mx = z[0]
    idx = 0
    for i, x in enumerate(z):
        if x > mx:
            mx = x
            idx = i
    return idx


if __name__ == '__main__':
    with open('./dec6.txt') as f:
        print(part1([0, 2, 7, 0]) == 5)
        print(part2([0, 2, 7, 0]) == 4)
        z = f.readline()
        inp = [int(x) for x in z.split()]
        print(part1(inp) == PART1SOL)
        print(part2(inp) == PART2SOL)

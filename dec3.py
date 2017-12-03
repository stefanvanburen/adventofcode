#!/usr/bin/env python

# http://adventofcode.com/2017/day/3
#
# Attempts:
# 284 - too low
# 564 - too high
# 418 - too low

inp = 312051


def dec3(i):
    print("I: ", i)
    x = 1
    above = 0
    below = 0
    while True:
        x2 = x ** 2
        if x2 > i:
            above = x2
            below = (x - 2) ** 2
            break
        x += 2

    size = (x-1) / 2

    # Base case
    if size == 1:
        return 0

    d = dist(x)

    l = above-below
    print("Above-Below", l)
    points = [1, 3, 5, 7]
    candidates = [((l//8) * p)+below for p in points]
    print("Size: ", size)
    print("Candidates: ", candidates)
    print("i: ", i)
    # print((x-1) / 2)
    # print(size + 13)
    md = min_dist(candidates, i)
    print("min_dist", md)
    return size + min(md)

def dist(z):
    return (z-1) / 2

def min_dist(points, v):
    print("V: ", v)
    return [abs(v-p) for p in points]


if __name__ == '__main__':
    for i in [1, 12, 23, 1024]:
        print(dec3(i))
    print([dec3(i) for i in [1, 12, 23, 1024]])
    print(dec3(inp))

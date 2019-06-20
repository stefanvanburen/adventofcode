#!/usr/bin/env python

# http://adventofcode.com/2017/day/3
#
# Attempts:
# 284 - too low
# 564 - too high
# 418 - too low
# 430 - answer

inp = 312051


def part1(i):
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

    size = (x - 1) / 2

    # Base case
    if size == 1:
        return 0

    length = above - below
    points = [1, 3, 5, 7]
    candidates = [((length // 8) * p) + below for p in points]
    md = min_dist(candidates, i)
    return size + md


def min_dist(points, v):
    return min([abs(v - p) for p in points])


def part2(i):
    # https://oeis.org/A141481/b141481.txt
    pass


if __name__ == '__main__':
    testin = [1, 12, 23, 1024]
    testout = [0, 3, 2, 31]
    # tests
    for x, y in zip(testin, testout):
        print(part1(x) == y)
    # testing on input
    print(part1(inp))

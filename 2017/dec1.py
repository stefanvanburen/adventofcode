#!/usr/bin/env python

import sys


def part1(x):
    s = 0
    n = len(x) / 2
    for i in range(len(x) - 1):
        if x[i] == x[(i + n) % len(x)]:
            print(x[i], "matches", x[(i + n) % len(x)])
            s += int(x[i])
    print(s)


if __name__ == '__main__':
    part1(sys.argv[1])

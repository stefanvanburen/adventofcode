#!/usr/bin/env python
def part1(x):
    s = 0
    for l in x:
        if check(l):
            s += 1
    return s


def check(z):
    p = z.split()
    d = dict()
    for f in p:
        if f in d:
            return False
        else:
            d[f] = True
    return True


def part2(x):
    s = 0
    for l in x:
        if sorted_check(l):
            s += 1
    return s


def sorted_check(z):
    p = z.split()
    d = dict()
    for f in p:
        m = str(sorted(f))
        if m in d:
            return False
        else:
            d[m] = True
    return True


if __name__ == '__main__':
    with open('./dec4.txt', 'r') as f:
        print(part1(f))
        f.seek(0)
        print(part2(f))

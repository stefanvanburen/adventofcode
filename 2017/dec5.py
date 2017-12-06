#!/usr/bin/env python


PART1SOL = 388611
PART2SOL = 27763113


# Right on the first try!
def part1(x):
    count = 0
    loc = 0
    while True:
        try:
            dat = x[loc]
        except IndexError:
            return count
        count += 1
        x[loc] += 1
        loc += dat


# 137 too low
# 169 too low
def part2(x):
    count = 0
    loc = 0
    while True:
        try:
            dat = x[loc]
        except IndexError:
            return count
        count += 1
        if dat >= 3:
            x[loc] -= 1
        else:
            x[loc] += 1
        loc += dat


if __name__ == "__main__":
    with open('./dec5.txt', 'r') as f:
        test1 = [0, 3, 0, 1, -3]
        test2 = test1[:]
        print(part1(test1) == 5)
        print(part2(test2) == 10)
        inp1 = [int(z) for z in f]
        inp2 = inp1[:]
        print(part1(inp1) == PART1SOL)
        f.seek(0)
        print(part2(inp2) == PART2SOL)
